package com.thanglong.chonlichthilai.danhmuc.phonghoc;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
// Annotation
@Service
 
// Class
public class PhongHocServiceImpl implements PhongHocService {
 
    @Autowired
    private PhongHocRepository repository;
    // Save operation
    @Override
    public PhongHoc save(PhongHoc e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<PhongHoc> findAll(){
        return (List<PhongHoc>) repository.findAll();
    }
    // Read operation
    @Override public PhongHoc findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Read operation
    @Override public PhongHoc findByTenPhong(String tenPhong){
        return repository.findByTenPhong(tenPhong).orElse(null);
    }
    // Update operation
    @Override
    public PhongHoc update(PhongHoc e, Long Id) {
    	PhongHoc edb= repository.findById(Id).get();
    	edb = e;
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}