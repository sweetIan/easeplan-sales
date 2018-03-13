package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class CartItem {
    private int id;
    private String title;
    private String price;
    private int amount;

    public static CartItem sample() {
        CartItem cartItem = new CartItem();
        cartItem.setAmount(10);
        cartItem.setId(123);
        cartItem.setPrice("1.23");
        cartItem.setTitle("纷纷");
        return cartItem;
    }
}
