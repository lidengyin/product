package cn.hctech2006.product.product.mapper;

import cn.hctech2006.product.product.bean.Provider;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProviderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Provider record);

    Provider selectByPrimaryKey(Long id);

    List<Provider> selectAll();

    int updateByPrimaryKey(Provider record);

    int checkByProviderId(String providerId);

    int updateByProviderId(Provider provider);

    int updateAvailable(String providerId);

    List<Provider> selectByNameOrShorts(Provider provider);
    Provider selectByProviderId(String providerId);
}