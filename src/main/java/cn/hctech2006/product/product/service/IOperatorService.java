package cn.hctech2006.product.product.service;

import cn.hctech2006.product.product.bean.Operator;
import cn.hctech2006.product.product.common.ServerResponse;

import java.util.List;

public interface IOperatorService {
    public ServerResponse selectByUsernameAndPassword(String username, String password);
    public List<Operator> selectAll();
    public Operator selectByOperatorId(String operatorId);

}
