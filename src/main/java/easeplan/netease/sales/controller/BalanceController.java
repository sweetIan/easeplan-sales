package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.PurchasedItem;
import easeplan.netease.sales.service.IAuthService;
import easeplan.netease.sales.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class BalanceController {
    @Autowired
    IAuthService authService;
    @Autowired
    IBalanceService balanceService;

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ModelAndView balancePage(
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isBuyer(identity)) {
            ModelAndView mav = new ModelAndView("balance");
            List<PurchasedItem> purchasedItems = balanceService.getPurchasedItemList();
            mav.addObject("purchasedItems", purchasedItems);
            int total = purchasedItems.stream().mapToInt(item -> item.getPurchaseAmount() * item.getPurchasePrice()).sum();
            mav.addObject("total", total);
            return mav;
        } else {
            response.sendRedirect("/");
            return null;
        }
    }

    @RequestMapping(value = "/api/purchase", method = RequestMethod.POST)
    @ResponseBody
    public void purchaseApi(
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isBuyer(identity)) {
            balanceService.purchase();
        } else {
            response.sendError(401);
        }
    }
}
