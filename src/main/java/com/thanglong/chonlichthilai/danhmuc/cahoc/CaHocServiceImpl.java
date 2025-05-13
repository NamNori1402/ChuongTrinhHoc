package com.thanglong.chonlichthilai.danhmuc.cahoc;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
// Annotation
@Service
 
// Class
public class CaHocServiceImpl implements CaHocService {
 
    @Autowired
    private CaHocRepository repository;
    // Save operation
    @Override
    public CaHoc save(CaHoc e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<CaHoc> findAll(){
        return (List<CaHoc>) repository.findAll();
    }
    // Read operation
    @Override public CaHoc findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Read operation
    @Override public CaHoc findByTenCa(String tenCa){
        return repository.findByTenCa(tenCa).orElse(null);
    }
    // Update operation
    @Override
    public CaHoc update(CaHoc e, Long Id) {
    	CaHoc edb= repository.findById(Id).get();
    	edb = e;
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}