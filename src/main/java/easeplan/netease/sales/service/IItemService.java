package easeplan.netease.sales.service;

import easeplan.netease.sales.domain.ItemAbstract;
import easeplan.netease.sales.domain.ItemDetail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容服务
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/12</pre>
 */
@Service
public interface IItemService {
    /**
     * 获取所有内容
     * @return
     */
    List<ItemAbstract> getItemAbstractListAll();

    /**
     * 获取所有没有售出/购买过的内容
     * @return
     */
    List<ItemAbstract> getItemAbstractListUnsold();

    /**
     * 获取内容详情
     * @param id
     * @return
     */
    ItemDetail getItemDetail(int id);

    /**
     * 新建内容
     * @param itemDetail
     * @return
     */
    int newItem(ItemDetail itemDetail);

    /**
     * 修改内容
     * @param id
     * @param itemDetail
     */
    void updateItem(int id, ItemDetail itemDetail);

    /**
     * 删除内容
     * @param id
     * @return 删除的条数
     */
    void deleteItem(int id);
}
