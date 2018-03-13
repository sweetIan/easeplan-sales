package easeplan.netease.sales.util;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/11</pre>
 */
public class PriceUtil {
    private PriceUtil() {
    }

    public static String formatPrice(int cent) {
        StringBuilder priceBuilder = new StringBuilder();
        priceBuilder.append(cent / 100).append(".").append(cent % 100);
        return priceBuilder.toString();
    }
}
