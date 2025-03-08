package com.thanglong.chonlichthilai.dangkylichthilai;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanglong.chonlichthilai.utils.Query;

 
// Annotation
@Service
 
// Class
public class DangKyLichThiLaiServiceImpl implements DangKyLichThiLaiService {
 
    @Autowired
    private DangKyLichThiLaiRepository repository;
    // Save operation
    @Override
    public DangKyLichThiLai save(DangKyLichThiLai e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<DangKyLichThiLai> findAll(){
        return (List<DangKyLichThiLai>) repository.findAll();
    }
    // Read operation
    @Override public DangKyLichThiLai findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Read operation
    @Override public DangKyLichThiLai findByMaSv(String maSv){
        return repository.findByMsv(maSv).orElse(null);
    }
    // Update operation
    @Override
    public DangKyLichThiLai update(DangKyLichThiLai e, Long Id) {
    	DangKyLichThiLai edb= repository.findById(Id).get();
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
    @Override
    public String querySQL(String sql) {
    	Query query = new Query();
    	System.out.println(sql);
    	String str = "";
    	try {
    		str = query.queryDB(sql);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return str;
    }
}