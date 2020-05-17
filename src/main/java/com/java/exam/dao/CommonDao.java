package com.java.exam.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.exam.BaseException;
import com.java.exam.data.Client;
import com.java.exam.data.Company;
import com.java.exam.vo.CommonDaoVo;

@Service
public interface CommonDao {
	
	public Map login(CommonDaoVo daovo) throws BaseException;

	public List getCompany(CommonDaoVo daovo) throws BaseException;
	
	public List getClient(CommonDaoVo daovo) throws BaseException;

}
