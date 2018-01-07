package com.ccx.models.dataexchange;

import com.ccx.models.Constants;
import com.ccx.models.annotation.CheckHttpRequst;
import com.ccx.models.api.dataexchange.ModelExtractService;
import com.ccx.models.api.dataexchange.VariableExchangeService;
import com.ccx.models.bean.MsgBean;
import com.ccx.models.bean.RecivePutOutBean;
import com.ccx.models.message.MsgPush;
import com.ccx.models.model.ModelsExtractTestRecord;
import com.ccx.models.model.ModelsProgram;
import com.ccx.models.service.impl.dataexchange.PutOutExchangeServiceImpl;
import com.ccx.models.util.ControllerUtil;
import com.ccx.models.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/11/21
 */
@Controller
@RequestMapping("/")
public class DataexchangeController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PutOutExchangeServiceImpl putOutExchangeService;

    @Autowired
    private VariableExchangeService variableExchangeService;

    @Autowired
    private ModelExtractService modelExtractService;
    /**
     * 变量统计
     * @param request
     * @param bean
     * @return
     */
    @PostMapping("/variable/api")
    @ResponseBody
    @CheckHttpRequst(type="ip")
    public Map<String,Object> statistics(HttpServletRequest request, @RequestBody String bean){
        Map<String,Object> map = new HashMap<>();
        try{
            variableExchangeService.saveData(JsonUtils.fromJson(bean,RecivePutOutBean.class),
                    null);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("变量统计信息存储失败，数据为"+bean);
            map.put("resCode","9999");
            map.put("resMsg","系统异常");
            return  map;
        }
        map.put("resCode","0000");
        map.put("resMsg","成功");
        return map;
    }

    /**
     * 模型项目输出结果
     * @param request
     * @param bean
     * @return
     */
    @PostMapping("/output/api")
    @ResponseBody
    @CheckHttpRequst(type="ip")
    public Map<String,Object> mdResult(HttpServletRequest request, @RequestBody String bean){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        RecivePutOutBean putOutBean=null;
        try{
            bean=bean.replace("\\","/").replace("//10.0.5.136/","d:/data/");
            logger.info("模型回调返回结果为："+bean);
            putOutBean= JsonUtils.fromJson(bean,RecivePutOutBean.class);
            putOutExchangeService.saveOutPut(putOutBean,data);
        }catch (Exception e){
            e.printStackTrace();
            Integer programId=Integer.valueOf(putOutBean.getReqId().split("-")[1]);
            putOutExchangeService.updateStatus(new ModelsProgram(programId,new Date(),null,(short)3));
            logger.info("模型项目输出存储失败，数据为"+bean,e);
            map.put("resCode","9999");
            map.put("resMsg","系统异常");

            return  map;
        }
        map.put("resCode","0000");
        map.put("resMsg","成功");
        return map;
    }
   /* @RequestMapping("/variable/api")
    public Map<String,Object> test(HttpServletRequest request,Integer id){
        ModelsExtractTestRecord file =new ModelsExtractTestRecord();
        file.setModelsExtractInfoId(id.longValue());
        file.setFileName("xxxx");
        file.setId(1l);
        file.setFilePath("//10.0.5.136/model/lilong/base14_1130 - 1111.csv");
        modelExtractService.saveForModelSend(file,"zhagnsan");
       return null;
    }*/
}
