package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.bean.Sale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SaleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sale record);

    Sale selectByPrimaryKey(Long id);

    List<Sale> selectAll();

    int updateByPrimaryKey(Sale record);
}