package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.ItemDetail;
import easeplan.netease.sales.domain.PurchasedItem;
import easeplan.netease.sales.service.IAuthService;
import easeplan.netease.sales.service.IBalanceService;
import easeplan.netease.sales.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class ItemController {
    @Autowired
    IAuthService authService;
    @Autowired
    IItemService itemService;
    @Autowired
    IBalanceService balanceService;

    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(
            @PathVariable int id,
            @CookieValue(value = "identity", required = false) String identity
    ) {
        ModelAndView mav = new ModelAndView("detail");
        ItemDetail itemDetail = itemService.getItemDetail(id);
        mav.addObject("item", itemDetail);
        if (authService.isBuyer(identity)) {
            List<PurchasedItem> purchaseHistory = balanceService.getPurchasedHistoryById(id);
            mav.addObject("purchaseHistory", purchaseHistory);
        }
        return mav;
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(
            @PathVariable int id,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isSeller(identity)) {
            ModelAndView mav = new ModelAndView("edit");
            mav.addObject("edit", true);
            ItemDetail itemDetail = itemService.getItemDetail(id);
            mav.addObject("item", itemDetail);
            return mav;
        } else {
            response.sendRedirect("/");
            return null;
        }
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView newPage(
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isSeller(identity)) {
            ModelAndView mav = new ModelAndView("edit");
            mav.addObject("edit", false);
            return mav;
        } else {
            response.sendRedirect("/");
            return null;
        }
    }

    @RequestMapping(path = "/api/items", method = RequestMethod.POST)
    @ResponseBody
    public ItemDetail newItemApi(
            @RequestBody ItemDetail item,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isSeller(identity)) {
            int id = itemService.newItem(item);
            item.setId(id);
            return item;
        } else {
            response.sendError(401);
            return null;
        }
    }

    @RequestMapping(path = "/api/items/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ItemDetail editItemApi(
            @PathVariable int id,
            @RequestBody ItemDetail item,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isSeller(identity)) {
            itemService.updateItem(id, item);
            return item;
        } else {
            response.sendError(401);
            return null;
        }
    }

    @RequestMapping(path = "/api/items/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItemApi(
            @PathVariable int id,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isSeller(identity)) {
            itemService.deleteItem(id);
        } else {
            response.sendError(401);
        }
    }
}

