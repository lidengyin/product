package cn.hctech2006.product.product.service;

import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.common.ServerResponse;

import java.util.List;

public interface IProviderService {
    public ServerResponse selectAll();
    public ServerResponse insertOrUpdate(Provider provider);
    public ServerResponse updateAvailable(String providerId);
    public ServerResponse selectByNameORShorts(Provider provider);
    public Provider selectByProviderId(String providerId);
}
