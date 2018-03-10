package easeplan.netease.sales.controller;

import easeplan.netease.sales.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/8</pre>
 */
@Controller
public class Login {
    @Autowired
    IAuthService authService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
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
            authService.addJUserCookie(username, response);
        } else {
            response.sendError(403);
        }
    }


}
