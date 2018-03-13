package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * 内容购买记录
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class PurchasedItem {
    private int id;
    private String image;
    private String title;
    private long purchaseDate;
    private long purchasePrice;
    private int purchaseAmount;
}
