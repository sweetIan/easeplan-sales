package easeplan.netease.sales.service;

import easeplan.netease.sales.domain.PurchasedItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Service
public interface IBalanceService {
    /**
     * 购买购物车内的所有内容
     * @return
     */
    void purchase();

    /**
     * 获取所有历史购买记录
     * @return
     */
    List<PurchasedItem> getPurchasedItemList();
}
