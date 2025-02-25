package com.thanglong.chonlichthilai.sinhvien;

//Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;



//Annotation
@Service

//Class
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhvienRepository;

    // Save operation
    @Override
    public SinhVien saveSinhVien(SinhVien sinhVien) {
        return sinhvienRepository.save(sinhVien);
    }

    // Read operations
    @Override
    public List<SinhVien> fetchSinhVienList() {
        return (List<SinhVien>) sinhvienRepository.findAll();
    }

    @Override
    public SinhVien findSinhVienById(Long id) {
        return sinhvienRepository.findById(id).orElse(null);
    }

    @Override
    public SinhVien findSinhVienByMaSinhVien(String maSinhVien) {
        return sinhvienRepository.findByMaSinhVien(maSinhVien).orElse(null);
    }

    // Update operations
    @Override
    public SinhVien updateSinhVien(SinhVien sinhvien, Long id) {
        Optional<SinhVien> optionalSinhVien = sinhvienRepository.findById(id);
        if (optionalSinhVien.isPresent()) {
            SinhVien existingSinhVien = optionalSinhVien.get();
            existingSinhVien.setUserName(sinhvien.getUserName());
            existingSinhVien.setPassword(sinhvien.getPassword());
            existingSinhVien.setMaSinhVien(sinhvien.getMaSinhVien());
            existingSinhVien.setHoTenDem(sinhvien.getHoTenDem());
            existingSinhVien.setTen(sinhvien.getTen());
            existingSinhVien.setLop(sinhvien.getLop());
            existingSinhVien.setKhoa(sinhvien.getKhoa());
            existingSinhVien.setNganh(sinhvien.getNganh());
            existingSinhVien.setDienThoai(sinhvien.getDienThoai());
            existingSinhVien.setEmail1(sinhvien.getEmail1());
            existingSinhVien.setEmail2(sinhvien.getEmail2());
            existingSinhVien.setGhiChu(sinhvien.getGhiChu());
            // Cập nhật các thuộc tính khác nếu cần
            return sinhvienRepository.save(existingSinhVien);
        } else {
            return null;
        }
    }

    @Override
    public SinhVien updateSinhVienByMaSinhVien(SinhVien sinhvien, String maSinhVien) {
        Optional<SinhVien> optionalSinhVien = sinhvienRepository.findByMaSinhVien(maSinhVien);
        if (optionalSinhVien.isPresent()) {
            SinhVien existingSinhVien = optionalSinhVien.get();
            existingSinhVien.setUserName(sinhvien.getUserName());
            existingSinhVien.setPassword(sinhvien.getPassword());
            existingSinhVien.setMaSinhVien(sinhvien.getMaSinhVien());
            existingSinhVien.setHoTenDem(sinhvien.getHoTenDem());
            existingSinhVien.setTen(sinhvien.getTen());
            existingSinhVien.setLop(sinhvien.getLop());
            existingSinhVien.setKhoa(sinhvien.getKhoa());
            existingSinhVien.setNganh(sinhvien.getNganh());
            existingSinhVien.setDienThoai(sinhvien.getDienThoai());
            existingSinhVien.setEmail1(sinhvien.getEmail1());
            existingSinhVien.setEmail2(sinhvien.getEmail2());
            existingSinhVien.setGhiChu(sinhvien.getGhiChu());
            // Cập nhật các thuộc tính khác nếu cần
            return sinhvienRepository.save(existingSinhVien);
        } else {
            return null;
        }
    }

    // Delete operations
    @Override
    public void deleteSinhVienById(Long id) {
        sinhvienRepository.deleteById(id);
    }

    @Override
    public void deleteSinhVienByMaSinhVien(String maSinhVien) {
        Optional<SinhVien> optionalSinhVien = sinhvienRepository.findByMaSinhVien(maSinhVien);
        optionalSinhVien.ifPresent(sinhVien -> sinhvienRepository.delete(sinhVien));
    }
}

