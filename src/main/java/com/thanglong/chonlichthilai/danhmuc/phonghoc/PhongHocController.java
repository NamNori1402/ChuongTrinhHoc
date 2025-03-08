package com.thanglong.chonlichthilai.danhmuc.phonghoc;

import java.util.List;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1")
public class PhongHocController {
 
    // Annotation
    @Autowired private PhongHocService service;
	
    @PostMapping("/phonghoc")
    public PhongHoc save(@Valid @RequestBody PhongHoc e)  {
        return service.save(e);
    }
	
    // Read operation
    @GetMapping("/phonghoc")
    public List<PhongHoc> findAll()  {
        return service.findAll();
    }
    @PutMapping("/phonghoc/{id}")
    public PhongHoc update(@RequestBody PhongHoc taiKhoan, @PathVariable("id") Long Id) {
        return service.update(taiKhoan, Id);
    }
    // Delete operation
    @DeleteMapping("/phonghoc/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
