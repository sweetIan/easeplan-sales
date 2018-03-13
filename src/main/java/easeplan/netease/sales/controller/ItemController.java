package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.ItemDetail;
import easeplan.netease.sales.service.implementation.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ItemService itemService;

    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(
            @PathVariable int id
    ) {
        ModelAndView mav = new ModelAndView("detail");
        ItemDetail itemDetail = itemService.getItemDetail(id);
        mav.addObject("item", itemDetail);
        return mav;
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(
            @PathVariable int id
    ) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("edit", true);
        ItemDetail itemDetail = itemService.getItemDetail(id);
        mav.addObject("item", itemDetail);
        return mav;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView newPage(
    ) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("edit", false);
        return mav;
    }

    @RequestMapping(path = "/api/items", method = RequestMethod.POST)
    @ResponseBody
    public ItemDetail newItemApi(
            @RequestBody ItemDetail item
    ) {
        int id = itemService.newItem(item);
        item.setId(id);
        return item;
    }

    @RequestMapping(path = "/api/items/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ItemDetail editItemApi(
            @PathVariable int id,
            @RequestBody ItemDetail item
    ) {
        itemService.updateItem(id, item);
        return item;
    }

    @RequestMapping(path = "/api/items/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItemApi(
            @PathVariable int id
    ) {
        itemService.deleteItem(id);
    }
}

