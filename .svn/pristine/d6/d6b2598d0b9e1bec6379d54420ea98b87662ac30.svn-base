package com.ccx.models.init;

import org.springframework.stereotype.Service;
/**
 * 启动项目 静态map装载指标行业映射关系、指标和评分规则关联
 * @author sunqi
 *
 */
@Service
public class InitIndexParam /*implements InitializingBean*/{

/*	@Autowired 
	QualitaIndexApi api;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String,List<IndexRuleBean>> IndexRuleMap = api.getIndexRuleList("1001");
		System.out.println(IndexRuleMap.toString());
		
	}*/

/*	private static final Logger logger = LoggerFactory.getLogger(InitIndexParam.class);
	
	public static Map<String,String> IndutstryIndexMap = new HashMap<>();//行业与指标映射map
	
	public static Map<String,Double> IndexWeightMap = new HashMap<>();//指标与权重映射map
	
	public static Map<String,String> IndexNameMap = new HashMap<>();//指标与名字映射map
	
	public static Map<String,List<IndexRuleBean>> IndexRuleMap = new HashMap<>();//指标规则 id与分值区间、得分、等级映射map
	
	@Autowired
	InitIndexParamApi api;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		logger.debug("项目启动加载行业指标等相关参数信息");
		List<IndustryIndex> list = api.selectIndustryIndex();
		for(int i = 0;i<list.size();i++){
			IndutstryIndexMap.put(list.get(i).getIndustryCode(), list.get(i).getIndexList());
		}
		
		List<IndexBean> listIndex = api.selectIndexData();
		for(int i = 0;i<listIndex.size();i++){
			IndexWeightMap.put(listIndex.get(i).getIndexId(), listIndex.get(i).getIndexWeight());
			IndexNameMap.put(listIndex.get(i).getIndexId(), listIndex.get(i).getIndexName());
		}
		
		
		List<IndexRuleBean> listIndexRule = api.selectIndexRule();
		
		for (int i = 0; i < listIndexRule.size() - 1; i++) {
			List<IndexRuleBean> listForMap = new ArrayList<>();
			listForMap.add(listIndexRule.get(i));
			for (int j = listIndexRule.size() - 1; j > i; j--) {
				if (listIndexRule.get(j).getIndexId().equals(listIndexRule.get(i).getIndexId())) {
					listForMap.add(listIndexRule.get(j));
					listIndexRule.remove(j);
				}
			}
			IndexRuleMap.put(listForMap.get(0).getIndexId(), listForMap);
		}
	}
	*/
	
	
	
}
