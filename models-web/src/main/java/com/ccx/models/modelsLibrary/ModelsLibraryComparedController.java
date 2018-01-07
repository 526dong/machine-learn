package com.ccx.models.modelsLibrary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccx.models.api.laboratory.ModelsReportApi;
import com.ccx.models.api.modelsLibrary.ModelsLibraryApi;
import com.ccx.models.base.BaseController;
import com.ccx.models.model.ModelsExtract;
import com.ccx.models.util.ControllerUtil;
import com.ccx.models.util.UsedUtil;
import com.github.pagehelper.PageHelper;

/**
 * 模型对比
 */
@Controller
@RequestMapping("/modelsCompared")
public class ModelsLibraryComparedController extends BaseController {

	private static Logger logger = LogManager.getLogger(ModelsLibraryComparedController.class);

	@Autowired
    private ModelsLibraryApi modelsLibraryApi;

	@Autowired
	private ModelsReportApi modelsReportApi;


    /**
     * @Description: 跳转至模型对比列表页
     */
    @RequestMapping(value = "/toComparedListPage", method = RequestMethod.GET)
    public String toComparedListPage(HttpServletRequest request) {
        return "modelsLibrary/modelsComparedList";
    }
    
    /**
     * @Description: 跳转至模型对比详情页
     */
    @RequestMapping(value = "/toComparedDetailsPage", method = RequestMethod.GET)
    public String toComparedDetailsPage(HttpServletRequest request) {
    	request.setAttribute("idArr", request.getParameter("idArr"));
    	request.setAttribute("modelNameArr", request.getParameter("modelNameArr"));
    	request.setAttribute("programNameArr", request.getParameter("programNameArr"));
    	return "modelsLibrary/modelsComparedDetails";
    }



	/**
	 * 模型对比返回结果
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showComparedDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toModelsDetailPage(HttpServletRequest request) {
		String idList = request.getParameter("idList")==null?"":request.getParameter("idList");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Map<String, Object> map = new HashMap<String, Object>();
		//项目id
		Long programId = null;
		//根据项目id获取项目基本信息
		LinkedHashMap<String,Object> programBasicInfoMap = new LinkedHashMap<String,Object>();
		//根据项目id获取模型数据分析结果list
		List<LinkedHashMap<String,Object>> modelDateAnalyInfoList = new ArrayList<LinkedHashMap<String,Object>>();
		//根据项目id获取模型数据评价指标list
		List<LinkedHashMap<String,Object>> modelDateEvaluateIndexList = new ArrayList<LinkedHashMap<String,Object>>();
		//根据数据文件id获取变量重要性排序list
		List<LinkedHashMap<String,Object>> varImportanceSortList = new ArrayList<LinkedHashMap<String,Object>>();
		//根据项目id获取Score分组详情list ----训练集
		List<LinkedHashMap<String,Object>> scoreGroupList = new ArrayList<LinkedHashMap<String,Object>>();
		//根据项目id获取Score分组详情list ----测试集
		List<LinkedHashMap<String,Object>> scoreGroupList2 = new ArrayList<LinkedHashMap<String,Object>>();
		//该项目采用算法id list
		List<String> arithmeticIdList = new ArrayList<String>();
		if(UsedUtil.isNotNull(idList)){
			try{
				Long modelId = Long.parseLong(idList);
				ModelsExtract modelsExtract = modelsLibraryApi.getModelById(modelId);
				programId = modelsExtract.getProgramId();
				programBasicInfoMap = modelsReportApi.getProgramMapById(programId);
				//判断项目信息是否为空
				if(null != programBasicInfoMap && !programBasicInfoMap.isEmpty()){
					modelDateAnalyInfoList = modelsReportApi.getModelDateAnalyInfoById(programId);
					arithmeticIdList.add(String.valueOf(modelsExtract.getArithmeticId()));
					programBasicInfoMap.put("arithmeticNames",modelsExtract.getArithmeticName());
					//该项目创建时间
					Date createTime = (Date) programBasicInfoMap.get("createTime");
					if(null != createTime){
						programBasicInfoMap.put("createTime",formatter.format(createTime));
					}
					modelDateEvaluateIndexList = modelsReportApi.getModelDateEvaluateIndexById(programId);
					varImportanceSortList = modelsReportApi.getVarImportanceSortById(programId);
					Map<String,Object> scoreParamMap = new HashMap<String, Object>();
					scoreParamMap.put("programId",programId);
					scoreParamMap.put("type","train");
					scoreGroupList = modelsReportApi.getScoreGroupListById(scoreParamMap);
					scoreParamMap.put("type","test");
					scoreGroupList2 = modelsReportApi.getScoreGroupListById(scoreParamMap);
				}
				
			}catch (Exception e){
				logger.info("模型库-----模型详情-----根据项目id获取项目基本信息失败",e);
			}
		}
		
		map.put("programBasicInfoMap",programBasicInfoMap);
		map.put("modelDateAnalyMap",modelDateAnalyInfoList);
		map.put("arithmeticIdList", arithmeticIdList);
		map.put("modelDateEvaluateIndexList",modelDateEvaluateIndexList);
		map.put("varImportanceSortList",varImportanceSortList);
		map.put("scoreGroupList",scoreGroupList);
		map.put("scoreGroupList1",scoreGroupList2);
		return map;
	}

	/**
     * 查询所有数据
     *
     * @return
     */
    @PostMapping("/getModelsAllList")
    @ResponseBody
    public List<ModelsExtract> getModelsAllList(HttpServletRequest request) {
        //获取查询条件
        List<ModelsExtract> list= modelsLibraryApi.getModelsAllList();
		return list;
    }

}
