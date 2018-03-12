package easeplan.netease.sales.mapper;

import easeplan.netease.sales.domain.ItemAbstract;
import easeplan.netease.sales.domain.ItemDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/12</pre>
 */
public interface ItemMapper {
    @Update({
            "create table if not exists t_item",
            "(id integer auto_increment primary key,",
            "title varchar(80) not null, price integer not null, image varchar(200) not null,",
            "summary varchar(140), detail varchar(1000), sold integer not null default 0)",
            "AUTO_INCREMENT = 1000"
    })
    void schema();


    @Select("select id, title, price, image, sold from t_item")
    List<ItemAbstract> getAbstractListAll();

    @Select("select id, title, price, image, sold from t_item where sold = 0")
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

    @Delete({"delete from t_item where id = #{param1} and sold = 0"})
    int delete(int id);
}
