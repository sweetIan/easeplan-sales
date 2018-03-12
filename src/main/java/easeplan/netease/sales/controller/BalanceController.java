package easeplan.netease.sales.controller;

import easeplan.netease.sales.domain.PurchasedItem;
import easeplan.netease.sales.util.PriceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
@Controller
public class BalanceController {
    @RequestMapping(value= "/balance", method = RequestMethod.GET)
    public ModelAndView balancePage() {
        ModelAndView mav = new ModelAndView("balance");
        List<PurchasedItem> purchasedItems = Arrays.asList(PurchasedItem.sample());
        mav.addObject("purchasedItems", purchasedItems);
        int total = purchasedItems.stream().mapToInt(item -> item.getBoughtAmount() * Integer.valueOf(item.getBoughtPrice().replace(".", ""))).sum();
        mav.addObject("total", PriceUtil.formatPrice(total));
        return mav;
    }
}
