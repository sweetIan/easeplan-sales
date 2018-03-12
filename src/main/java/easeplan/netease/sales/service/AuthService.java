package easeplan.netease.sales.service;

import com.google.common.collect.ImmutableMap;
import easeplan.netease.sales.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Component
// todo 改成用JWT
public class AuthService implements IAuthService {
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
        return  (PASSWORD_MAP.containsKey(username) && password != null && PASSWORD_MAP.get(username).equals(password.toUpperCase()));
    }

    @Override
    public void addJUserCookie(String username, HttpServletResponse response) {
        Cookie cookie = new Cookie("identity", username);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    @Override
    public Optional<User> getCookieJUser(Cookie[] cookies) {
        if (cookies != null) {
            Optional<Cookie> identityCookieOptional = Stream.of(cookies).filter(cookie -> "identity".equals(cookie.getName())).findFirst();
            if (identityCookieOptional.isPresent()) {
                return Optional.ofNullable(USER_MAP.get(identityCookieOptional.get().getValue()));
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
}
