package com.java.exam.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.exam.BaseException;
import com.java.exam.Service.CommonService;
import com.java.exam.data.Client;
import com.java.exam.data.Clients;
import com.java.exam.data.Company;
import com.java.exam.vo.CommonVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="1.新增", description="PostController")
@RestController
public class PostController {

	Map<String, Object> params = new LinkedHashMap<>();
	
	@Autowired
	private CommonService commonService;
	
	@ApiOperation("1.登入系統")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=true,value="用户的姓名",defaultValue="Adam"),
        @ApiImplicitParam(paramType="header",name="password",dataType="String",required=true,value="用户的密码",defaultValue="12345")
    })
	@PostMapping(path="/login")
	public ResponseEntity login(HttpServletRequest request, @RequestParam("name") String name, @RequestHeader("password") String password) throws BaseException {
		params.clear();
		CommonVo vo = new CommonVo();
		vo.setClientName(name);
		vo.setClientPassword(password);
		params = commonService.login(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("API_KEY", params.get("API_KEY").toString());
		session.setAttribute(params.get("API_KEY").toString(), new HashMap(params));
		
		return ResponseEntity.ok(params);
	}
	
	@ApiOperation("2.新增 Company")
	@PostMapping("/createCompany")
	public ResponseEntity<Company> createCompany(HttpServletRequest request,@RequestBody Company body) throws BaseException {
		
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		commonService.createCompany(session,body);
		
		return ResponseEntity.ok(body);
	}
	
	@ApiOperation("3.新增 Client")
	@PostMapping("/createClient")
	public ResponseEntity<Client> createClient(HttpServletRequest request,@RequestBody Client body) throws BaseException {
		
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		commonService.createClient(session,body);
		
		return ResponseEntity.ok(body);
	}
	
	@ApiOperation("4.新增 多筆Client")
	@PostMapping("/createMultipleClients")
	public ResponseEntity<Map> createMultipleClients(HttpServletRequest request,@RequestBody Clients body) throws BaseException {
		params.clear();
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		commonService.createMultipleClients(session,body,params);
		
		return ResponseEntity.ok(params);
	}
	
}
