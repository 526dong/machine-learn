package com.ccx.models.api.dataexchange;

import com.ccx.models.bean.RecivePutOutBean;
import com.ccx.models.model.ModelsProgram;

import java.util.Map;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/11/21
 */
public interface PutOutExchangeService {
    Map<String,Object> sendTOModel(ModelsProgram program,String userName);

    void saveOutPut(RecivePutOutBean putOutBean,Map<String,Object> data);

    void updateStatus(ModelsProgram np);
}
