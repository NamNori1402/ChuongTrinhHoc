package com.thanglong.chonlichthilai.ky;

import java.util.Date;
//Importing required classes
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;


//Annotation
@Service

//Class
public class KyServiceImpl implements KyService {

 @Autowired
 private KyRepository repository;
 // Save operation
 @Override
 public Ky save(Ky e){
     return repository.save(e);
 }
 // Read operation
 @Override public List<Ky> findAll(){
     return (List<Ky>) repository.findAll();
 }
 @Override public void updateInBulkByMacDinh(Long macDinh){
     repository.updateInBulkByMacDinh(0);
 }
 @Override
 public List<Ky> findAllByOrderByMacDinhDescIdDesc() {
     return repository.findAllByOrderByMacDinhDescIdDesc();
 }
 // Read operation
 @Override public Ky findById(Long id){
     return repository.findById(id).orElse(null);
 }
 
 @Override public Ky findByMaKy(String maKy){
     return repository.findByMaKy(maKy).orElse(null);
 }
 @Override public Ky findByMacDinh(Integer macDinh){
     return repository.findByMacDinh(macDinh).orElse(null);
 }
 
 // Update operation
 @Override
 public Ky update(Ky e, Long id) {
     Optional<Ky> optionalKy = repository.findById(id);
     
     if (optionalKy.isPresent()) {
         Ky k = optionalKy.get();

         // Kiểm tra xem maKy mới đã tồn tại chưa (nếu khác với maKy hiện tại)
         if (!k.getMaKy().equals(e.getMaKy()) && repository.findByMaKy(e.getMaKy()).isPresent()) {
             throw new IllegalArgumentException("Mã kỳ '" + e.getMaKy() + "' đã tồn tại!");
         }

         k.setMaKy(e.getMaKy());
         k.setTenKy(e.getTenKy());
         k.setMaNam(e.getMaNam());
         k.setBatDauKyHoc(e.getBatDauKyHoc());
         k.setKetThucKyHoc(e.getKetThucKyHoc());
         k.setBatDauChonLich(e.getBatDauChonLich());
         k.setKetThucChonLich(e.getKetThucChonLich());
         k.setBatDauLapLich(e.getBatDauLapLich());
         k.setKetThucLapLich(e.getKetThucLapLich());
         k.setLastModify(new Date());
         k.setMacDinh(e.getMacDinh());

         return repository.save(k);
     } else {
         throw new EntityNotFoundException("Không tìm thấy kỳ học với id: " + id);
     }
 }
 // Delete operation
 @Override
 public void deleteById(Long id){
	 repository.deleteById(id);
 }
}

