package easeplan.netease.sales.interceptor;

import easeplan.netease.sales.domain.User;
import easeplan.netease.sales.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 身份拦截器
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/9</pre>
 */
@Component
public class IdentityInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IAuthService authService;

    /**
     * 检查是否有登录后的身份 Cookie，若有则在 ModelAndView 中注入 user
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Optional<User> userOptional = authService.getCookieJUser(request.getCookies());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                modelAndView.addObject("user", user);
            }
        }
    }
}
