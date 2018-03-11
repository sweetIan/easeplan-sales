package easeplan.netease.sales.json;

import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
public class JProduct {
    private String id;
    private String image;
    private String title;
    private String summary;
    private String detail;
    private String price;
    private int purchased;
    private int sold;

    private static String formatPrice(int cent) {
        StringBuilder priceBuilder = new StringBuilder();
        priceBuilder.append(cent / 100).append(".").append(cent % 100);
        return priceBuilder.toString();
    }

    public static JProduct sample() {
        JProduct product = new JProduct();
        product.setId("ID123");
        product.setImage("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=983430254,4267619461&fm=173&app=12&f=JPEG?w=218&h=146&s=CFFA29C548730486D7A0A4DA0300D097");
        product.setTitle("商品1~");
        product.setSummary("abstract...");
        product.setDetail("detail...");
        product.setPrice("1.23");
        product.setPurchased(5);
        product.setSold(5);
        return product;
    }
}
