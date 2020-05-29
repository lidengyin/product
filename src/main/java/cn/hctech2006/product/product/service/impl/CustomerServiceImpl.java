package cn.hctech2006.product.product.service.impl;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.mapper.CustomerMapper;
import cn.hctech2006.product.product.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.print.CUPSPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 插入或者修改用户
     * @param customer
     * @return
     */
    public ServerResponse insertOrUpdate(Customer customer){
        int count = 0;
        int hasAlready=0;
        //判断是注册还是更新
        if (customer.getCustomerId() != null){
            //判断该编号客户是否存在
            hasAlready = customerMapper.checkByCustomerId(customer.getCustomerId());
            if (hasAlready<=0){
                return ServerResponse.createByError("需要修改的用户不存在");
            }
            count = customerMapper.updateByPrimaryKey(customer);
        }else{
            //设置客户编号
            customer.setCustomerId(UUID.randomUUID().toString());
            //判断客户是否已经注册
            hasAlready = customerMapper.selectCountByName(customer.getName());
            if (hasAlready > 0){
                return ServerResponse.createBySuccess("客户已经注册");
            }
            count = customerMapper.insert(customer);
        }
        if (count > 0){
            return ServerResponse.createBySuccess("客户添加/修改成功");
        }
        return ServerResponse.createByError("客户添加/修改失败");
    }

    /**
     * 根据用户名查询客户列表
     * @param name
     * @return
     */
    public ServerResponse selectByName(String name){
        List<Customer> customerList = customerMapper.selectByName(name);
        return ServerResponse.createBySuccess("查询成功",customerList);
    }

    /**
     * 获取全部用户列表
     * @return
     */
    public ServerResponse selectAll(){
        List<Customer> customerList = customerMapper.selectAll();
        if (customerList ==null){

            return ServerResponse.createBySuccess(new ArrayList<Customer>());
        }
        return ServerResponse.createBySuccess(customerList);
    }

    public ServerResponse selectByCustomerId(String customerId){
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return ServerResponse.createBySuccess(customer);
    }
    public ServerResponse updateAvailable(String customerId){
        int count = customerMapper.updateAvailable(customerId);
        if (count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByError("删除失败");
    }

    @Override
    public ServerResponse selectByNameORShorts(Customer customer) {
        List<Customer> customerList = customerMapper.selectByNameOrShorts(customer);
        return ServerResponse.createBySuccess(customerList);
    }
}
