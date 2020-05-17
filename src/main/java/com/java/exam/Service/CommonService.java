package com.java.exam.Service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.java.exam.BaseException;
import com.java.exam.data.Client;
import com.java.exam.data.Clients;
import com.java.exam.data.Company;
import com.java.exam.vo.CommonVo;

@Service
public interface CommonService {

	public Map<String, Object> login(CommonVo vo) throws BaseException;

	public Map<String, Object> getCompany(CommonVo vo) throws BaseException;
	
	public Map<String, Object> getClient(CommonVo vo) throws BaseException;
	
	public void createCompany(HttpSession session, Company body) throws BaseException;

	public void createClient(HttpSession session, Client body) throws BaseException;
	
	public Map<String, Object> createMultipleClients(HttpSession session, Clients body,Map<String, Object> params) throws BaseException;

	public Company updateCompany(HttpSession session, Company body) throws BaseException;
	
	public Client updateClient(HttpSession session, Client body) throws BaseException;
	
	public Map<String, Object> delCompany(HttpSession session, Company body, Map<String, Object> params) throws BaseException;
	
	public Map<String, Object> delClient(HttpSession session, Client body , Map<String, Object> params) throws BaseException;
	
	public void chkLogin(HttpSession session) throws BaseException;

	
}
