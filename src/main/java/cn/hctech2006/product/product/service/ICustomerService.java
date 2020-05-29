package cn.hctech2006.product.product.service;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.common.ServerResponse;

public interface ICustomerService {
    public ServerResponse selectAll();
    public ServerResponse insertOrUpdate(Customer customer);
    public ServerResponse updateAvailable(String customerId);
    public ServerResponse selectByNameORShorts(Customer customer);
}
