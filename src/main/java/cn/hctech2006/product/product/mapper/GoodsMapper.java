package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Long id);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);
    int checkByGoodsId(String goodsId);
    int updateByGoodsId(Goods goods);
    int updateAvailable(String goodsId);

    List<Goods> selectByNameAndShorts(Goods goods);
    Goods selectByGoodsId(String goodsId);
}