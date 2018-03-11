package easeplan.netease.sales.json;

import lombok.Data;

import java.util.Date;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class PurchasedItem {
    private String id;
    private String image;
    private String title;
    private Date boughtDate;
    private String boughtPrice;
    private int boughtAmount;

    public static PurchasedItem sample() {
        PurchasedItem product = new PurchasedItem();
        product.setId("ID123");
        product.setImage("https://www.google.co.jp//images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");
        product.setTitle("商品1~");
        product.setBoughtDate(new Date());
        product.setBoughtPrice("18.50");
        product.setBoughtAmount(18);
        return product;
    }
}
