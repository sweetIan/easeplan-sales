package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.CartItem;
import easeplan.netease.sales.service.IAuthService;
import easeplan.netease.sales.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 购物车
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class CartController {
    @Autowired
    IAuthService authService;
    @Autowired
    ICartService cartService;

    /**
     * 购物车页
     *
     * @param referer
     * @param identity
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public ModelAndView cartPage(
            @RequestHeader(value = "Referer", required = false, defaultValue = "/") String referer,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isBuyer(identity)) {
            ModelAndView mav = new ModelAndView("cart");
            List<CartItem> cartItems = cartService.getCartItemList();
            mav.addObject("cartItems", cartItems);
            int deletedItemCount = cartService.deleteDeletedItems();
            mav.addObject("deletedItemCount", deletedItemCount);
            try {
                if (!new URI(referer).getPath().startsWith("/cart")) {
                    mav.addObject("referer", referer);
                }
            } catch (URISyntaxException e) {
            }
            return mav;
        } else {
            response.sendRedirect("/");
            return null;
        }
    }

    /**
     * 添加指定内容
     *
     * @param id
     * @param identity
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addItem(
            @PathVariable int id,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isBuyer(identity)) {
            cartService.addItem(id);
        } else {
            response.sendError(401);
        }
    }

    /**
     * 修改购物车内指定内容数量
     *
     * @param id
     * @param num
     * @param identity
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateItemAmount(
            @PathVariable int id,
            @RequestParam int num,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isBuyer(identity)) {
            cartService.updateItemAmount(id, num);
        } else {
            response.sendError(401);
        }
    }

    /**
     * 删除指定内容
     *
     * @param id
     * @param identity
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItem(
            @PathVariable int id,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isBuyer(identity)) {
            cartService.deleteItem(id);
        } else {
            response.sendError(401);
        }
    }
}
