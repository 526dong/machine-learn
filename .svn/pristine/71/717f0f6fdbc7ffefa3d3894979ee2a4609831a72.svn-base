package com.ccx.models.api;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.ccx.models.model.Organization;
import com.ccx.models.model.vo.Tree;

/**
 * 
 * @description Organization 表数据服务层接口
 * @author zxr
 * @date 2017 下午5:12:32
 */
public interface OrganizationApi extends IService<Organization>{

	 /**
	  * 获取部门树
	  * @return
	  */
	 List<Tree> selectTree();
	 
	 /**
	  * 获取层次树
	  * @return
	  */
	 List<Organization> selectTreeGrid();

	 /**
	  * 获取所有部门
	  * @return
	  */
	List<Organization> findAll();

}
