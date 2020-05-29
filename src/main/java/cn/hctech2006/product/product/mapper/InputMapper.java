package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.DO.InputDO;
import cn.hctech2006.product.product.bean.Input;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface InputMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Input record);

    Input selectByPrimaryKey(Long id);

    List<Input> selectAll();

    int updateByPrimaryKey(Input record);

    Input checkByInputIdAndGoodsId(String inputId,String goodsId);

    int updateByInputIdAndGoodsId(Input input);

    List<Input> selectInputBykeyWord(InputDO inputDO);
}