package com.thanglong.chonlichthilai.ky;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class KyController {
 
    // Annotation
    @Autowired private KyService service;
 
    // Save operation
    @CrossOrigin(origins = "*") 
    @PostMapping("/ky")
    public Ky saveKy(@Valid @RequestBody Ky e)  {
    	if (e.getMacDinh()==1) {
    		service.updateInBulkByMacDinh(Long.parseLong("0"));
    	}
        return service.save(e);
    }
 
    // Read operation
    @GetMapping("/ky")
    public List<Ky> findAllByOrderByIdDesc()  {
        return service.findAllByOrderByMacDinhDescIdDesc();
    }
    @GetMapping("/ky/{id}")
    public Ky getKyId(@PathVariable("id") Long id)  {
        return service.findById(id);
    }
    @GetMapping("/ky/search")
    public Ky getKyByMaKy(@RequestParam("maKy") String e)  {
        return service.findByMaKy(e);
    }
    // Update operation
    @PutMapping("/ky/{id}")
    public Ky updateUser(@RequestBody Ky e, @PathVariable("id") Long id) {
    	if (e.getMacDinh()==1) {
    		service.updateInBulkByMacDinh(Long.parseLong("0"));
    	}
        return service.update(e, id);
    }
 
    // Delete operation
    @DeleteMapping("/ky/{id}")
    public String deleteById(@PathVariable("id") Long id)  {
        service.deleteById(id);
        return "Deleted Successfully";
    }
}
