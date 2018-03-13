package easeplan.netease.sales;

import easeplan.netease.sales.mapper.CartItemMapper;
import easeplan.netease.sales.mapper.ItemMapper;
import easeplan.netease.sales.mapper.PurchasedItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/12</pre>
 */
@Component
public class DbInitRunner implements CommandLineRunner {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    PurchasedItemMapper purchasedItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(DbInitRunner.class);
    @Override
    public void run(String...args) throws Exception {
        logger.info("Initializing DB tables...");
        itemMapper.schema();
        cartItemMapper.schema();
        purchasedItemMapper.schema();
    }
}