package cn.hctech2006.product.product.service;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.common.ServerResponse;

public interface IGoodsService {
    public ServerResponse selectAll();
    public ServerResponse insertOrUpdate(Goods goods);
    public ServerResponse updateAvailable(String goodsId);
    public ServerResponse selectByNameORShorts(Goods goods);
    public Goods selectByGoodsId(String goodsId);
}
