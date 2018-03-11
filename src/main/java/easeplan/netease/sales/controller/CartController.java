package easeplan.netease.sales.controller;

import easeplan.netease.sales.json.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class CartController {
    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public ModelAndView cartPage(
            @RequestHeader(value = "Referer", required = false, defaultValue = "/") String referer
    ) {
        ModelAndView mav = new ModelAndView("cart");
        List<CartItem> cartItems = Arrays.asList(CartItem.sample());
        mav.addObject("cartItems", cartItems);
        try {
            if (!new URI(referer).getPath().startsWith("/cart")) {
                mav.addObject("referer", referer);
            }
        } catch (URISyntaxException e) {
        }
        return mav;
    }
    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void changeItemAmount(
            @PathVariable String id,
            @RequestParam int num
    ) {
    }

    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeItem(@PathVariable String id) {

    }
}
