package easeplan.netease.sales.service;

import easeplan.netease.sales.domain.CartItem;
import easeplan.netease.sales.mapper.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Component
public class CartService implements ICartService {
    @Autowired
    CartItemMapper cartItemMapper;

    @Override
    public List<CartItem> getCartItemList() {
        return cartItemMapper.getCartItemList();
    }

    @Override
    public void addItem(int id) {
        CartItem cartItem = cartItemMapper.getCartItem(id);
        if (cartItem != null) {
            int amount = 1;
            amount += cartItem.getAmount();
            cartItemMapper.updateItemAmount(id, amount);
        } else {
            cartItemMapper.insertItem(id);
        }
    }

    @Override
    public void updateItemAmount(int id, int amount) {
        cartItemMapper.updateItemAmount(id, amount);
    }

    @Override
    public void deleteItem(int id) {
        cartItemMapper.deleteItem(id);
    }
}
