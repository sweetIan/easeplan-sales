package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * 购物车内容
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class CartItem {
    private int id;
    private String title;
    private long price;
    private int amount;
}
