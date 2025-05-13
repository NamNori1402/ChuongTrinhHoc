package com.thanglong.chonlichthilai.dangky;

import java.util.Collections;
// Importing required classes
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thanglong.chonlichthilai.utils.Query;


 
// Annotation
@Service
 
// Class
public class DangKyServiceImpl implements DangKyService {
 
    @Autowired
    private DangKyRepository repository;
    // Save operation
    @Override
    public DangKy save(DangKy e){
        return repository.save(e);
    }
    
    @Override
    public List<DangKy> saveAll(List<DangKy> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return repository.saveAll(list);
    }
    
    // Read operation
    @Override public List<DangKy> findAll(){
        return (List<DangKy>) repository.findAll();
    }
    // Read operation
    @Override public DangKy findById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<DangKy> findByMsv(String msv) {
        System.out.println("Gọi repository.findByMsv với MSV: " + msv);
        List<DangKy> result = repository.findByMsv(msv);
        System.out.println("Số bản ghi tìm thấy: " + (result != null ? result.size() : "null"));
        return result != null ? result : Collections.emptyList();
    }

    
    
    // Update operation
    @Override
    public DangKy update(DangKy e, Long id) {
        Optional<DangKy> optionalDangKy = repository.findById(id);
        if (optionalDangKy.isPresent()) {
            DangKy existingDangKy = optionalDangKy.get();
            existingDangKy.setMsv(e.getMsv());
            return repository.save(existingDangKy);
        }
        return null; // hoặc throw exception tùy vào yêu cầu
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
    @Override
    @Transactional
    public void deleteAllByMsv(Set<String> msvSet) {
        if (msvSet != null && !msvSet.isEmpty()) {
            repository.deleteAllByMsv(msvSet);
        }
    }

    @Override
    public DangKy findFirstByMsv(String msv) {
        return repository.findFirstByMsv(msv);
    }
    @Override
    public List<DangKy> deleteAllByMaKy(String maKy) {
        return repository.deleteAllByMaKy(maKy);
    }
    @Override
    public String querySQL(String sql) {
        System.out.println(sql);
        try {
            return new Query().queryDB(sql); // Không nên chạy trực tiếp SQL ở Service
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}