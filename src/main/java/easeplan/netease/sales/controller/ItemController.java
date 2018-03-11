package easeplan.netease.sales.controller;

import easeplan.netease.sales.json.ItemDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class ItemController {
    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(
            @PathVariable String id
    ) {
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("product", ItemDetail.sample());
        return mav;
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(
            @PathVariable String id
    ) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("edit", true);
        mav.addObject("product", ItemDetail.sample());
        return mav;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView newPage(
    ) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("edit", false);
        return mav;
    }

    @RequestMapping(path = "/api/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String editApi(
            @PathVariable String id
    ) {
        return "{\"id\":\"edit\"}";
    }

    @RequestMapping(path = "/api/new", method = RequestMethod.POST)
    @ResponseBody
    public String newApi(
    ) {
        return "{\"id\":\"new\"}";
    }
}

