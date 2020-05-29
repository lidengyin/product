package cn.hctech2006.product.product.service.impl;

import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.mapper.ProviderMapper;
import cn.hctech2006.product.product.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ProviderServiceImpl implements IProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public ServerResponse selectAll() {
        List<Provider> providerList = providerMapper.selectAll();
        return ServerResponse.createBySuccess(providerList);
    }

    @Override
    public ServerResponse insertOrUpdate(Provider provider) {
        int hasAlready = 0;
        int count = 0;
        //注册还是更新
        if (provider.getProviderId() != null){
            hasAlready = providerMapper.checkByProviderId(provider.getProviderId());
            //判断需要更新的商品是否存在
            if (hasAlready<=0){
                return ServerResponse.createByError("需要修改的供应商不存在");
            }
            count = providerMapper.updateByProviderId(provider);
            //判断商品修改是否成功
            if (count > 0){
                return ServerResponse.createBySuccess("修改成功");
            }
            return ServerResponse.createByError("修改失败");
        }else {
            //注册
            provider.setProviderId(UUID.randomUUID().toString());
            count= providerMapper.insert(provider);
            if (count > 0){
                return ServerResponse.createBySuccess("供应商添加成功");
            }
            return ServerResponse.createByError("供应商添加失败");
        }
    }

    @Override
    public ServerResponse updateAvailable(String providerId) {
        int count = providerMapper.updateAvailable(providerId);
        if (count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByError("删除失败");
    }

    @Override
    public ServerResponse selectByNameORShorts(Provider provider) {
        List<Provider> providerList = providerMapper.selectByNameOrShorts(provider);
        return ServerResponse.createBySuccess(providerList);
    }

    @Override
    public Provider selectByProviderId(String providerId) {
        return providerMapper.selectByProviderId(providerId);
    }
}
