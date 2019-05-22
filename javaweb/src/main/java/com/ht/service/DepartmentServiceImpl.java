package com.ht.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.dao.DepartmentDao;
import com.ht.tools.Result;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	//这些类在同时访问时所产生的对象会不会是多个共用一个对象呢？如果是，我下面的写法就有问题
	@Autowired
	private DepartmentDao dao;
	
	@Override
	public Result getDepartmentById(String id) {
		// TODO Auto-generated method stub
		Result result=new Result();
		if(null==id || ""==id) {
			result.addMsg("id字段不能为空");
			return result;
		}
		Map<String, Object> department = dao.SelectDepartmentById(id);
		if(null==department) {
			result.addMsg("未查找到对应部门");
		}
		result.addMsg("成功");
		result.addValue(department);
		return result;
	}

	@Override
	public Result getDepartments() {
		// TODO Auto-generated method stub
		Result result=new Result();
		List<Map<String, Object>> list = dao.selectAllDepartment();
		result.addMsg("成功");
		result.addValue(list);
		return result;
	}

	@Override
	public void editDepartmentById(String id, String name, String phone, String officer, String remarks) {
		// TODO Auto-generated method stub
		dao.editDepartmentById(id, name, phone, officer, remarks);
	}

	@Override
	public void physicDeleteDepartment(String id) {
		// TODO Auto-generated method stub
		dao.deletDepartMentById(id);
		
	}

	@Override
	public void logicalDeleteDepartment(String id) {
		// TODO Auto-generated method stub
		dao.setIsdelete(id);
	}

	@Override
	public Result addDepartment(String name, String phone, String officer, String remarks) {
		// TODO Auto-generated method stub
		Result result=new Result();
		if(0!=dao.selectCountByName(name)) {
			result.addMsg("部门已经存在，部门名字不可以重复！");
			return result;
		}
		
		try {
			dao.addDepartMent(name, phone, officer, remarks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.addMsg(e.getMessage());
			return result;
		}
		result.addMsg("成功");
		return result;
	}

	//没有删除的
	@Override
	public Result getDepartmentNoDelete() {
		// TODO Auto-generated method stub
		Result result=new Result();
		List<Map<String, Object>> departs = dao.selectByIsDelete("0");
		result.addMsg("成功");
		result.addValue(departs);
		return result;
	}

	@Override
	public Result getDepartmentDelete() {
		// TODO Auto-generated method stub
		Result result=new Result();
		List<Map<String, Object>> departs = dao.selectByIsDelete("1");
		result.addMsg("成功");
		result.addValue(departs);
		return result;
	}

}
