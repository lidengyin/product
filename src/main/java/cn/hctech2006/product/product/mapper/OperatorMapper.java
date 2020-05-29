package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.bean.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OperatorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Operator record);

    Operator selectByPrimaryKey(Long id);

    List<Operator> selectAll();

    int updateByPrimaryKey(Operator record);

    Operator selectByUsername(String username);
    Operator selectByOperatorId(String operatorId);
}