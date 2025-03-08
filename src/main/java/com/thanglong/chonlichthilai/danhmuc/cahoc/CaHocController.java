package com.thanglong.chonlichthilai.danhmuc.cahoc;

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
public class CaHocController {
 
    // Annotation
    @Autowired private CaHocService service;
	
    @PostMapping("/cahoc")
    public CaHoc save(@Valid @RequestBody CaHoc e)  {
        return service.save(e);
    }
	
    // Read operation
    @GetMapping("/cahoc")
    public List<CaHoc> findAll()  {
        return service.findAll();
    }
    @PutMapping("/cahoc/{id}")
    public CaHoc update(@RequestBody CaHoc e, @PathVariable("id") Long Id) {
        return service.update(e, Id);
    }
    // Delete operation
    @DeleteMapping("/cahoc/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
