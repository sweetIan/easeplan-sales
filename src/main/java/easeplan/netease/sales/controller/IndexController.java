package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.ItemAbstract;
import easeplan.netease.sales.service.IAuthService;
import easeplan.netease.sales.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    IItemService itemService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(required = false, defaultValue = "false") boolean filter
    ) {
        ModelAndView mav = new ModelAndView("index");
        List<ItemAbstract> items;
        if (filter) {
            items = itemService.getItemAbstractListUnsold();
        } else {
            items = itemService.getItemAbstractListAll();
        }
        mav.addObject("items", items);
        mav.addObject("filter", filter);
        return mav;
    }
}
