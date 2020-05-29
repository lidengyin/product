package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.DO.OutputDO;
import cn.hctech2006.product.product.bean.Output;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OutputMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Output record);

    Output selectByPrimaryKey(Long id);

    List<Output> selectAll();

    int updateByPrimaryKey(Output record);
    Output checkByOutputIdAndGoodsId(String outputId, String goodsId);
    int updateByOutputIdAndGoodsId(Output output);
    List<Output> selectOutputBykeyWord(OutputDO outputDO);
}