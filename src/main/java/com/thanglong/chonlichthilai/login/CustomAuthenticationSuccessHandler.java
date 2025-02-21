package com.thanglong.chonlichthilai.login;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.thanglong.chonlichthilai.taikhoan.*;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired private UserService userService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectURL = request.getContextPath();
        redirectURL = "/dashboard.html";
        String au = authentication + "";
        System.out.println(au);
        au = au.substring(au.indexOf("User Attributes:"),au.indexOf("Credentials"));
        String arr [] = au.split(", ");
        //
        String userName  = arr[5].replace("@thanglong.edu.vn", "").replace("email=", "");
        String givenName = arr[2].replace("given_name=", "");
        String familyName= arr[3].replace("family_name=", "");
        String email = arr[5].replace("email=", "");
        //
        User user = userService.findUserByUserName(userName);
        if (user == null) {
        	user = new User();
            user.setUserName(userName);
            user.setGivenName(givenName);
            user.setFamilyName(familyName);
            user.setEmail(email);
            if (userName.indexOf("thanhnx") >=0) {
            	user.setAdmin(true);
            } else {
            	user.setLecturer(true);
            }
            userService.saveUser(user);
        } else {
        	Boolean right = user.getAdmin();
        	if (right == true) {
        		response.sendRedirect("/dashboardAdmin.html");
        	} else {
        		right = user.getSecretary();
            	if (right == true) {
            		response.sendRedirect("/dashboardSecretary.html");
            	} else {
            		right = user.getLecturer();
                	if (right == true) {
                		response.sendRedirect("/dashboardLecturer.html");
                	} else {
                    	response.sendRedirect("/dashboardStudent.html");
                	}     
            	}
        	}
        }
    }
}