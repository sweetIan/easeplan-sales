package easeplan.netease.sales.service.implementation;

import easeplan.netease.sales.domain.CartItem;
import easeplan.netease.sales.domain.PurchasedItem;
import easeplan.netease.sales.mapper.CartItemMapper;
import easeplan.netease.sales.mapper.PurchasedItemMapper;
import easeplan.netease.sales.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        List<CartItem> cartItems = cartItemMapper.getCartItemList();
        List<PurchasedItem> purchasedItems = cartItems.stream().map(cartItem -> {
            PurchasedItem purchasedItem = new PurchasedItem();
            purchasedItem.setId(cartItem.getId());
            purchasedItem.setPurchaseDate(System.currentTimeMillis());
            purchasedItem.setPurchasePrice(cartItem.getPrice());
            purchasedItem.setPurchaseAmount(cartItem.getAmount());
            return purchasedItem;
        }).collect(Collectors.toList());
        purchasedItemMapper.batchInsert(purchasedItems);
        cartItemMapper.clear();
    }

    @Override
    public List<PurchasedItem> getPurchasedItemList() {
        return purchasedItemMapper.getPurchasedItemList();
    }

    @Override
    public List<PurchasedItem> getPurchasedHistoryById(int id) {
        return purchasedItemMapper.getPurchasedHistoryById(id);
    }
}
