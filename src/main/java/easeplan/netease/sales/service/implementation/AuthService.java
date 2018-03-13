package easeplan.netease.sales.service.implementation;

import com.google.common.collect.ImmutableMap;
import easeplan.netease.sales.domain.User;
import easeplan.netease.sales.service.IAuthService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Component
public class AuthService implements IAuthService {
    private static final Key key = MacProvider.generateKey();

    private static Map<String, String> PASSWORD_MAP = ImmutableMap.of(
            "buyer", DigestUtils.md5DigestAsHex("reyub".getBytes()).toUpperCase(),
            "seller", DigestUtils.md5DigestAsHex("relles".getBytes()).toUpperCase()
    );
    private static Map<String, User> USER_MAP = ImmutableMap.of(
            "buyer", new User("buyer", "有钱的金主", "buyer"),
            "seller", new User("seller", "叮当猫的口袋", "seller")
    );

    @Override
    public boolean checkLoginInfo(String username, String password) {
        return (PASSWORD_MAP.containsKey(username) && password != null && PASSWORD_MAP.get(username).equals(password.toUpperCase()));
    }

    @Override
    public void setIdentityCookie(String username, HttpServletResponse response) {
        String identityCookieValue = Jwts.builder()
                                         .setSubject(username)
                                         .setExpiration(new Date(System.currentTimeMillis() + 600_1000))
                                         .signWith(SignatureAlgorithm.HS512, key)
                                         .compact();
        Cookie cookie = new Cookie("identity", identityCookieValue);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    @Override
    public Optional<User> getCookieJUser(Cookie[] cookies) {
        if (cookies != null) {
            Optional<Cookie> identityCookieOptional = Stream.of(cookies)
                                                            .filter(cookie -> "identity".equals(cookie.getName())
                                                                    && !StringUtils.isEmpty(cookie.getValue()))
                                                            .findFirst();
            if (identityCookieOptional.isPresent()) {
                return getUser(identityCookieOptional.get().getValue());
            }
        }
        return Optional.empty();
    }

    @Override
    public void removeLogin(HttpServletResponse response) {
        Cookie cookie = new Cookie("identity", "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Override
    public boolean isLogin(String jwtString) {
        Optional<User> userOptional = getUser(jwtString);
        return userOptional.isPresent();
    }

    @Override
    public boolean isBuyer(String jwtString) {
        Optional<User> userOptional = getUser(jwtString);
        return userOptional.isPresent() && userOptional.get().getRole().equals("buyer");
    }

    @Override
    public boolean isSeller(String jwtString) {
        Optional<User> userOptional = getUser(jwtString);
        return userOptional.isPresent() && userOptional.get().getRole().equals("seller");
    }

    /**
     * 尝试根据JWT凭据获取用户
     *
     * @param jwtString
     * @return
     */
    private Optional<User> getUser(String jwtString) {
        if (StringUtils.isEmpty(jwtString)) {
            return Optional.empty();
        }
        try {
            String username = Jwts.parser()
                                  .setSigningKey(key)
                                  .parseClaimsJws(jwtString)
                                  .getBody()
                                  .getSubject();
            return Optional.ofNullable(USER_MAP.get(username));
        } catch (JwtException e) {
            return Optional.empty();
        }
    }
}
