package com.java.exam.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.exam.BaseException;
import com.java.exam.dao.CommonDao;
import com.java.exam.data.Client;
import com.java.exam.data.Clients;
import com.java.exam.data.Company;
import com.java.exam.repository.ClientRepository;
import com.java.exam.repository.CompanyRepository;
import com.java.exam.vo.CommonDaoVo;
import com.java.exam.vo.CommonVo;

@Service
public class CommonServiceImpl implements CommonService {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private CompanyRepository companyDao;
	@Autowired
	private ClientRepository clientDao;
	
	public Map<String, Object> login(CommonVo vo) throws BaseException{
		
		if(StringUtils.isBlank(vo.getClientName())) {
			throw new BaseException("403", "Client Name can't be null");
		}
		if(StringUtils.isBlank(vo.getClientPassword()))	{
			throw new BaseException("403", "Client Password can't be null");
		}
		
		CommonDaoVo daovo = new CommonDaoVo();
		daovo.setClientName(vo.getClientName());
		daovo.setClientPassword(vo.getClientPassword());
		Map<String, Object> user = commonDao.login(daovo);

		return user;
	}
	
	public Map<String, Object> getCompany(CommonVo vo) throws BaseException{

		Map<String, Object> companyMap = new HashMap<>();
		CommonDaoVo daovo = new CommonDaoVo();
		daovo.setCompanyId(vo.getCompanyId());
		daovo.setCompanyName(vo.getCompanyName());
		
		List<?> queryList = commonDao.getCompany(daovo);
		
		if(null != queryList && queryList.size()>0) {
			for (int i=0; i < queryList.size(); i++) {
				Map<?, ?> temp = (Map<?, ?>) queryList.get(i);
				companyMap.put(temp.get("ID").toString(), temp);
			}
		}
		
		return companyMap;
	}
	
	public Map<String, Object> getClient(CommonVo vo) throws BaseException{
		
		Map<String, Object> clientMap = new HashMap<>();
		CommonDaoVo daovo = new CommonDaoVo();
		daovo.setClientId(vo.getClientId());
		daovo.setClientName(vo.getClientName());
		
		List<?> queryList = commonDao.getClient(daovo);
		
		if(null != queryList && queryList.size()>0) {
			for (int i=0; i < queryList.size(); i++) {
				Map<?, ?> temp = (Map<?, ?>) queryList.get(i);
				clientMap.put(temp.get("ID").toString(), temp);
			}
		}
		
		return clientMap;
	}
	
	public void createCompany(HttpSession session, Company body) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		
		if(!"1".equals(permissionId) && !"3".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to create company."); 
		}
		
		
		if(StringUtils.isBlank(body.getName())) {
			throw new BaseException("403", "Company Name can't be null."); 
		}
		if(StringUtils.isBlank(body.getAddress())) {
			throw new BaseException("403", "Company Address can't be null."); 
		}
		

