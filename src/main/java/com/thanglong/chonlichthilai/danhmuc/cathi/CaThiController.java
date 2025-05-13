package com.thanglong.chonlichthilai.danhmuc.cathi;

import java.util.List;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.danhmuc.cahoc.CaHocService;
import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
@RequestMapping("/api/v1")
public class CaThiController {
 
    // Annotation
    @Autowired private CaThiService service;
	
    @PostMapping("/cathi")
    public CaThi save(@Valid @RequestBody CaThi e)  {
        return service.save(e);
    }
	
    // Read operation
    @GetMapping("/cathi")
    public List<CaThi> findAll()  {
        return service.findAll();
    }
    @PutMapping("/cathi/{id}")
    public CaThi update(@RequestBody CaThi e, @PathVariable("id") Long Id) {
        return service.update(e, Id);
    }
    // Delete operation
    @DeleteMapping("/cathi/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
