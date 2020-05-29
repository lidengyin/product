package cn.hctech2006.product.product.service.impl;

import cn.hctech2006.product.product.bean.Operator;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.mapper.OperatorMapper;
import cn.hctech2006.product.product.service.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements IOperatorService {
    @Autowired
    private OperatorMapper operatorMapper;

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    public ServerResponse selectByUsernameAndPassword(String username, String password){

        Operator operator = operatorMapper.selectByUsername(username);
        if (operator != null && operator.getPassword().equals(password)){
            return ServerResponse.createBySuccess("登录成功",operator);
        }
        return ServerResponse.createByError("用户不存在或密码错误");
    }

    @Override
    public List<Operator> selectAll() {
        return operatorMapper.selectAll();
    }

    @Override
    public Operator selectByOperatorId(String operatorId) {
        return operatorMapper.selectByOperatorId(operatorId);
    }

}
