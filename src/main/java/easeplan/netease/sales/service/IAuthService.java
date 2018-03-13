package easeplan.netease.sales.service;

import easeplan.netease.sales.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 认证服务
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Service
public interface IAuthService {
    /**
     * 检查用户名密码是否可以登录
     *
     * @param username
     * @param password
     * @return
     */
    boolean checkLoginInfo(String username, String password);

    /**
     * 设置用户的身份凭据Cookie
     *
     * @param username
     * @param response
     */
    void setIdentityCookie(String username, HttpServletResponse response);

    /**
     * 检查Cookie中的登录信息，获取对应的JUser（若有）
     *
     * @param cookies
     * @return
     */
    Optional<User> getCookieJUser(Cookie[] cookies);

    /**
     * 注销登录
     *
     * @param response
     */
    void removeLogin(HttpServletResponse response);

    /**
     * 凭据是否为已登录用户
     *
     * @param jwtString
     * @return
     */
    boolean isLogin(String jwtString);

    /**
     * 凭据是否为买家用户
     *
     * @param jwtString
     * @return
     */
    boolean isBuyer(String jwtString);

    /**
     * 凭据是否为卖家用户
     *
     * @param jwtString
     * @return
     */
    boolean isSeller(String jwtString);
}
