package easeplan.netease.sales.mapper;

import easeplan.netease.sales.domain.PurchasedItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
public interface PurchasedItemMapper {
    @Update({
            "create table if not exists t_purchased_item",
            "(id integer, purchase_date bigint, purchase_price integer, purchase_amount integer,",
            "primary key(id, purchase_date))"
    })
    void schema();

    @Select({
            "select a.id as id, image, title, purchase_date as purchaseDate, purchase_price as purchasePrice, purchase_amount as purchaseAmount",
            "from t_purchased_item as a join t_item as b on a.id = b.id"
    })
    List<PurchasedItem> getPurchasedItemList();

    @Insert({
            "<script>",
            "insert into t_purchased_item",
            "(id, purchase_date, purchase_price, purchase_amount)",
            "values",
            "<foreach item='purchasedItem' collection='list' separator=','>",
            "(#{purchasedItem.id}, #{purchasedItem.purchaseDate}, #{purchasedItem.purchasePrice}, #{purchasedItem.purchaseAmount})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(List<PurchasedItem> purchasedItems);

    @Select({
            "select a.id as id, image, title, purchase_date as purchaseDate, purchase_price as purchasePrice, purchase_amount as purchaseAmount",
            "from t_purchased_item as a join t_item as b on a.id = b.id",
            "where a.id = #{param1}"
    })
    List<PurchasedItem> getPurchasedHistoryById(int id);
}
