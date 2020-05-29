package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.bean.SaleBack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SaleBackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleBack record);

    SaleBack selectByPrimaryKey(Long id);

    List<SaleBack> selectAll();

    int updateByPrimaryKey(SaleBack record);
}