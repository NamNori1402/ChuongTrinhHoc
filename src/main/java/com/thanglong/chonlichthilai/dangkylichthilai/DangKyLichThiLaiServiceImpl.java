package com.thanglong.chonlichthilai.dangkylichthilai;

// Importing required classes
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.entity.CanThiLai;
import com.thanglong.chonlichthilai.entity.ThiLaiResp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

 
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
   
//    public List<DangKyLichThiLai> findByMaKy(String maKy){
//		return repository.findByMaKy(maKy);
//	}
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<ThiLaiResp> findByMaKy(String maKy) {
        String sql = "SELECT " +
                "    x.ma_lop_hoc_phan, x.id, x.ca_thi, x.ngay_thi, x.ma_giang_vien, " +
                "    y.msv, " +
                "    y.id AS chon_lich_id, " +
                "    k.ten, " +
                "    k.lop_chuyen_nganh, " +
                "    k.dien_thoai1 " +
                "FROM tkb x " +
                "INNER JOIN dang_ky_lich_thi_lai y ON x.id = y.tkb_id " +
                "INNER JOIN sinh_vien k ON y.msv = k.ma_sinh_vien " +
                "WHERE x.ma_ky = :maKy";

        Query query = entityManager.createNativeQuery(sql, ThiLaiResp.class);
        query.setParameter("maKy", maKy);
        return query.getResultList();
    }
    @Transactional
    @Override
    public List<CanThiLai> findDsCanThiLai(String maKy) {
        String sql = "Select x.*,y.ten, y.ho_ten_dem,y.lop_chuyen_nganh,y.dien_thoai1 from bang_diem x inner join sinh_vien y on (x.msv = y.ma_sinh_vien)\r\n"
        		+ "where diem_tong_ket < 4 and ma_hoc_phan in (Select ma_hoc_phan from tkb where ma_ky = :maKy)\r\n"
        		+ "order by msv, ma_hoc_phan";
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql, CanThiLai.class);
        query.setParameter("maKy", maKy);
        return query.getResultList();
    }

}