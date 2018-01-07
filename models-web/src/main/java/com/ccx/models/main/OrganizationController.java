package com.ccx.models.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccx.models.api.OrganizationApi;

/**
 * 
 * @description 部门管理
 * @author zxr
 * @date 2017 下午2:18:25
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationApi organizationApi;

	/**
     * 部门管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "organization/organization";
    }
    
    /**
     * 部门树
     *
     * @return
     */
    @PostMapping(value = "/tree")
    @ResponseBody
    public Object tree() {
        return organizationApi.selectTree();
    } 
}
