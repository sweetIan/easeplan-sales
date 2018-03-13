package easeplan.netease.sales.service.implementation;

import easeplan.netease.sales.domain.PurchasedItem;
import easeplan.netease.sales.mapper.CartItemMapper;
import easeplan.netease.sales.mapper.PurchasedItemMapper;
import easeplan.netease.sales.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Component
public class BalanceService implements IBalanceService {
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    PurchasedItemMapper purchasedItemMapper;

    @Override
    @Transactional
    public void purchase() {

    }

    @Override
    public List<PurchasedItem> getPurchasedItemList() {
        return Arrays.asList(PurchasedItem.sample(), PurchasedItem.sample());
    }
}
