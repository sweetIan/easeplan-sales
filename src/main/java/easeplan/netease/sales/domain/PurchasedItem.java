package easeplan.netease.sales.domain;

import lombok.Data;

/**
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
    private int purchasePrice;
    private int purchaseAmount;
}
