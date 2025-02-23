package com.thanglong.chonlichthilai.taikhoan;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
// Annotation
@Service
 
// Class
public class GiangVienServiceImpl implements GiangVienService {
 
    @Autowired
    private TaiKhoanRepository repository;
    // Save operation
    @Override
    public GiangVien save(GiangVien e){
        return repository.save(e);
    }
    // Read operation
    @Override public List<GiangVien> fetchList(){
        return (List<GiangVien>) repository.findAll();
    }
    // Read operation
    @Override public GiangVien findById(Long id){
        return repository.findById(id).orElse(null);
    }
    // Read operation
    @Override public GiangVien findByUserName(String userName){
        return repository.findByUserName(userName).orElse(null);
    }
    // Update operation
    @Override
    public GiangVien update(GiangVien e, Long Id) {
    	GiangVien edb= repository.findById(Id).get();
    	edb.setDienThoai(e.getDienThoai());
    	edb.setEmail1(e.getEmail1());
    	edb.setEmail2(e.getEmail2());
    	edb.setHoTenDem(e.getHoTenDem());
    	edb.setDonVi(e.getDonVi());
    	edb.setGhiChu(e.getGhiChu());
    	edb.setTen(e.getTen());
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}