package easeplan.netease.sales.service.implementation;

import easeplan.netease.sales.domain.ItemAbstract;
import easeplan.netease.sales.domain.ItemDetail;
import easeplan.netease.sales.exception.BadItemRequestException;
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
        itemMapper.delete(id);
    }

    /**
     * 验证是否符合格式要求
     *
     * @param itemDetail
     */
    private void verify(ItemDetail itemDetail) {
        String title = itemDetail.getTitle();
        String image = itemDetail.getImage();
        int price = itemDetail.getPrice();
        String summary = itemDetail.getSummary();
        String detail = itemDetail.getDetail();

        if (price < 0
                || title == null || title.length() < 2 || title.length() > 80
                || image == null || !image.matches("\\.(jpg|gif|png)$")
                || summary == null || summary.length() < 2 || summary.length() > 140
                || detail == null || detail.length() < 2 || detail.length() > 1000) {
            throw new BadItemRequestException();
        }
    }
}
