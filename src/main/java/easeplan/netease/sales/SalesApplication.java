package easeplan.netease.sales;

import easeplan.netease.sales.mapper.CartItemMapper;
import easeplan.netease.sales.mapper.ItemMapper;
import easeplan.netease.sales.mapper.PurchasedItemMapper;
import easeplan.netease.sales.service.IStorageService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("easeplan.netease.sales.mapper")
public class SalesApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SalesApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

    @Bean
    CommandLineRunner init(IStorageService storageService,
                           ItemMapper itemMapper,
                           CartItemMapper cartItemMapper,
                           PurchasedItemMapper purchasedItemMapper) {
        return (args) -> {
            LOGGER.info("Initializing DB schemas and picture storage path...");
            itemMapper.schema();
            cartItemMapper.schema();
            purchasedItemMapper.schema();
            storageService.init();
        };
    }
}
