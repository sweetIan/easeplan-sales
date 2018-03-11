package easeplan.netease.sales.json;

import lombok.Data;

import java.util.Date;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class ItemDetail {
    private String id;
    private String image;
    private String title;
    private String summary;
    private String detail;
    private String price;

    private int purchased;
    private int sold;

    private Date boughtDate;
    private String boughtPrice;
    private String boughtAmount;

    public static ItemDetail sample() {
        ItemDetail product = new ItemDetail();
        product.setId("ID123");
        product.setImage("https://www.google.co.jp//images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");
        product.setTitle("商品1~");
        product.setSummary("abstract...");
        product.setDetail("detail...");
        product.setPrice("1.23");
        return product;
    }
}
