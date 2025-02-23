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
    @Autowired private KyService kyService;
 
    // Save operation
    @PostMapping("/ky/")
    public Ky saveKy(@Valid @RequestBody Ky ky)  {
        return kyService.saveKy(ky);
    }
 
    // Read operation
    @GetMapping("/ky")
    public List<Ky> fetchKyList()  {
        return kyService.fetchKyList();
    }
    @GetMapping("/ky/{id}")
    public Ky getKyId(@PathVariable("id") Long kyId)  {
        return kyService.findKyById(kyId);
    }
    @GetMapping("/ky/search")
    public Ky getKyByMaKy(@RequestParam("maKy") String maKy)  {
        return kyService.findKyByMaKy(maKy);
    }
    // Update operation
    @PutMapping("/ky/{id}")
    public Ky updateUser(@RequestBody Ky ky, @PathVariable("id") Long kyId) {
        return kyService.updateKy(ky, kyId);
    }
 
    // Delete operation
    @DeleteMapping("/ky/{id}")
    public String deleteKyById(@PathVariable("id") Long kyId)  {
        kyService.deleteKyById(kyId);
        return "Deleted Successfully";
    }
}
