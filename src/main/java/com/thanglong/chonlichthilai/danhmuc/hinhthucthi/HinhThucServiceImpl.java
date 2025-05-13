package com.thanglong.chonlichthilai.danhmuc.hinhthucthi;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
// Annotation
@Service
 
// Class
public class HinhThucServiceImpl implements HinhThucThiService {
 
    @Autowired
    private HinhThucThiRepository repository;
    // Save operation
    @Override
    public HinhThucThi save(HinhThucThi e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<HinhThucThi> findAll(){
        return (List<HinhThucThi>) repository.findAll();
    }
    // Read operation
    @Override public HinhThucThi findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Read operation
    @Override public HinhThucThi findByTenHinhThucThi(String tenHinhThucThi){
        return repository.findByTenHinhThucThi(tenHinhThucThi).orElse(null);
    }
    // Update operation
    @Override
    public HinhThucThi update(HinhThucThi e, Long Id) {
    	HinhThucThi edb= repository.findById(Id).get();
    	edb =e;
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}