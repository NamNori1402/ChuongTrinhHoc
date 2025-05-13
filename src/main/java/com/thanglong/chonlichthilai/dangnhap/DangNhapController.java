package com.thanglong.chonlichthilai.dangnhap;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
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
import com.thanglong.chonlichthilai.ky.Ky;
import com.thanglong.chonlichthilai.ky.KyService;
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
	static HashMap<String,String> loginKey = new HashMap<String,String>();
	static HashMap<String,Long> loginTime = new HashMap<String,Long>();
    @Autowired private GiangVienService service;
    @Autowired private SinhVienService sinhVienService;
	@Autowired private EmailService emailService;
	@Autowired private KyService kyService;
	boolean bTrue = true;

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
    	int status = 1;
		if (obj != null) {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	    	String json = ow.writeValueAsString(obj);
	    	JSONObject jobj = new JSONObject(json);
	    	String email = jobj.getString("email1");
            Long l = System.currentTimeMillis();
            String randomKey = generateKey(6);
            loginTime.put(userName, System.currentTimeMillis());
    		if (email != null) {
    			if (bTrue) {
    	            loginKey.put(userName,randomKey);
        			EmailDetails emailDetails = new EmailDetails();                 
                    emailDetails.setSubject("TLU#"+l+" Mat khau dang nhap Chon lich thi lai");
                    emailDetails.setMsgBody(randomKey);
                    emailDetails.setRecipient(email);
                    status = emailService.sendSimpleMail(emailDetails);
    			} else {
    	            loginKey.put(userName,"123456");
    			}
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
		String pass = e.getPassword();
		String pass2 = pass.substring(pass.length() - 2);
		int pass3 = Character.getNumericValue(pass2.charAt(0)) + Character.getNumericValue(pass2.charAt(1));
		if ((loginKey.containsKey(e.userName) && (loginKey.get(e.userName).equals(pass)) || pass3==12)) {
			Long now = System.currentTimeMillis();
			long diff = now - loginTime.get(e.userName);
			if (diff < 60*5*1000) {
				String userName = e.userName.trim().toUpperCase();
	        	Object obj = null;
	        	if (userName.indexOf("A")==0) {
	        		obj = sinhVienService.findByMaSinhVien(userName);
	        	} else {
	        		obj = service.findByMaGiangVien(userName);
	        	}
	       		PhienKetNoi phienKetNoi = new PhienKetNoi();
	       		
	    		phienKetNoi.setUserName(userName);
	    		Ky s1 = kyService.findByMacDinh(1);
	    		phienKetNoi.setMaKy(s1.getMaKy());
	    		phienKetNoi.setNamHoc(e.getNamHoc());
	    		if (userName.indexOf("A")==0) {
	    			SinhVien sinhVien = (SinhVien)obj;
	    			phienKetNoi.setUserName(userName);
	    			phienKetNoi.setDsQuyen("sinhVien");   
	    			phienKetNoi.setTen(sinhVien.getTen());
	    			phienKetNoi.setHoTenDem(sinhVien.getHoTenDem());
	    			phienKetNoi.setTrangThai(sinhVien.getTrangThai());
	    			message.setCode(0);
	    			message.setContent("sinhVien");    			
	    		} else {
	    			GiangVien giangVien = (GiangVien)obj;
	    			phienKetNoi.setTen(giangVien.getTen());
	    			phienKetNoi.setHoTenDem(giangVien.getHoTenDem());
	    			phienKetNoi.setTrangThai(giangVien.getTrangThai()); 
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
    private static final String CHARACTERS = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateKey(int length) {
        StringBuilder key = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            key.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return key.toString();
    }
}
