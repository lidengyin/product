package cn.hctech2006.product.product.service.impl;

import cn.hctech2006.product.product.DO.InputDO;
import cn.hctech2006.product.product.bean.Input;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.mapper.InputMapper;
import cn.hctech2006.product.product.service.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InputServiceImpl implements IInputService {
    @Autowired
    private InputMapper inputMapper;
    @Override
    public ServerResponse insertOrUpdateInput(List<Input> inputs) {
        int count = 0;

        try{
            for(Input input:inputs){
                Input inputAlready = inputMapper.checkByInputIdAndGoodsId(input.getInputId(), input.getGoodsId());
                if (inputAlready != null){
                    input.setNumber(input.getNumber()+inputAlready.getNumber());
                    count = inputMapper.updateByInputIdAndGoodsId(input);

                }else{
                    count = inputMapper.insert(input);
                }
            }
            return ServerResponse.createBySuccess("添加或修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByError("添加或修改失败");
        }

    }

    @Override
    public List<Input> selectAll() {
        return inputMapper.selectAll();
    }

    @Override
    public List<Input> selectInputBykeyWord(InputDO inputDO) {
        return inputMapper.selectInputBykeyWord(inputDO);
    }
}
