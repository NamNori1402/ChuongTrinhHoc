package com.thanglong.chonlichthilai.tkb;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.entity.ThoiKhoaBieu;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1")
public class TKBController {
 
    // Annotation
    @Autowired private TKBService service;
 
    // Save operation
    @PostMapping("/tkb")
    public TKB save(@Valid @RequestBody TKB e, ServletRequest request)  {
    	HttpServletRequest req = (HttpServletRequest) request;
		PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
		e.setMaNguoiNhap(phienKetNoi.getUserName());
        return service.save(e);
    }
    @PostMapping("/tkb/ky")
    public List<TKB>  getTKBKy(@Valid @RequestBody TKB e, ServletRequest request)  {
        List<TKB> tkbList = service.findByMaKy(e.getMaKy());
//        tkbList.forEach(tkb -> Hibernate.initialize(tkb.getTkbChiTietList())); // Force initialization
        return tkbList;
    } 
    // Read operation
    @GetMapping("/tkb")
    public List<TKB> findAll() {
        List<TKB> tkbList = service.findAll();
        tkbList.forEach(tkb -> Hibernate.initialize(tkb.getTkbChiTietList())); // Force initialization
        return tkbList;
    }
    
    @GetMapping("/tkb/{id}")
    public TKB getId(@PathVariable("id") Long Id)  {
        return service.findById(Id);
    }
    @PostMapping("/tkb/caNhan")
    public List<ThoiKhoaBieu> getThoiKhoaBieu(@RequestBody Map<String, String> requestBody, ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
        String userName = requestBody.get("userName");
        String doiTuong = requestBody.get("doiTuong");
        List<ThoiKhoaBieu> tkb = service.findByMaGiangVienMaKy(userName, doiTuong, phienKetNoi.getMaKy());
        return tkb;
    }
    // Update operation
    @PutMapping("/tkb/{id}")
    public TKB update(@RequestBody TKB e, @PathVariable("id") Long Id, ServletRequest request) {
    	HttpServletRequest req = (HttpServletRequest) request;
		PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
		e.setMaNguoiNhap(phienKetNoi.getUserName());
        return service.update(e, Id);
    }

    // Delete operation
    @DeleteMapping("/tkb/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
