package com.thanglong.chonlichthilai.dangkylichthilai;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.entity.CanThiLai;
import com.thanglong.chonlichthilai.entity.ThiLaiResp;
import com.thanglong.chonlichthilai.tkb.TKB;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
 
// Annotation
@RestController
 
@RequestMapping("/api/v1")
public class DangKyLichThiLaiController {
 
    // Annotation
    @Autowired private DangKyLichThiLaiService service;
	@Autowired private EmailService emailService;
	
    @PostMapping("/dangkylichthilai")
    public DangKyLichThiLai save(@Valid @RequestBody DangKyLichThiLai e, ServletRequest request)  {
    	HttpServletRequest req = (HttpServletRequest) request;
		PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
		e.setMsv(phienKetNoi.getUserName());
		e.setMaKy(phienKetNoi.getMaKy());
        return service.save(e);
    }
    @PostMapping("/dangkylichthilai/ky")
    public List<ThiLaiResp>  getTKBKy(@Valid @RequestBody DangKyLichThiLai e, ServletRequest request)  {
        List<ThiLaiResp> thiLaiList = service.findByMaKy(e.getMaKy());
        return thiLaiList;
    }
    @PostMapping("/dangkylichthilai/canThiLai")
    public List<CanThiLai>  dsCanThiLai(ServletRequest request)  {
    	HttpServletRequest req = (HttpServletRequest) request;
		PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
        List<CanThiLai> thiLaiList = service.findDsCanThiLai(phienKetNoi.getMaKy());
        return thiLaiList;
    } 
    // Read operation
    @GetMapping("/dangkylichthilai/tb")
    public List<DangKyLichThiLai> findAll()  {
        return service.findAll();
    }
    @GetMapping(value = "/dangkylichthilai", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> findAll1()  {
    	String sql = "SELECT \r\n"
    			+ "    x.ma_lop_hoc_phan, x.id, x.ca_thi,x.ngay_thi,x.ma_giang_vien, \r\n"
    			+ "    y.msv, \r\n"
    			+ "    y.id AS chon_lich_id, \r\n"
    			+ "    k.ten, \r\n"
    			+ "    k.lop_chuyen_nganh,"
    			+ "	   k.dien_thoai1\r\n"
    			+ "FROM tkb x\r\n"
    			+ "INNER JOIN dang_ky_lich_thi_lai y ON x.id = y.tkb_id\r\n"
    			+ "INNER JOIN sinh_vien k ON y.msv = k.ma_sinh_vien;";
//    	sql = "Select y.msv, y.id AS chon_lich_id from dang_ky_lich_thi_lai y";
//        String result = service.querySQL(sql); // Trả về danh sách map
        return ResponseEntity.ok(null);
    }    
    
    @PutMapping("/dangkylichthilai/{id}")
    public DangKyLichThiLai update(@RequestBody DangKyLichThiLai dangKyLichThiLai, @PathVariable("id") Long Id) {
        return service.update(dangKyLichThiLai, Id);
    }
    // Delete operation
    @DeleteMapping("/dangkylichthilai/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
