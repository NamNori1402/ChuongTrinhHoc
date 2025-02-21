package com.thanglong.chonlichthilai.tkb;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
// Annotation
@Service
 
// Class
public class TKBServiceImpl implements TKBService {
 
    @Autowired
    private TKBRepository repository;
    // Save operation
    @Override
    public TKB save(TKB e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<TKB> fetchList(){
        return (List<TKB>) repository.findAll();
    }
    // Read operation
    @Override public TKB findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Update operation
    @Override
    public TKB update(TKB e, Long Id) {
        TKB userDB= repository.findById(Id).get(); 
        userDB = e;
        return repository.save(userDB);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}