package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.bean.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    Customer selectByPrimaryKey(String  customerId);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    int selectCountByName(String name);
    List<Customer> selectByName(String name);
    int checkByCustomerId(String customerId);

    int updateAvailable(String customerId);

    List<Customer> selectByNameOrShorts(Customer customer);
}