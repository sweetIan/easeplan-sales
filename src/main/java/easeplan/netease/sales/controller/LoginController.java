package easeplan.netease.sales.controller;

import easeplan.netease.sales.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/8</pre>
 */
@Controller
public class LoginController {
    @Autowired
    IAuthService authService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(
            @RequestHeader(name = "Referer", required = false, defaultValue = "/") String referer,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isLogin(identity)) {
            response.sendRedirect("/");
            return null;
        } else {
            ModelAndView mav = new ModelAndView("login");
            try {
                if (!new URI(referer).getPath().startsWith("/login")) {
                    mav.addObject("referer", referer);
                }
            } catch (URISyntaxException e) {
            }
            return mav;
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response) throws IOException {
        authService.removeLogin(response);
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response
    ) throws IOException {
        if (authService.checkLoginInfo(username, password)) {
            authService.setIdentityCookie(username, response);
        } else {
            response.sendError(403);
        }
    }
}
