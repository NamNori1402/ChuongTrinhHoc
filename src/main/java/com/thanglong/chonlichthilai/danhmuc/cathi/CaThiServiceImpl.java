package com.thanglong.chonlichthilai.danhmuc.cathi;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
// Annotation
@Service
 
// Class
public class CaThiServiceImpl implements CaThiService {
 
    @Autowired
    private CaThiRepository repository;
    // Save operation
    @Override
    public CaThi save(CaThi e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<CaThi> findAll(){
        return (List<CaThi>) repository.findAll();
    }
    // Read operation
    @Override public CaThi findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Read operation
    @Override public CaThi findByTenCa(String tenCa){
        return repository.findByTenCa(tenCa).orElse(null);
    }
    // Update operation
    @Override
    public CaThi update(CaThi e, Long Id) {
    	CaThi edb= repository.findById(Id).get();
    	edb = e;
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}