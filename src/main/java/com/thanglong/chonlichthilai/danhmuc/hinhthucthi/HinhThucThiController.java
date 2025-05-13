package com.thanglong.chonlichthilai.danhmuc.hinhthucthi;

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
public class HinhThucThiController {
 
    // Annotation
    @Autowired private HinhThucThiService service;
	
    @PostMapping("/hinhthucthi")
    public HinhThucThi save(@Valid @RequestBody HinhThucThi e)  {
        return service.save(e);
    }
	
    // Read operation
    @GetMapping("/hinhthucthi")
    public List<HinhThucThi> findAll()  {
        return service.findAll();
    }
    @PutMapping("/hinhthucthi/{id}")
    public HinhThucThi update(@RequestBody HinhThucThi e, @PathVariable("id") Long Id) {
        return service.update(e, Id);
    }
    // Delete operation
    @DeleteMapping("/hinhthucthi/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
