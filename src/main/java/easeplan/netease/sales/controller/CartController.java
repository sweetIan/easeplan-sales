package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.CartItem;
import easeplan.netease.sales.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class CartController {
    @Autowired
    ICartService cartService;

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public ModelAndView cartPage(
            @RequestHeader(value = "Referer", required = false, defaultValue = "/") String referer
    ) {
        ModelAndView mav = new ModelAndView("cart");
        List<CartItem> cartItems = cartService.getCartItemList();
        mav.addObject("cartItems", cartItems);
        try {
            if (!new URI(referer).getPath().startsWith("/cart")) {
                mav.addObject("referer", referer);
            }
        } catch (URISyntaxException e) {
        }
        return mav;
    }

    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addItem(
            @PathVariable int id
    ) {
        cartService.addItem(id);
    }

    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateItemAmount(
            @PathVariable int id,
            @RequestParam int num
    ) {
        cartService.updateItemAmount(id, num);
    }

    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItem(@PathVariable int id) {
        cartService.deleteItem(id);
    }
}
