package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * 内容详情
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class ItemDetail {
    private Integer id;
    private String title;
    private long price;
    private String image;
    private String summary;
    private String detail;
    private int sold;
}
