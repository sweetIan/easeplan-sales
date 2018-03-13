package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class ItemAbstract {
    private String id;
    private String title;
    private int price;
    private String image;
    private int sold;
}
