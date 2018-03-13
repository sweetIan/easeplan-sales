package easeplan.netease.sales.service.implementation;

import easeplan.netease.sales.domain.ItemAbstract;
import easeplan.netease.sales.domain.ItemDetail;
import easeplan.netease.sales.mapper.ItemMapper;
import easeplan.netease.sales.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/12</pre>
 */
@Component
public class ItemService implements IItemService {
    @Autowired
    ItemMapper itemMapper;

    @Override
    public List<ItemAbstract> getItemAbstractListAll() {
        return itemMapper.getAbstractListAll();
    }

    @Override
    public List<ItemAbstract> getItemAbstractListUnsold() {
        return itemMapper.getAbstractListUnsold();
    }

    @Override
    public ItemDetail getItemDetail(int id) {
        return itemMapper.getDetailById(id);
    }

    @Override
    public int newItem(ItemDetail itemDetail) {
        verify(itemDetail);
        itemMapper.insert(itemDetail);
        return itemDetail.getId();
    }

    @Override
    public void updateItem(int id, ItemDetail itemDetail) {
        verify(itemDetail);
        itemDetail.setId(id);
        itemMapper.update(itemDetail);
    }

    @Override
    public void deleteItem(int id) {
        if (itemMapper.delete(id) == 0) {
            //todo
            throw new RuntimeException();
        }
    }

    /**
     * 验证是否符合格式要求
     * @param itemDetail
     */
    private void verify(ItemDetail itemDetail) {
        //todo
    }
}
