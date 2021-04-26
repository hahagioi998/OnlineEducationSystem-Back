package com.example.demo.service;
import org.slf4j.Logger;

import static com.example.demo.exception.AppConstants.OPERATION_FAILED;
import static com.example.demo.exception.AppConstants.USER_NOT_FOUND;
import static com.example.demo.exception.AppConstants.WRONG_PASSWORD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admin;

import com.example.demo.exception.OperationFailedException;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.AdminRepository;
import com.example.demo.util.GlobalResources;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {	

private Logger logger=GlobalResources.getLogger(AdminServiceImpl.class);
	@Autowired
	AdminRepository adminRepository;
	
	//admin login
	@Override
	public String loginAdmin(Admin admin) {

		 String methodName="loginAdmin()";
				 logger.info(methodName + "called");
		String str=null;
		Optional<Admin> adminObj= adminRepository.findByUserName(admin.getAdminuserName());
		if (!adminObj.isPresent())
		{
			System.out.println(adminObj);
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			
			String pwd = adminObj.get().getAdminPassword();
			if (!pwd.equals(admin.getAdminPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
				
			}
			try { 
				str = "Login in sucessfull";
				adminRepository.saveAndFlush(adminObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}

}