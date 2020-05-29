package cn.hctech2006.product.product.service;

import cn.hctech2006.product.product.DO.OutputDO;
import cn.hctech2006.product.product.bean.Output;
import cn.hctech2006.product.product.common.ServerResponse;

import java.util.List;

public interface IOutputService {
    ServerResponse insertOrUpdateOutput(List<Output> outputs);
    List<Output> selectAll();
    List<Output> selectOutputBykeyWord(OutputDO outputDO);
}
