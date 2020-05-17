package com.java.exam.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.exam.BaseException;
import com.java.exam.Service.CommonService;
import com.java.exam.vo.CommonVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="3.查詢", description="GetController")
@RestController
public class GetController {


	Map<String, Object> params = new LinkedHashMap<>();
	
	@Autowired
	private CommonService commonService;
	
	@ApiOperation("1.查詢Company")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=false,value="Company Name",defaultValue="全家便利商店股份有限公司"),
        @ApiImplicitParam(paramType="query",name="id",dataType="String",required=false,value="Company Id",defaultValue="2")
    })
	@GetMapping(path="/getCompany")
	public ResponseEntity getCompany(HttpServletRequest request,@RequestParam("name") String name, @RequestParam("id") String id) throws BaseException {		
		params.clear();
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		CommonVo vo = new CommonVo();
		vo.setSession(session);
		vo.setCompanyName(name);
		vo.setCompanyId(id);
		params = commonService.getCompany(vo);
		
		return ResponseEntity.ok(params);
	}
	
	@ApiOperation("2.查詢 Client")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=false,value="Client Name",defaultValue="阿笠博士"),
        @ApiImplicitParam(paramType="query",name="id",dataType="String",required=false,value="Client Id",defaultValue="6")
    })
	@GetMapping(path="/getClient")
	public ResponseEntity getClient(HttpServletRequest request,@RequestParam("name") String name, @RequestParam("id") String id) throws BaseException {		
		params.clear();
		HttpSession session =request.getSession();
		commonService.chkLogin(session);
		
		CommonVo vo = new CommonVo();
		vo.setSession(session);
		vo.setClientId(id);
		vo.setClientName(name);
		
		params = commonService.getClient(vo);
		
		return ResponseEntity.ok(params);
	}
}
