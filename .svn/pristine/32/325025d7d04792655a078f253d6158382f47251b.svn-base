package com.ccx.models.base;

import com.ccx.models.api.laboratory.ModelsReportApi;
import com.ccx.models.util.JsonResult;
import com.ccx.models.util.UsedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 中英文切换
 */
@Controller
@RequestMapping("/varLanguage")
public class BasicVarLanguageController extends BaseController {
	private static Logger logger = LogManager.getLogger(BasicVarLanguageController.class);

	@Autowired
	private ModelsReportApi modelsReportApi;

	/**
	 * 根据fileId查找中英文对应list
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getChinsesAndEnglish", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getChinsesAndEnglish(HttpServletRequest request) {
		String fileId = request.getParameter("fileId")==null?"":request.getParameter("fileId");
		if(UsedUtil.isNotNull(fileId)){
			try {
				List<LinkedHashMap<String,Object>> list = modelsReportApi.getChinsesAndEnglish(Long.valueOf(fileId));
				if(null != list && !list.isEmpty()){
					LinkedHashMap<String,List<String>> resultMap = new LinkedHashMap<String,List<String>>();
					//定义一个英文list，一个中文list，两者长度相同，内容对应
					List<String> englishList = new ArrayList<String>();
					List<String> chineseList = new ArrayList<String>();
					for (int i=0;i<list.size();i++) {
						englishList.add((String)list.get(i).get("name"));
						chineseList.add((String)list.get(i).get("chineseName"));
					}
					resultMap.put("englishList",englishList);
					resultMap.put("chineseList",chineseList);
					return JsonResult.ok(resultMap);
				}
			} catch (Exception e) {
				logger.info("根据fileId查找中英文对应list失败!,失败原因========",e);
				return JsonResult.error("查找中英文对应关系失败！");
			}
		}
		return JsonResult.error("查找中英文对应关系失败！");
	}








}
