package com.thanglong.chonlichthilai.tkb.chitiet;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


 
// Annotation
@Service
 
// Class
public class TkbChiTietServiceImpl implements TkbChiTietService {
 
    @Autowired
    private TkbChiTietRepository repository;
    // Save operation
    @Override
    public TkbChiTiet save(TkbChiTiet e) {
        return repository.save(e);
    }
    // Read operation
    @Override public List<TkbChiTiet> findAll(){
        return (List<TkbChiTiet>) repository.findAll();
    }
    // Read operation
    @Override public TkbChiTiet findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Update operation
    @Override
    public TkbChiTiet update(TkbChiTiet e, Long Id) {
    	TkbChiTiet tkb= repository.findById(Id).get(); 
        return repository.save(tkb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}