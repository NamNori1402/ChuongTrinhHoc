package com.thanglong.chonlichthilai.tkb;

import java.util.List;

import org.hibernate.Hibernate;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
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
