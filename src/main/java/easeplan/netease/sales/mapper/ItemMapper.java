package easeplan.netease.sales.mapper;

import easeplan.netease.sales.domain.ItemAbstract;
import easeplan.netease.sales.domain.ItemDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 内容
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/12</pre>
 */
public interface ItemMapper {
    @Update({
            "create table if not exists t_item",
            "(id integer auto_increment primary key,",
            "title varchar(80) not null, price bigint not null, image varchar(200) not null,",
            "summary varchar(140), detail varchar(1000))",
            "AUTO_INCREMENT = 1000"
    })
    void schema();


    @Select("select a.id as id, title, price, image, coalesce(sum(b.purchase_amount), 0) as sold from t_item as a left outer join t_purchased_item as b on a.id = b.id group by a.id")
    List<ItemAbstract> getAbstractListAll();

    @Select("select a.id as id, title, price, image, coalesce(sum(b.purchase_amount), 0) as sold from t_item as a left outer join t_purchased_item as b on a.id = b.id group by a.id having sold = 0")
    List<ItemAbstract> getAbstractListUnsold();

    @Select("select * from t_item where id = #{param1}")
    ItemDetail getDetailById(int id);

    @Insert({
            "insert into t_item (title, price, image, summary, detail)",
            "values (#{title}, #{price}, #{image}, #{summary}, #{detail})"
    })
    @Options(useGeneratedKeys = true)
    int insert(ItemDetail itemDetail);

    @Update({
            "update t_item",
            "set title = #{title},",
            "price = #{price},",
            "image = #{image},",
            "summary = #{summary},",
            "detail = #{detail}",
            "where id = #{id}"
    })
    int update(ItemDetail itemDetail);

    @Delete({"delete from t_item where id = #{param1}"})
    void delete(int id);

    @Select("select count(*) from t_item")
    int count();
}
