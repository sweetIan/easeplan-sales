package easeplan.netease.sales.service;

import easeplan.netease.sales.json.JUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Service
public interface IAuthService {
    /**
     * 检查用户名密码是否可以登录
     * @param username
     * @param password
     * @return
     */
    boolean checkLoginInfo(String username, String password);

    /**
     * 添加对应用户的身份凭据Cookie到响应头
     * @param username
     * @param response
     */
    void addJUserCookie(String username, HttpServletResponse response);

    /**
     * 检查Cookie中的登录信息，获取对应的JUser（若有）
     * @param cookies
     * @return
     */
    Optional<JUser> getCookieJUser(Cookie[] cookies);

    /**
     * 注销登录
     * @param response
     */
    void removeLogin(HttpServletResponse response);
}
