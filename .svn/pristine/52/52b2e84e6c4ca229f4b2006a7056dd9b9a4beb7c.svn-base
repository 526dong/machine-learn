package com.ccx.models.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ccx.models.api.OrganizationApi;
import com.ccx.models.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ccx.models.mapper.OrganizationMapper;
import com.ccx.models.model.vo.Tree;

@Service("organizationApi")
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationApi {

	@Autowired
    private OrganizationMapper organizationMapper;
	
	@Override
	public List<Tree> selectTree() {
		List<Organization> organizationList = selectTreeGrid();
		List<Tree> trees = new ArrayList<Tree>();
        if (organizationList != null) {
            for (Organization organization : organizationList) {
                Tree tree = new Tree();
                tree.setId(organization.getId());
                tree.setText(organization.getName());
                tree.setIconCls(organization.getIcon());
                tree.setPid(organization.getPid());
                trees.add(tree);
            }
        }
        return trees;
	}

	@Override
	public List<Organization> selectTreeGrid() {
		EntityWrapper<Organization> wrapper = new EntityWrapper<>();
		wrapper.orderBy("seq");
		return organizationMapper.selectList(wrapper);
	}

	@Override
	public List<Organization> findAll() {
		List<Organization> orList = organizationMapper.findAll();
		return orList;
	}

}