		body.setCreatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
		body.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		body.setUpdatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
		body.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		
		//判定是否已有資料
		List<Company> queryList = new ArrayList<Company>();
		queryList = companyDao.query(body.getName(), body.getAddress());
		if(queryList.size() == 0 ) {
			try {
				companyDao.save(body);
			}catch (Exception e) {
				throw new BaseException("403", "Create Error!"); 
			}
		}else {
			throw new BaseException("201", "The Company has been created"); 
		}
		//重新查詢建立的資料
		queryList = companyDao.query(body.getName(), body.getAddress());
		if(null != queryList && queryList.size() == 1 ) {
			body = queryList.get(0);
		}else {
			throw new BaseException("403", "Create Error!"); 
		}
	}
	
	public void createClient(HttpSession session, Client body) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		if(!"1".equals(permissionId) && !"3".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to create Client."); 
		}
		
		
		if(StringUtils.isBlank(body.getCompany_id())) {
			throw new BaseException("403", "Client CompanyId can't be null."); 
		}
		if(StringUtils.isBlank(body.getName())) {
			throw new BaseException("403", "Client Name can't be null."); 
		}
		if(StringUtils.isBlank(body.getEmail())) {
			throw new BaseException("403", "Client Email can't be null."); 
		}
		if(StringUtils.isBlank(body.getPhone())) {
			throw new BaseException("403", "Client Phone can't be null."); 
		}
		

		body.setCreatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
		body.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		body.setUpdatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
		body.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		
		//判定是否已有資料
		List<Client> queryList = new ArrayList<Client>();
		queryList = clientDao.query(body.getCompany_id(), body.getName(), body.getEmail(), body.getPhone());
		if(queryList.size() == 0 ) {
			try {
				clientDao.save(body);
			}catch (Exception e) {
				e.printStackTrace();
				throw new BaseException("403", "Create Error!"); 
			}
		}else {
			throw new BaseException("201", "The Client has been created"); 
		}
		//重新查詢建立的資料
		queryList = clientDao.query(body.getCompany_id(), body.getName(), body.getEmail(), body.getPhone());
		if(null != queryList && queryList.size() == 1 ) {
			body = queryList.get(0);
		}else {
			throw new BaseException("403", "Create Error!"); 
		}
	}
	
	
	public Company updateCompany(HttpSession session, Company body) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		
		//not Super User & not Operator
		if(!"1".equals(permissionId) && !"2".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to update company."); 
		}
		
		if(StringUtils.isBlank(String.valueOf(body.getId()))) {
			throw new BaseException("403", "Company Id can't be null."); 
		}
		
		//判定是否已有資料
		List<Company> queryList = new ArrayList<Company>();
		queryList = companyDao.queryById(body.getId());
		if(null != queryList && queryList.size() == 1) {
			Company orgBody = queryList.get(0);
			body.setCreatedBy(orgBody.getCreatedBy());
			body.setCreatedAt(orgBody.getCreatedAt());
			body.setUpdatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
			body.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			try {
				companyDao.save(body);
			}catch (Exception e) {
				throw new BaseException("403", "Update Error!"); 
			}
		}else {
			throw new BaseException("404", "The Company id not found."); 
		}
		//重新查詢修改的資料
		queryList = companyDao.queryById(body.getId());
		if(null != queryList && queryList.size() == 1) {
			body = queryList.get(0);
		}else {
			throw new BaseException("403", "Update Error!"); 
		}
		return body;
	}
	
	public Client updateClient(HttpSession session, Client body) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		//not Super User & not Operator
		if(!"1".equals(permissionId) && !"2".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to update Client."); 
		}
		
		
		if(StringUtils.isBlank(String.valueOf(body.getId()))) {
			throw new BaseException("403", "Client Id can't be null."); 
		}
				
		body.setUpdatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
		body.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		
		//判定是否已有資料
		List<Client> queryList = new ArrayList<Client>();
		queryList = clientDao.queryById(body.getId());
		if(null != queryList && queryList.size() == 1) {
			Client orgBody = queryList.get(0);
			body.setCreatedBy(orgBody.getCreatedBy());
			body.setCreatedAt(orgBody.getCreatedAt());
			body.setUpdatedBy(null == infoMap.get("NAME") ? "" :infoMap.get("NAME").toString());
			body.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			try {
				clientDao.save(body);
			}catch (Exception e) {
				throw new BaseException("403", "Update Error!"); 
			}
		}else {
			throw new BaseException("404", "The Client id not found."); 
		}
		//重新查詢修改的資料
		queryList = clientDao.queryById(body.getId());
		if(null != queryList && queryList.size() == 1 ) {
			body = queryList.get(0);
		}else {
			throw new BaseException("403", "Update Error!"); 
		}
		return body;
	}
	
	public Map<String, Object> delCompany(HttpSession session, Company body, Map<String, Object> params) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		
		//not Super User & not Operator
		if(!"1".equals(permissionId) && !"2".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to update company."); 
		}
		
		if(StringUtils.isBlank(String.valueOf(body.getId()))) {
			throw new BaseException("403", "Company Id can't be null."); 
		}
		if(StringUtils.isBlank(body.getName())) {
			throw new BaseException("403", "Company Name can't be null."); 
		}
		if(StringUtils.isBlank(body.getAddress())) {
			throw new BaseException("403", "Company Address can't be null."); 
		}
		
		//判定是否已有資料
		List<Company> queryList = new ArrayList<Company>();
		queryList = companyDao.query(body.getId(),body.getName(),body.getAddress());
		if(null != queryList && queryList.size()==1) {
			try {
				companyDao.delete(body);
			}catch(Exception e) {
				throw new BaseException("403", "Delete Error!"); 
			}
		}else {
			throw new BaseException("404", "The Company id not found."); 
		}
		
		params.put("Code", 200);
		params.put("Message", "Delete Company Success!");
		
		
		return params;
	}
	
	
	public Map<String, Object> delClient(HttpSession session, Client body, Map<String, Object> params) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		//not Super User & not Operator
		if(!"1".equals(permissionId) && !"2".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to delete Client."); 
		}
		
		if(StringUtils.isBlank(String.valueOf(body.getId()))) {
			throw new BaseException("403", "Client Id can't be null."); 
		}
		if(StringUtils.isBlank(body.getCompany_id())) {
			throw new BaseException("403", "Client CompanyId can't be null."); 
		}
		if(StringUtils.isBlank(body.getName())) {
			throw new BaseException("403", "Client Name can't be null."); 
		}
		if(StringUtils.isBlank(body.getEmail())) {
			throw new BaseException("403", "Client Email can't be null."); 
		}
		if(StringUtils.isBlank(body.getPhone())) {
			throw new BaseException("403", "Client Phone can't be null."); 
		}
		
		//判定是否已有資料
		List<Client> queryList = new ArrayList<Client>();
		queryList = clientDao.query(body.getId(),body.getCompany_id(), body.getName(), body.getEmail(), body.getPhone());
		if(null != queryList && queryList.size()==1) {
			try {
				clientDao.delete(body);
			}catch(Exception e) {
				throw new BaseException("403", "Delete Error!"); 
			}
		}else {
			throw new BaseException("404", "The Client id not found."); 
		}
		
		params.put("Code", 200);
		params.put("Message", "Delete Client Success!");
		
		return params;
	}
	
	public Map<String, Object> createMultipleClients(HttpSession session, Clients body, Map<String, Object> params) throws BaseException{
		//權限檢查
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();
		Map<String, Object> infoMap = this.getLoginInfo(session,token);
		
		String permissionId = null == infoMap.get("PERMISSION_ID") ? "" : infoMap.get("PERMISSION_ID").toString();
		if(!"1".equals(permissionId) && !"3".equals(permissionId)) {
			throw new BaseException("401", "You don't have permission to create company."); 
		}
		
		Client[] companyList = body.getClients();
		if(null != companyList && companyList.length > 0 ) {
			for (int i=0; i< companyList.length; i++) {
				Client clientBody = companyList[i];
				try {
					this.createClient(session, clientBody);
					params.put(String.valueOf(i), clientBody);
				}catch(BaseException e) {
					Map<String, Object> erroMap= new LinkedHashMap();
					erroMap.put("Code", e.getErrorCode());
					erroMap.put("Message", e.getErrorMessage());
					params.put(String.valueOf(i), erroMap);
				}
			}
		}else {
			throw new BaseException("400", "Data Error!"); 
		}
	
		return params;
	}
	
	public void chkLogin(HttpSession session) throws BaseException{
		String token = null == session.getAttribute("API_KEY") ? "" : session.getAttribute("API_KEY").toString();

		if(null != session.getAttribute(token)) {
			Map<String, Object> infoMap= (Map<String, Object>) session.getAttribute(token);
			
			if(token.equals(infoMap.get("API_KEY").toString())) {
				LocalDateTime ldt = LocalDateTime.parse(infoMap.get("UPDATED_TIME").toString(), dtf);
				Duration duration = Duration.between(ldt, LocalDateTime.now());
				long timeOut = 5;
				long durations = duration.toMinutes();
				if(timeOut < durations) {
					throw new BaseException("403", "It's timeout. \nPlease reLogin agagin.");
				}
				infoMap.put("UPDATED_TIME", dtf.format(LocalDateTime.now()));
			}else {
				throw new BaseException("403", "Access Denied!");
			}
		}else {
			throw new BaseException("403", "Access Denied!");
		}
	}
	
	public Map<String, Object> getLoginInfo(HttpSession session, String key) throws BaseException {
		Map<String, Object> infoMap = new HashMap<>();
		
		if(null != session.getAttribute(key)) {
			 infoMap= (Map<String, Object>) session.getAttribute(key);
		}
		return infoMap;
	}


}
