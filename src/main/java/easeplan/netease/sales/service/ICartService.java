package easeplan.netease.sales.service;

import easeplan.netease.sales.domain.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车服务
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Service
public interface ICartService {
    /**
     * 获取所有购物车内容
     *
     * @return
     */
    List<CartItem> getCartItemList();

    /**
     * 向购物车中添加一件指定内容
     *
     * @param id
     */
    void addItem(int id);

    /**
     * 调整购物车中指定内容的数量
     *
     * @param id
     * @param amount
     */
    void updateItemAmount(int id, int amount);

    /**
     * 删除购物车中的指定内容
     *
     * @param id
     */
    void deleteItem(int id);

    /**
     * 购物车中已经失效的内容条数
     *
     * @return 删除的内容条数
     */
    int deleteDeletedItems();
}
