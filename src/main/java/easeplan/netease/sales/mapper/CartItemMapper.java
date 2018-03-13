package easeplan.netease.sales.mapper;

import easeplan.netease.sales.domain.CartItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 购物车记录
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
public interface CartItemMapper {
    @Update({
            "create table if not exists t_cart_item",
            "(id integer primary key,",
            "amount integer not null)"
    })
    void schema();

    @Select({
            "select a.id as id, title, price, amount",
            "from t_cart_item as a join t_item as b on a.id = b.id"
    })
    List<CartItem> getCartItemList();

    @Select("select * from t_cart_item where id = #{param1}")
    CartItem getCartItem(int id);

    @Insert("insert into t_cart_item (id, amount) values (#{param1}, 1)")
    int insertItem(int id);

    @Update("update t_cart_item set amount = #{param2} where id = #{param1}")
    int updateItemAmount(int id, int amount);

    @Delete("delete from t_cart_item where id = #{param1}")
    int deleteItem(int id);

    @Select("select count(*) from t_cart_item where not exists (select * from t_item where t_cart_item.id = t_item.id)")
    int getDeletedItemCount();

    @Delete("delete from t_cart_item where not exists (select * from t_item where t_cart_item.id = t_item.id)")
    void deleteDeletedItems();

    @Delete("delete from t_cart_item")
    void clear();
}
