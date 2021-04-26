package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.exception.BaseResponse;
import com.example.demo.service.AdminService;
import com.example.demo.util.GlobalResources;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")

public class AdminController {
	private Logger logger=GlobalResources.getLogger(AdminController.class);
	@Autowired(required = false)
    private AdminService adminService;
	
	//http://localhost:8080/api/admin/login
	 @PostMapping("/login") 
	    
	    public ResponseEntity<?>loginAdmin( @RequestBody Admin admin) {
		 String methodName="loginAdmin()";
		 logger.info(methodName + "called");
	        String str = adminService.loginAdmin(admin);
	        BaseResponse baseResponse = new BaseResponse();
	        baseResponse.setStatusCode(1);
	        baseResponse.setResponse(str);
	        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	    }

}