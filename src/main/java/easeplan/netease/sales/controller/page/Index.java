package easeplan.netease.sales.controller.page;

import easeplan.netease.sales.json.JUser;
import easeplan.netease.sales.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/5</pre>
 */
@Controller
public class Index {
    @Autowired
    IAuthService authService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        Optional<JUser> userOptional = authService.auth("buyer", "reyub");
        mav.addObject("user", userOptional.get());
        return mav;
    }
}
