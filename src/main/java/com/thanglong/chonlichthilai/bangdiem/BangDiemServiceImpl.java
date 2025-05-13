package com.thanglong.chonlichthilai.bangdiem;

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
public class BangDiemServiceImpl implements BangDiemService {
 
    @Autowired
    private BangDiemRepository repository;
    // Save operation
    @Override
    public BangDiem save(BangDiem e){
        return repository.save(e);
    }
    
    @Override
    public List<BangDiem> saveAll(List<BangDiem> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return repository.saveAll(list);
    }
    
    // Read operation
    @Override public List<BangDiem> findAll(){
        return (List<BangDiem>) repository.findAll();
    }
    // Read operation
    @Override public BangDiem findById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<BangDiem> findByMsv(String msv) {
        System.out.println("Gọi repository.findByMsv với MSV: " + msv);
        List<BangDiem> result = repository.findByMsv(msv);
        System.out.println("Số bản ghi tìm thấy: " + (result != null ? result.size() : "null"));
        return result != null ? result : Collections.emptyList();
    }

    
    
    // Update operation
    @Override
    public BangDiem update(BangDiem e, Long id) {
        Optional<BangDiem> optionalBangDiem = repository.findById(id);
        if (optionalBangDiem.isPresent()) {
            BangDiem existingBangDiem = optionalBangDiem.get();
            existingBangDiem.setMsv(e.getMsv());
            existingBangDiem.setMaHocPhan(e.getMaHocPhan());
            existingBangDiem.setDiemTongKet(e.getDiemTongKet());
            existingBangDiem.setSoLanThi(e.getSoLanThi());
            existingBangDiem.setTime(e.getTime());
            return repository.save(existingBangDiem);
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
    public BangDiem findFirstByMsv(String msv) {
        return repository.findFirstByMsv(msv);
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