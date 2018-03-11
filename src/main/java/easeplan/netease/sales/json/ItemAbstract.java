package easeplan.netease.sales.json;

import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class ItemAbstract {
    private String id;
    private String image;
    private String title;
    private String price;

    private int purchased;
    private int sold;

    public static ItemAbstract sample() {
        ItemAbstract product = new ItemAbstract();
        product.setId("ID123");
        product.setImage("https://www.google.co.jp//images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");
        product.setTitle("商品1~");
        product.setPrice("1.23");
        product.setPurchased(10);
        product.setSold(10);
        return product;
    }
}
