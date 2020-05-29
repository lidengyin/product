package cn.hctech2006.product.product.service;

import cn.hctech2006.product.product.DO.InputDO;
import cn.hctech2006.product.product.bean.Input;
import cn.hctech2006.product.product.common.ServerResponse;

import java.util.List;

public interface IInputService {
    ServerResponse insertOrUpdateInput(List<Input> inputs);
    List<Input> selectAll();
    List<Input> selectInputBykeyWord(InputDO inputDO);
}
