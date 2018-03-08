package easeplan.netease.sales.json;

import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class JProduct {
    private String image;
    private String title;
    private int price;
    private int purchased;
    private int sold;
}
