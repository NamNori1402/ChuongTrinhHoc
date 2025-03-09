package com.thanglong.chonlichthilai.dangnhap;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.giangvien.GiangVien;
import com.thanglong.chonlichthilai.giangvien.GiangVienService;
import com.thanglong.chonlichthilai.sinhvien.SinhVien;
import com.thanglong.chonlichthilai.sinhvien.SinhVienService;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class DangNhapController {
 
    // Annotation
    @Autowired private GiangVienService service;
    @Autowired private SinhVienService sinhVienService;
	@Autowired private EmailService emailService;

    // Read operation
    @GetMapping("/dangnhap")
    public List<GiangVien> findAll()  {
        return service.findAll();
    }

    @GetMapping("/dangnhap/{userName}")
    public Message layMatKhau(@PathVariable("userName") String userName) throws JsonProcessingException  {
    	userName = userName.trim().toUpperCase();
    	Message message = new Message();
    	message.setCode(-1);
    	Object obj = null;
    	if (userName.indexOf("A")==0) {
    		obj = sinhVienService.findByMaSinhVien(userName);
    	} else {
    		obj = service.findByMaGiangVien(userName);
    	}
		if (obj != null) {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	    	String json = ow.writeValueAsString(obj);
	    	JSONObject jobj = new JSONObject(json);
	    	String email = jobj.getString("email1");
    		if (email != null) {
//    			EmailDetails emailDetails = new EmailDetails();
//                Long l = System.currentTimeMillis();
//                emailDetails.setSubject("TLU#"+l+" Mat khau dang nhap Chon lich thi lai");
//                emailDetails.setMsgBody("123456");
//                emailDetails.setRecipient(email);
//                int status = emailService.sendSimpleMail(emailDetails);
    			int status = 1;
                if (status == 1) {
                	message.setCode(0);
                    message.setContent(email);
                    message.setExtent(json);
                } else {
                	message.setContent("Không gửi được mật khẩu đến email: " + email);
                }
    		} else {
    			message.setContent("Không tồn tại tài khoản hoặc chưa có email");  
    		}    		
    	} else {
    		message.setContent("Không tồn tại mã sinh viên hoặc mã giảng viên này.");
    	}    	
        return message;
    }
    @PostMapping("/dangnhap")
    public Message kiemTraMatKhau(HttpServletRequest request, HttpServletResponse response,@Valid @RequestBody PhienKetNoi e) throws IOException  {
		Message message = new Message();
    	if (e.getPassword().equals("123456")) {
    		String userName = e.userName.trim().toUpperCase();
        	Object obj = null;
        	if (userName.indexOf("A")==0) {
        		obj = sinhVienService.findByMaSinhVien(userName);
        	} else {
        		obj = service.findByMaGiangVien(userName);
        	}
       		PhienKetNoi phienKetNoi = new PhienKetNoi();
       		
    		phienKetNoi.setUserName(userName);
    		phienKetNoi.setMaKy(e.getMaKy());
    		phienKetNoi.setNamHoc(e.getNamHoc());
    		if (userName.indexOf("A")==0) {
    			SinhVien sinhVien = (SinhVien)obj;
    			phienKetNoi.setUserName(userName);
    			phienKetNoi.setDsQuyen("sinhVien");   
    			phienKetNoi.setTen(sinhVien.getTen());
    			phienKetNoi.setHoTenDem(sinhVien.getHoTenDem());
    			message.setCode(0);
    			message.setContent("sinhVien");    			
    		} else {
    			GiangVien giangVien = (GiangVien)obj;
    			phienKetNoi.setTen(giangVien.getTen());
    			phienKetNoi.setHoTenDem(giangVien.getHoTenDem());
    			int quanTri = giangVien.getQuanTri();
    			int thuKy = giangVien.getThuKy();
    			if (quanTri == 1) {
    				phienKetNoi.setDsQuyen("quanTri");
        			message.setCode(0);
        			message.setContent("quanTri");
    			} else if (thuKy == 1){
    				phienKetNoi.setDsQuyen("thuKy");
        			request.getSession().setAttribute("session",phienKetNoi);
        			message.setCode(0);
        			message.setContent("thuKy");
    			} else {
    				phienKetNoi.setDsQuyen("giangVien");
        			message.setCode(0);
        			message.setContent("giangVien");        			
    			}
    		}
    		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	    	String json = ow.writeValueAsString(phienKetNoi);
	    	JSONObject jobj = new JSONObject(json);
	    	
    		message.setExtent(json);
    		phienKetNoi.setExtent(json);
    		request.getSession().setAttribute("phienKetNoi",phienKetNoi);
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

	public GiangVienService getService() {
		return service;
	}

	public void setService(GiangVienService service) {
		this.service = service;
	}

	public SinhVienService getSinhVienService() {
		return sinhVienService;
	}

	public void setSinhVienService(SinhVienService sinhVienService) {
		this.sinhVienService = sinhVienService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}
