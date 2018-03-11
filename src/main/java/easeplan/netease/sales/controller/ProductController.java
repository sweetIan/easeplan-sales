package easeplan.netease.sales.controller;

import easeplan.netease.sales.json.JProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class ProductController {
    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(
            @PathVariable String id
    ) {
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("product", JProduct.sample());
        return mav;
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(
            @PathVariable String id
    ) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("product", JProduct.sample());
        return mav;
    }

    @RequestMapping(path = "/api/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String editApi(
            @PathVariable String id
    ) {
        return "{\"id\":\"happy\"}";
    }
}
