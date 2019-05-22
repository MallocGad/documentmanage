package com.ht.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 封装结果
 * 由于目前的结果集信息是直接返回给前端，所以没有考虑用泛型
 * 如果结果集还要经过后端处理访问的话应该改为泛型
 * @author ht032
 *
 */
@Component
public class Result {
	
	

	@Override
	public String toString() {
		return "Result [result=" + result + "]";
	}

	//保存结果集信息
	List<Map<String,Object>> result=null;
	//结果情况描述信息
	public Result() {
		result=new ArrayList<Map<String,Object>>();
	}
	
	/**
	 * 结果集描述信息
	 * @param msg
	 */
	public void addMsg(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg",msg);
		this.result.add(map);
	}
	
	/**
	 * 查询结果集信息
	 * @param object
	 */
	public void addValue(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",object);
		this.result.add(map);
	}
	
	/**
	 * 自定义信息
	 * @param key
	 * @param object
	 */
	public void add(String key,Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key,object);
		this.result.add(map);
	}
	
	/**
	 * 返回结果信息
	 * @return
	 */
	public List<Map<String, Object>> getResult() {
		return this.result;
	}
}
