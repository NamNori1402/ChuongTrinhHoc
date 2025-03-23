package com.thanglong.chonlichthilai.dangky;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.sinhvien.SinhVien;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
 
// Annotation
@RestController
 
@RequestMapping("/api/v1")
public class DangKyController {
 
    // Annotation
    @Autowired private DangKyService service;
	@Autowired private EmailService emailService;
	
    @PostMapping("/dangky2")
    public DangKy save(@Valid @RequestBody DangKy e, ServletRequest request)  {
    	HttpServletRequest req = (HttpServletRequest) request;
		PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
        return service.save(e);
    }

    @PostMapping("/dangky")
    public List<DangKy> findSinhVien(@Valid @RequestBody SinhVien maSinhVien, ServletRequest request)  {
    	List<DangKy> e = service.findByMsv(maSinhVien.getMaSinhVien());
    	return e;
//    	String sql = "SELECT * from bang_diem where msv ='"+msv+"'";
//        String result = service.querySQL(sql); // Trả về danh sách map
//        return ResponseEntity.ok(result);
    }
    // Read operation
//    @GetMapping("/dangky/tb")
//    public List<DangKy> findAll()  {
//        return service.f();
//    }
    @GetMapping(value = "/dangky", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> findAll1(String msv)  {
    	String sql = "SELECT * from bang_diem where msv ='"+msv+"'";
//    	sql = "Select y.msv, y.id AS chon_lich_id from dang_ky_lich_thi_lai y";
        String result = service.querySQL(sql); // Trả về danh sách map
        return ResponseEntity.ok(result);
    }    
    
    @PutMapping("/dangky/{id}")
    public DangKy update(@RequestBody DangKy dangKyLichThiLai, @PathVariable("id") Long Id) {
        return service.update(dangKyLichThiLai, Id);
    }
    // Delete operation
    @DeleteMapping("/dangky/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
