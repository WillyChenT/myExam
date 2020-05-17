package com.java.exam.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.exam.BaseException;
import com.java.exam.Service.CommonService;
import com.java.exam.data.Client;
import com.java.exam.data.Company;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(tags="2.更新", description="PutController")
@RestController
public class PutController {

	Map<String, Object> params = new LinkedHashMap<>();
	
	@Autowired
	private CommonService commonService;
	
	@ApiOperation("1.更新  Company")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="id",dataType="String",required=true,value="Company Id",defaultValue="2"),
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=false,value="Company Name",defaultValue="肯德基"),
        @ApiImplicitParam(paramType="query",name="address",dataType="String",required=false,value="Company address",defaultValue="台北市建國北路一段96號9樓")
    })
	@PutMapping("/updateCompany")
	public ResponseEntity updateCompany(HttpServletRequest request, @RequestHeader String id, @RequestParam("name") String name, 
			@RequestParam("address") String address) throws BaseException {
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		Company body = new Company();
		body.setId(Integer.valueOf(id));
		body.setName(name);
		body.setAddress(address);
		
		body = commonService.updateCompany(session,body);
		
		return ResponseEntity.ok(body);
	}
	
	@ApiOperation("2.更新 Client")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="id",dataType="String",required=true,value="Client Id",defaultValue="7"),
        @ApiImplicitParam(paramType="query",name="companyId",dataType="String",required=false,value="Client Company Id",defaultValue="3"),
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=false,value="Client Name",defaultValue="Tom"),
        @ApiImplicitParam(paramType="query",name="email",dataType="String",required=false,value="Client Email",defaultValue="tomCat@test.com"),
        @ApiImplicitParam(paramType="query",name="phone",dataType="String",required=false,value="Client phone",defaultValue="0954321000")
    })
	@PutMapping("/updateClient")
	public ResponseEntity updateClient(HttpServletRequest request, @RequestHeader("id") String id, @RequestParam("companyId") String companyId,
			@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone) throws BaseException {
		params.clear();
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		Client body = new Client();
		body.setId(Integer.valueOf(id));
		body.setCompany_id(companyId);
		body.setName(name);
		body.setEmail(email);
		body.setPhone(phone);
		body = commonService.updateClient(session,body);
		
		return ResponseEntity.ok(body);
	}
	
}
