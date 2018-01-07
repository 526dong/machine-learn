package com.ccx.models.manager;

import com.ccx.models.Constants;
import com.ccx.models.bean.Base;
import com.ccx.models.bean.ExtractOutBean;
import com.ccx.models.bean.ProgramOutPutBean;
import com.ccx.models.listener.WatchDirsListener;
import com.ccx.models.mapper.dataexchange.DataExchangeMapper;
import com.ccx.models.message.MsgPush;
import com.ccx.models.model.ModelsProgram;
import com.ccx.models.model.datafile.ModelsDataFile;
import com.ccx.models.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.StandardWatchEventKinds;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description:manager接口调用相关处理
 * @author:lilong
 * @Date: 2017/11/21
 */
@Component
public class HttpDataExchangeManager {

    private  Logger log = LogManager.getLogger(this.getClass());

    public static Map<String,String> userReqIdMap=new TimerConcurrentHashMap<>(Constants.HOUR,100);
    public static Map<Integer,Queue<String>> userQueueMap=new TimerConcurrentHashMap<>(Constants.HOUR,100);

    @Autowired
    private DataExchangeMapper dataExchangeMapper;

    @Autowired
    private MsgPush push;

    /**
     * 变量分析-获取项目数据并传递给模型组
     * @param  file
     * @param  userName
     * @return
     */
    public String sendMsgToModel(ModelsDataFile file,String  userName){
        String json=null;
        ProgramOutPutBean bean=new ProgramOutPutBean();
        Base base = getBase(file,bean);
        bean.setType(0);
        bean.setReqId(file.getId()+"-"+System.currentTimeMillis()%1000000);
        bean.setUserPath(PropertiesUtil.getProperty("model_user_fileBasePath")+userName);
        userReqIdMap.put(bean.getReqId(),userName);
        log.info(file.getId()+",调用模型平台变量统计接口，参数为:"+JsonUtils.toJson(bean));
        json= httpjson(PropertiesUtil.getData("prop/resource-template.properties","model.http.url"),bean);
        log.info("模型平台变量统计返回数据，id,"+file.getId()+"结果:"+json);
        return json.replace("\\\\","/").replace("\"Null\"","null")
                .replace("//10.0.5.136/","d:/data/");
    }
    /**
     * 模型算法调用-获取项目数据并传递给模型组
     * @param  file
     * @param  program
     * @return
     */
    public String sendMsgToModel(ModelsDataFile file,ModelsProgram program,String  userName){
        String json=null;
        ProgramOutPutBean bean=new ProgramOutPutBean();
        Base base = getBase(file,bean);
        bean.setType(1);
        bean.setUserPath(PropertiesUtil.getProperty("model_user_fileBasePath")+program.getCreator());
        Integer md=program.getModelConf();
        base.setModelConf(md==0?"demo":(md==1?"speed":(md==2?"accuracy":"stable")));
        String[] sf=program.getArithmeticNames().split(",");
        final  List<Object> flag=new ArrayList<>();

        //每个请求的队列
        Queue<String> queue=new FunctionTimerQueue<>(Constants.MINUTE_10,(be)->{
            String js= httpjson(PropertiesUtil.getData("prop/resource-template.properties","model.http.url"),be);
            log.info("模型平台模型统计返回数据，结果:"+js);
            Map<String,Object> res=JsonUtils.fromJson(
                    js.replace("//10.0.5.136/","d:/data/")
                            .replace("\\\\","/"),Map.class);
           if (flag.size()==0){
               doLog((String)res.get("logPath"),String.valueOf(program.getId()),userName);
               flag.add(false);
           }
          return true;
        });
        userQueueMap.put(program.getId(),queue);
        for(String str:sf){
            if(!"".equals(str.trim())){
                base.setArithmetic(str);
                bean.setReqId(str+"-"+program.getId()+"-"+System.currentTimeMillis()%1000000);
                userReqIdMap.put(bean.getReqId(),userName);
                log.info(program.getId()+",算法，"+str+",调用模型平台模型统计接口，参数为:"+JsonUtils.toJson(bean));
                //请求放入队列
                queue.add(JsonUtils.toJson(bean));
            }
        }
        if(queue.size()>0) queue.poll();
        System.out.println("program.getCreateTime()：：：："+program.getCreateTime());
        sentTimeStart(System.currentTimeMillis()-program.getCreateTime().getTime()>1000*20?(System.currentTimeMillis()-1000*2):program.getCreateTime().getTime(),userName);
        return  "0000";
    }
    private void sentTimeStart(long timestam,String userName){
        Map<String,Object> send=new HashMap<>();
        send.put("iftimer",true);
        send.put("timestart",timestam);
        try {
            TimeUnit.SECONDS.sleep(2);
            push.sendMsgToUser(send, Constants.PUSH_CHANNEL_MODEL,(String) MsgPush.comet_map.get(userName));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * 日志处理
     * @param path
     * @param userName
     */
    private void doLog(String path,String projectId,String userName){
        new WatchDirsListener(path,projectId).listener(
                StandardWatchEventKinds.ENTRY_MODIFY,(line)->{
                    Map<String,Object> send=new HashMap<>();
                    send.put("iflog",true);
                    log.info(line);
                    send.put("data", line);
                    push.sendMsgToUser(send, Constants.PUSH_CHANNEL_MODEL,(String) MsgPush.comet_map.get(userName));
            return null;
        });
    }
    //基础信息获取
    private Base getBase(ModelsDataFile file, ProgramOutPutBean bean){
        Base base = new Base(file.getId(),file.getName(),null,null);
        BeanUtils.copyProperties(file,base);
        base.setFileType(file.getType());
        base.setFielDelimiter(file.getFileDelimiter());
        bean.setBase(base);
        bean.setFields(dataExchangeMapper.selectModelFieldData(file.getId()));
        return base;
    }
    /**
     * 模型提取
     * @param file
     * @param program
     * @param bean
     * @return
     */
    public String sendMsgToModel(ModelsDataFile file,ModelsProgram program,ExtractOutBean bean,String firlUrl){
        Base base = bean.getBase();
        BeanUtils.copyProperties(file,base);
        base.setFileType(file.getType());
        base.setFielDelimiter(file.getFileDelimiter());
        base.setFileUrl(firlUrl);
        Integer md=program.getModelConf();
        base.setModelConf(md==0?"demo":(md==1?"speed":(md==2?"accuracy":"stable")));
        log.info(bean.getReqId()+",调用模型平台提取接口，参数为:"+JsonUtils.toJson(bean));
        String json= httpjson(PropertiesUtil.getData("prop/resource-template.properties","model.http.contract.url"),bean);
        log.info("模型平台提取返回数据，id,"+bean.getReqId()+"结果:"+json);
        return json;
    }
    /**
     *调用模型组Http服务
     * @param url
     * @param data
     * @return
     */
    public  String httpjson(String url,Object data) {
        Map<String, Object> map = new HashMap<>();
        String res="";
        try {
            /*String account="";
            String reqTid="";
            String privateKey="";
            String sign=getSisn(account,reqTid,privateKey);
            Map<String,Object> passData=new HashMap<>();
            passData.put("account",account);
            passData.put("reqTid",reqTid);
            passData.put("data",data);
            passData.put("sign",sign);*/
            String json=(data instanceof String)? data.toString():JsonUtils.toJson(data);
            json=json.replace("d:/data/","//10.0.5.136/");
            res= HttpClientUtil.sendPostSSLRequest(url,
                   json, "UTF-8","application/json");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            log.info("调用模型服务，url:"+url+",参数"+JsonUtils.toJson(data));
        }
        return res;
    }

    /**
     * 获取签名
     * @param account
     * @param reqTid
     * @param privateKey
     * @return
     */
    private  String getSisn(String account,String reqTid,String privateKey){
        Map<String, String> parammap = new HashMap<String, String>();
        parammap.put("account",account);
        parammap.put("reqTid",reqTid);
        return SignTools.getSignature((HashMap<String, String>) parammap,privateKey);
    }

    //数据存储抽象类
    public static abstract class Save<E,M> {

        public abstract void insert(E info, M mapper);

        public  void delete(Integer dataFileId, M mapper){
            //重写
        }

        public void insertBatch(List<E> e, M m) {
            for (E str : e) {
                insert(str, m);
            }
        }

        public void insertBatch(List<E> e, M m, Integer dataFileId) {
            if (dataFileId != null) {
                delete(dataFileId, m);
            }

            for (E str : e) {
                insert(str, m);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("sdffd\r\n".split(""));
    }
}
