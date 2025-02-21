package com.thanglong.chonlichthilai.tkb;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class TKBController {
 
    // Annotation
    @Autowired private TKBService service;
 
    // Save operation
    @PostMapping("/tkb")
    public TKB save(@Valid @RequestBody TKB e)  {
        return service.save(e);
    }
 
    // Read operation
    @GetMapping("/tkb")
    public List<TKB> fetchList()  {
        return service.fetchList();
    }
    @GetMapping("/tkb/{id}")
    public TKB getId(@PathVariable("id") Long Id)  {
        return service.findById(Id);
    }
    // Update operation
    @PutMapping("/tkb/{id}")
    public TKB update(@RequestBody TKB e, @PathVariable("id") Long Id) {
        return service.update(e, Id);
    }
 
    // Delete operation
    @DeleteMapping("/tkb/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
