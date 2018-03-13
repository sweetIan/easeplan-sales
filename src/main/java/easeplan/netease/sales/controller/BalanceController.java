package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.PurchasedItem;
import easeplan.netease.sales.service.IBalanceService;
import easeplan.netease.sales.util.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class BalanceController {
    @Autowired
    IBalanceService balanceService;

    @RequestMapping(value= "/balance", method = RequestMethod.GET)
    public ModelAndView balancePage() {
        ModelAndView mav = new ModelAndView("balance");
        List<PurchasedItem> purchasedItems = balanceService.getPurchasedItemList();
        mav.addObject("purchasedItems", purchasedItems);
        int total = purchasedItems.stream().mapToInt(item -> item.getBoughtAmount() * Integer.valueOf(item.getBoughtPrice().replace(".", ""))).sum();
        mav.addObject("total", PriceUtil.formatPrice(total));
        return mav;
    }

    @RequestMapping(value = "/api/purchase", method = RequestMethod.POST)
    @ResponseBody
    public void purchaseApi() {
        balanceService.purchase();
    }
}
