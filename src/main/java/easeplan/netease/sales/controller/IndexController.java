package easeplan.netease.sales.controller;

import easeplan.netease.sales.json.ItemAbstract;
import easeplan.netease.sales.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/5</pre>
 */
@Controller
public class IndexController {
    @Autowired
    IAuthService authService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(required = false, defaultValue = "false") boolean filter
    ) {
        ModelAndView mav = new ModelAndView("index");
        List<ItemAbstract> items = Arrays.asList(ItemAbstract.sample(), ItemAbstract.sample());
        mav.addObject("items", items);
        mav.addObject("filter", filter);
        return mav;
    }
}
