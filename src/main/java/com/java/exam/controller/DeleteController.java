package com.java.exam.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@Api(tags="4.刪除", description="DeleteController")
@RestController
public class DeleteController {
	
	@Autowired
	private CommonService commonService;
	
	Map<String, Object> params = new LinkedHashMap<>();
	
	@ApiOperation("1.刪除 Company")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="id",dataType="String",required=true,value="Company Id",defaultValue="2"),
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=true,value="Company Name",defaultValue="全家便利商店股份有限公司"),
        @ApiImplicitParam(paramType="query",name="address",dataType="String",required=true,value="Company address",defaultValue="台北市中山區中山北路二段61號7樓"),
    })
	@DeleteMapping("/delCompany")
	public ResponseEntity delCompany(HttpServletRequest request, @RequestHeader String id, @RequestParam("name") String name, 
			@RequestParam("address") String address) throws BaseException {
		
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		Company body = new Company();
		body.setId(Integer.valueOf(id));
		body.setName(name);
		body.setAddress(address);
		
		commonService.delCompany(session, body, params);
		
		return ResponseEntity.ok(params);
	}
	
	@ApiOperation("2.刪除 Client")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="id",dataType="String",required=true,value="Client Id",defaultValue="5"),
        @ApiImplicitParam(paramType="query",name="companyId",dataType="String",required=true,value="Client Company Id",defaultValue="2"),
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=true,value="Client Name",defaultValue="陳小美"),
        @ApiImplicitParam(paramType="query",name="email",dataType="String",required=true,value="Client Email",defaultValue="xiaomei@test.com"),
        @ApiImplicitParam(paramType="query",name="phone",dataType="String",required=true,value="Client phone",defaultValue="091234567")
    })
	@DeleteMapping("/delClient")
	public ResponseEntity delCompany(HttpServletRequest request, @RequestHeader("id") String id, @RequestParam("companyId") String companyId,
			@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone) throws BaseException {
		
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		Client body = new Client();
		body.setId(Integer.valueOf(id));
		body.setCompany_id(companyId);
		body.setName(name);
		body.setEmail(email);
		body.setPhone(phone);
		
		commonService.delClient(session, body, params);
		
		return ResponseEntity.ok(params);
	}

}
