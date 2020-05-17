package com.java.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.java.exam.BaseException;
import com.java.exam.data.Client;
import com.java.exam.data.Company;
import com.java.exam.vo.CommonDaoVo;

@Service
public class CommonDaoImpl implements CommonDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public Map login(CommonDaoVo daovo) throws BaseException {
		Map<String, Object> result = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList<String>();
		
		if(StringUtils.isBlank(daovo.getClientPassword())) {
			throw new BaseException("400", "Client Name can't be null");
		}
		if(StringUtils.isBlank(daovo.getClientPassword()))	{
			throw new BaseException("400", "Client Password can't be null");
		}
		
		sql.append(" SELECT p.name_id,							\n")
			.append("        c.NAME,							\n")
			.append("        p.api_key,							\n")
			.append("        r.permission_id,					\n")
			.append("        Parsedatetime(CURRENT_TIMESTAMP, 'yyyy-MM-dd HH:mm:ss') AS updated_time	\n")
			.append(" FROM   password p							\n")
			.append("        INNER JOIN client c				\n")
			.append("                ON c.id = p.name_id		\n")
			.append("        INNER JOIN role r					\n")
			.append("                ON r.name_id = p.name_id	\n")
			.append(" WHERE  1 = 1								\n")
			.append("        AND c.NAME = ?						\n")
			.append("        AND p.api_key = HASH('SHA256', STRINGTOUTF8( ? ), 1000)  				\n");

		params.add(daovo.getClientName());
		params.add(daovo.getClientPassword());
		
		try {
			result =  (Map) jdbc.queryForMap(sql.toString(), params.toArray());
			
		}catch(EmptyResultDataAccessException e){
			e.printStackTrace();
			throw new BaseException("404", "Can't not find the client");
		}
		
		
		return result;
	}
	
	public List getCompany(CommonDaoVo daovo) throws BaseException {
		List resultList;
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		
		sql.append(" SELECT *								\n")
		.append(" FROM  company c							\n")
		.append(" WHERE  1 = 1								\n");
	
		if(StringUtils.isNoneBlank(daovo.getCompanyId())) {
			sql.append("        AND c.id = ?  					\n");
			params.add(daovo.getCompanyId());
		}
		if(StringUtils.isNotBlank(daovo.getCompanyName())) {
			sql.append("        AND c.NAME = ?					\n");
			params.add(daovo.getCompanyName());
		}
		
		try {
			resultList = jdbc.queryForList(sql.toString(), params.toArray());
			
		}catch(EmptyResultDataAccessException e){
			e.printStackTrace();
			throw new BaseException("204", "Can't not find the Company");
		}
		
		
		return resultList;
	}
	
	public List getClient(CommonDaoVo daovo) throws BaseException {
		List resultList;
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		
		sql.append(" SELECT *								\n")
			.append(" FROM   client c						\n")
			.append(" WHERE  1 = 1							\n");
		
		if(StringUtils.isNoneBlank(daovo.getClientId())) {
			sql.append("        AND c.id = ?  					\n");
			params.add(daovo.getClientId());
		}
		if(StringUtils.isNotBlank(daovo.getClientName())) {
			sql.append("        AND c.NAME = ?					\n");
			params.add(daovo.getClientName());
		}
		
		try {
			resultList = jdbc.queryForList(sql.toString(), params.toArray());
			
		}catch(EmptyResultDataAccessException e){
			e.printStackTrace();
			throw new BaseException("204", "Can't not find the Client");
		}
		
		
		return resultList;
	}
	
}
