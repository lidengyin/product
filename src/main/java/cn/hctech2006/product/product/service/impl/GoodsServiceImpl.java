package cn.hctech2006.product.product.service.impl;

import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.mapper.GoodsMapper;
import cn.hctech2006.product.product.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public ServerResponse selectAll() {
        List<Goods> goodsList =  goodsMapper.selectAll();
        return ServerResponse.createBySuccess(goodsList);
    }

    @Override
    public ServerResponse insertOrUpdate(Goods goods) {
        int hasAlready = 0;
        int count = 0;
        //注册还是更新
        if (goods.getGoodsId() != null){
            hasAlready = goodsMapper.checkByGoodsId(goods.getGoodsId());
            //判断需要更新的商品是否存在
            if (hasAlready<=0){
                return ServerResponse.createByError("需要修改的商品不存在");
            }
            count = goodsMapper.updateByGoodsId(goods);
            //判断商品修改是否成功
            if (count > 0){
                return ServerResponse.createBySuccess("修改成功");
            }
            return ServerResponse.createByError("修改失败");
        }else {
            //注册
            goods.setGoodsId(UUID.randomUUID().toString());
            count=goodsMapper.insert(goods);
            if (count > 0){
                return ServerResponse.createBySuccess("商品添加成功");
            }
            return ServerResponse.createByError("商品添加失败");
        }
    }

    @Override
    public ServerResponse updateAvailable(String goodsId) {
        int count = goodsMapper.updateAvailable(goodsId);
        if (count>0){
            return ServerResponse.createBySuccess("商品删除成功");
        }
        return ServerResponse.createByError("商品删除失败");
    }

    @Override
    public ServerResponse selectByNameORShorts(Goods goods) {
        List<Goods> goodsList = goodsMapper.selectByNameAndShorts(goods);
        return ServerResponse.createBySuccess(goodsList);
    }

    @Override
    public Goods selectByGoodsId(String goodsId) {
        return goodsMapper.selectByGoodsId(goodsId);
    }
}
