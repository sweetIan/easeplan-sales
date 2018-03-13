package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * 内容简介
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class ItemAbstract {
    private String id;
    private String title;
    private long price;
    private String image;
    private int sold;
}
