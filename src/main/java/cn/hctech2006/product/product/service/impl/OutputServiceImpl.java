package cn.hctech2006.product.product.service.impl;

import cn.hctech2006.product.product.DO.InputDO;
import cn.hctech2006.product.product.DO.OutputDO;
import cn.hctech2006.product.product.bean.Input;
import cn.hctech2006.product.product.bean.Output;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.mapper.InputMapper;
import cn.hctech2006.product.product.mapper.OutputMapper;
import cn.hctech2006.product.product.service.IInputService;
import cn.hctech2006.product.product.service.IOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OutputServiceImpl implements IOutputService {
    @Autowired
    private OutputMapper outputMapper;
    @Override
    public ServerResponse insertOrUpdateOutput(List<Output> outputs) {
        int count = 0;

        try{
            for(Output output:outputs){
                Output outputAlready = outputMapper.checkByOutputIdAndGoodsId(output.getOutportId(), output.getGoodsId());
                if (outputAlready != null){
                    output.setNumber(output.getNumber()+outputAlready.getNumber());
                    count = outputMapper.updateByOutputIdAndGoodsId(output);

                }else{
                    count = outputMapper.insert(output);
                }
            }
            return ServerResponse.createBySuccess("添加或修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByError("添加或修改失败");
        }

    }

    @Override
    public List<Output> selectAll() {
        return outputMapper.selectAll();
    }

    @Override
    public List<Output> selectOutputBykeyWord(OutputDO outputDO) {
        return outputMapper.selectOutputBykeyWord(outputDO);
    }
}
