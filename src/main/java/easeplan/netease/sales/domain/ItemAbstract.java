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

    public static ItemAbstract sample() {
        ItemAbstract product = new ItemAbstract();
        product.setId("ID123");
        product.setImage("https://www.google.co.jp//images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");
        product.setTitle("商品1~");
        product.setPrice(123);
        product.setSold(10);
        return product;
    }
}
