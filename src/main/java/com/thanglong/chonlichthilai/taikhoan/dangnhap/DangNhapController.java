package com.thanglong.chonlichthilai.taikhoan.dangnhap;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.taikhoan.GiangVien;
import com.thanglong.chonlichthilai.taikhoan.GiangVienService;
import com.thanglong.chonlichthilai.util.Message;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class DangNhapController {
 
    // Annotation
    @Autowired private GiangVienService service;
	@Autowired private EmailService emailService;

    // Read operation
    @GetMapping("/dangnhap")
    public List<GiangVien> fetchUserList()  {
        return service.fetchList();
    }

    @GetMapping("/dangnhap/{userName}")
    public Message layMatKhau(@PathVariable("userName") String userName)  {
//        EmailDetails emailDetails = new EmailDetails();
//        Long l = System.currentTimeMillis();
//        emailDetails.setSubject("TLU#"+l+" Mat khau dang nhap Chon lich thi lai");
//        emailDetails.setMsgBody("123456");
//        emailDetails.setRecipient(userName + "@thanglong.edu.vn");
//        int status = emailService.sendSimpleMail(emailDetails);
//        System.out.println(status);
        Message message = new Message();
//        if ( status == 1) {
            message.setCode(0);
            message.setContent("Gửi thành công");
//        } else {
//            message.setCode(0);
//            message.setContent("Gửi không thành công");        }
        return message;
    }
    @PostMapping("/dangnhap")
    public Message kiemTraMatKhau(HttpServletRequest request, HttpServletResponse response,@Valid @RequestBody GiangVien e) throws IOException  {
		Message message = new Message();
    	if (e.getPassword().equals("123456")) {
    		String userName = e.getUserName();
    		Session session = new Session();
        	session.setUserName(userName);
    		if (userName.indexOf("A")==0) {
    			session.setUserName(userName);
    			session.setDsQuyen("sinhVien");
    			message.setCode(0);
    			message.setContent("sinhVien");
    		} else {
//    			GiangVienEntity giangVien = service.findByUserName(userName);
    			if (userName.equals("thanhnx2")) {
        			session.setUserName(userName);
        			session.setDsQuyen("quanTri");
        			message.setCode(0);
        			message.setContent("quanTri");
    			} else if (userName.equals("linhnp")){
        			session.setUserName(userName);
        			session.setDsQuyen("thuKy");
        			request.getSession().setAttribute("session",session);
        			message.setCode(0);
        			message.setContent("thuKy");
    			} else {
        			session.setUserName(userName);
        			session.setDsQuyen("giangVien");
        			message.setCode(0);
        			message.setContent("giangVien");        			
    			}
    		}
    		request.getSession().setAttribute("session",session);
    	} else {
			message.setCode(-1);
			message.setContent("Tên đăng nhập hoặc tài khoản không hợp lệ, vui lòng liên hệ 0904.222.294");
    	}
    	return message;
    }

    // Update operation
    @PutMapping("/dangnhap/{id}")
    public GiangVien update(@RequestBody GiangVien taiKhoan, @PathVariable("id") Long id) {
        return service.update(taiKhoan, id);
    }
 
    // Delete operation
    @DeleteMapping("/dangnhap/{id}")
    public String deleteById(@PathVariable("id") Long id)  {
    	service.deleteById(id);
        return "Deleted Successfully";
    }
}
