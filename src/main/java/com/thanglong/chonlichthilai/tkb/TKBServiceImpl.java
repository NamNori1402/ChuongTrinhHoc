package com.thanglong.chonlichthilai.tkb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTiet;
import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTietRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class TKBServiceImpl implements TKBService {

    @Autowired
    private TKBRepository repository;

    @Autowired
    private TkbChiTietRepository tkbChiTietRepository; // ✅ Initialize repository

    @Override
    public TKB save(TKB e) {
        // Create and save the main TKB entity
        TKB tkb = TKB.builder()
                .ttTkbTruong(e.getTtTkbTruong())
                .tenLop(e.getTenLop())
                .maKy(e.getMaKy())
                .maHocPhan(e.getMaHocPhan())
                .maLopHocPhan(e.getMaLopHocPhan())
                .maGiangVien(e.getMaGiangVien())
                .trangThai(e.getTrangThai())
                .maNguoiNhap(e.getMaNguoiNhap())
                .ghiChu(e.getGhiChu())
                .build();

        TKB savedTKB = repository.save(tkb);

        // ✅ Convert DTOs to TKBChiTiet entities properly
        List<TkbChiTiet> details = e.getTkbChiTietList() != null ? e.getTkbChiTietList().stream().map(
        		detail -> TkbChiTiet.builder()
        		.batDau(detail.getBatDau())
        		.ketThuc(detail.getKetThuc())
        		.loaiHocPhan(detail.getLoaiHocPhan())
        		.thu(detail.getThu())
        		.phong(detail.getPhong())
        		.ghiChuCa(detail.getGhiChuCa())
                .tkb(savedTKB) // Associate with saved TKB
                .build())
                .collect(Collectors.toList())
                : new ArrayList<>(); // Prevent null

        // ✅ Save all details only if the list is not empty
        if (!details.isEmpty()) {
            tkbChiTietRepository.saveAll(details);
        }

        // ✅ Set the list in the main entity correctly
        savedTKB.setTkbChiTietList(details);

        return savedTKB;
    }
    // Read operation
    @Override public List<TKB> findAll(){
    	List<TKB> e = (List<TKB>) repository.findAll();
        return e;
    }
    // Read operation
    @Override public TKB findById(Long id){
        return repository.findById(id).orElse(null);
    }
    @Override public TKB findByTtTkbTruong(Long ttTkbTruong){
        return repository.findByTtTkbTruong(ttTkbTruong).orElse(null);
    }
    // Update operation

    @Override
    public TKB update(TKB e, Long id) {
        TKB tkb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TKB with ID " + id + " not found"));

        // Cập nhật thông tin chính
        tkb.setTtTkbTruong(e.getTtTkbTruong());
        tkb.setTenLop(e.getTenLop());
        tkb.setMaKy(e.getMaKy());
        tkb.setMaHocPhan(e.getMaHocPhan());
        tkb.setMaLopHocPhan(e.getMaLopHocPhan());
        tkb.setMaGiangVien(e.getMaGiangVien());
        tkb.setTrangThai(e.getTrangThai());
        tkb.setMaNguoiNhap(e.getMaNguoiNhap());
        tkb.setGhiChu(e.getGhiChu());
        
        tkb.setNgayThi(e.getNgayThi());
        tkb.setCaThi(e.getCaThi());
        tkb.setPhongThi(e.getPhongThi());
        tkb.setHinhThucThi(e.getHinhThucThi());
        tkb.setMaNguoiCoiThi(e.getMaNguoiCoiThi());
        tkb.setGhiChuLichThi(e.getGhiChuLichThi());

        // Xóa danh sách chi tiết cũ bằng cách duyệt danh sách hiện có
        if (tkb.getTkbChiTietList() != null) {
            tkb.getTkbChiTietList().clear();
        }

        // Cập nhật danh sách chi tiết mới
        if (e.getTkbChiTietList() != null) {
            for (TkbChiTiet detail : e.getTkbChiTietList()) {
                TkbChiTiet newDetail = TkbChiTiet.builder()
                        .batDau(detail.getBatDau())
                        .ketThuc(detail.getKetThuc())
                        .loaiHocPhan(detail.getLoaiHocPhan())
                        .thu(detail.getThu())
                        .phong(detail.getPhong())
                        .ghiChuCa(detail.getGhiChuCa())
                        .tkb(tkb) // Liên kết với TKB
                        .build();
                tkb.getTkbChiTietList().add(newDetail);
            }
        }

        return repository.save(tkb);
    }
    @PersistenceContext
    private EntityManager entityManager1;

    @Transactional
    @Override
    public void saveAllBatch(List<TKB> tkbBatch) {
        int batchSize = 50; // Adjust batch size for performance
        for (int i = 0; i < tkbBatch.size(); i++) {
            entityManager1.persist(tkbBatch.get(i));
            
            // Flush and clear periodically to avoid memory issues
            if (i % batchSize == 0) {
                entityManager1.flush();
                entityManager1.clear();
            }
        }
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    @Override
    public void deleteAllByMaLopHocPhanIn(Set<String> maLopHocPhanSet) {
        Query query = entityManager1.createQuery(
            "DELETE FROM TKB t WHERE t.maLopHocPhan IN :maLopHocPhanSet"
        );
        query.setParameter("maLopHocPhanSet", maLopHocPhanSet);
        query.executeUpdate();
    }
    @Override
    public List<TKB> findByMaKy(String maKy) {
    	return repository.findByMaKy(maKy);
    }
}