package easeplan.netease.sales.domain;

import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class ItemDetail {
    private Integer id;
    private String title;
    private int price;
    private String image;
    private String summary;
    private String detail;
    private int sold;

    public static ItemDetail sample() {
        ItemDetail product = new ItemDetail();
        product.setId(1000);
        product.setImage("https://www.google.co.jp//images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");
        product.setTitle("商品1~");
        product.setSummary("abstract...");
        product.setDetail("detail...");
        product.setPrice(123);
        product.setSold(15);
        return product;
    }
}
