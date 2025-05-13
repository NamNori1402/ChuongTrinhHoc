package com.thanglong.chonlichthilai.hocphan;

import java.util.ArrayList;
import java.util.Collections;
//Importing required classes
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;


//Annotation
@Service

//Class
public class HocPhanServiceImpl implements HocPhanService {

	 @Autowired
	 private HocPhanRepository repository;
	 @Autowired
	 private HocPhanThayTheRepository hocPhanThayTheRepository;
	 // Save operation
	 @Override
	 public HocPhan save(HocPhan e){
	     return repository.save(e);
	 }
	 // Read operation
	 @Override public List<HocPhan> findAll(){
	     return (List<HocPhan>) repository.findAll();
	 }
	 @Override public List<HocPhan> findAllByOrderByMaHocPhanAsc(){
	     return (List<HocPhan>) repository.findAllByOrderByMaHocPhanAsc();
	 }
	
	 // Read operation
	 @Override public HocPhan findById(Long id){
	     return repository.findById(id).orElse(null);
	 }
	 
	 @Override public HocPhan findByMaHocPhan(String maHocPhan){
	     return repository.findByMaHocPhan(maHocPhan).orElse(null);
	 }
	 // Update operation
	 @Override
	 public HocPhan update(HocPhan updated, Long id) {
	     HocPhan existing = repository.findById(id).orElseThrow();

	     // Cập nhật các trường cần thiết, KHÔNG đụng tới danhSachThayThe
	     existing.setMaHocPhan(updated.getMaHocPhan());
	     existing.setTenHocPhan(updated.getTenHocPhan());
	     existing.setSoTinChi(updated.getSoTinChi());
	     existing.setTienQuyet(updated.getTienQuyet());
	     existing.setSoCa(updated.getSoCa());
	     existing.setMaGiangVien(updated.getMaGiangVien());
	     existing.setNganhPhuTrach(updated.getNganhPhuTrach());

	     // ❌ Không set lại danhSachThayThe nếu không có thay đổi

	     return repository.save(existing);
	 }

	 // Delete operation
	 @Override
	 public void deleteById(Long Id){
		 repository.deleteById(Id);
	 }
	 @Override
	 public List<HocPhan> saveAll(List<HocPhan> list) {
	     if (list == null || list.isEmpty()) {
	         return Collections.emptyList();
	     }

	     // Lấy danh sách mã học phần đầu vào
	     List<String> inputMaHocPhan = list.stream()
	         .map(HocPhan::getMaHocPhan)
	         .filter(Objects::nonNull)
	         .map(String::trim)
	         .collect(Collectors.toList());

	     // Tìm các mã học phần đã tồn tại
	     List<HocPhan> existingHocPhan = repository.findAllByMaHocPhanIn(inputMaHocPhan);
	     Set<String> existingMaSet = existingHocPhan.stream()
	         .map(HocPhan::getMaHocPhan)
	         .collect(Collectors.toSet());

	     // Lọc ra các học phần chưa tồn tại
	     List<HocPhan> newHocPhan = list.stream()
	         .filter(hp -> !existingMaSet.contains(hp.getMaHocPhan()))
	         .collect(Collectors.toList());

	     // Nếu không có học phần mới, trả về danh sách rỗng
	     if (newHocPhan.isEmpty()) {
	         return Collections.emptyList();
	     }

	     return repository.saveAll(newHocPhan);
	 }

	 
	 @Override
	 public List<HocPhanWithThayTheDTO> getAllHocPhanWithThayThe() {
	     return repository.findAll().stream()
	         .map(this::convertToHocPhanWithThayTheDTO)
	         .collect(Collectors.toList());
	 }
	 
	 @Override
	 public HocPhanWithThayTheDTO getHocPhanWithThayTheByMa(String maHocPhan) {
	     Optional<HocPhan> optionalHocPhan = repository.findByMaHocPhan(maHocPhan);

	     if (!optionalHocPhan.isPresent()) return null;

	     HocPhan hocPhan = optionalHocPhan.get();

	     HocPhanWithThayTheDTO dto = new HocPhanWithThayTheDTO();
	     dto.setMaHocPhan(hocPhan.getMaHocPhan());
         dto.setMaHocPhanHienTai(hocPhan.getMaHocPhanHienTai());
         dto.setTenHocPhan(hocPhan.getTenHocPhan());	         
         dto.setSoTinChi(hocPhan.getSoTinChi());
         dto.setTienQuyet(hocPhan.getTienQuyet());
         dto.setNganhPhuTrach(hocPhan.getNganhPhuTrach());
         dto.setMaGiangVien(hocPhan.getMaGiangVien());
         dto.setLienLac(hocPhan.getLienLac());

	     List<HocPhanThayTheDTO> dsThayThe = hocPhan.getDanhSachThayThe().stream().map(thayThe -> {
	         HocPhan hpThayThe = thayThe.getHocPhanThayThe();
	         HocPhanThayTheDTO thayTheDTO = new HocPhanThayTheDTO();
	         thayTheDTO.setMaHocPhanGoc(hocPhan.getMaHocPhan());

	         if (hpThayThe != null) {
	             thayTheDTO.setMaHocPhanThayThe(hpThayThe.getMaHocPhan());
	             thayTheDTO.setTenHocPhanThayThe(hpThayThe.getTenHocPhan());
	             thayTheDTO.setSoTinChi(hpThayThe.getSoTinChi());
	         }

	         thayTheDTO.setKhoaApDungTu(thayThe.getKhoaApDungTu());
	         thayTheDTO.setKhoaApDungDen(thayThe.getKhoaApDungDen());
	         return thayTheDTO;
	     }).collect(Collectors.toList());

	     dto.setDanhSachThayThe(dsThayThe);

	     return dto;
	 }
	 
	 @Override
	 public List<HocPhanWithThayTheDTO> searchHocPhanWithThayThe(String keyword) {
	     // Chuẩn hóa keyword
	     String normalizedKeyword = keyword.trim().toLowerCase();
	     
	     // Nếu chỉ nhập 2 ký tự chữ (không có số) -> tìm theo đầu mã
	     if (normalizedKeyword.length() == 2 && normalizedKeyword.matches("[a-z]{2}")) {
	         List<HocPhan> hocPhans = repository.findByMaHocPhanPrefix(normalizedKeyword);
	         return hocPhans.stream()
	             .map(this::convertToHocPhanWithThayTheDTO)
	             .collect(Collectors.toList());
	     }
	     
	     // Ngược lại tìm kiếm mở rộng
	     List<HocPhan> hocPhans = repository.searchHocPhan(normalizedKeyword);
	     return hocPhans.stream()
	         .map(this::convertToHocPhanWithThayTheDTO)
	         .collect(Collectors.toList());
	 }
	 
	 
	 private HocPhanWithThayTheDTO convertToHocPhanWithThayTheDTO(HocPhan hocPhan) {
		    if (hocPhan == null) {
		        return null;
		    }

		    HocPhanWithThayTheDTO dto = new HocPhanWithThayTheDTO();
		    dto.setId(hocPhan.getId());
		    dto.setMaHocPhan(hocPhan.getMaHocPhan());
		    dto.setMaHocPhanHienTai(hocPhan.getMaHocPhanHienTai());
		    dto.setTenHocPhan(hocPhan.getTenHocPhan());
		    dto.setSoTinChi(hocPhan.getSoTinChi());
		    dto.setTienQuyet(hocPhan.getTienQuyet());
		    dto.setNganhPhuTrach(hocPhan.getNganhPhuTrach());
		    dto.setMaGiangVien(hocPhan.getMaGiangVien());
		    dto.setLienLac(hocPhan.getLienLac());

		    // Xử lý danh sách học phần thay thế
		    if (hocPhan.getDanhSachThayThe() != null && !hocPhan.getDanhSachThayThe().isEmpty()) {
		        List<HocPhanThayTheDTO> dsThayThe = hocPhan.getDanhSachThayThe().stream()
		            .map(thayThe -> {
		                HocPhanThayTheDTO thayTheDTO = new HocPhanThayTheDTO();
		                thayTheDTO.setMaHocPhanGoc(hocPhan.getMaHocPhan());
		                
		                HocPhan hpThayThe = thayThe.getHocPhanThayThe();
		                if (hpThayThe != null) {
		                    thayTheDTO.setMaHocPhanThayThe(hpThayThe.getMaHocPhan());
		                    thayTheDTO.setTenHocPhanThayThe(hpThayThe.getTenHocPhan());
		                    thayTheDTO.setSoTinChi(hpThayThe.getSoTinChi());
		                }
		                
		                thayTheDTO.setKhoaApDungTu(thayThe.getKhoaApDungTu());
		                thayTheDTO.setKhoaApDungDen(thayThe.getKhoaApDungDen());
		                
		                return thayTheDTO;
		            })
		            .collect(Collectors.toList());
		        
		        dto.setDanhSachThayThe(dsThayThe);
		    } else {
		        dto.setDanhSachThayThe(Collections.emptyList());
		    }

		    return dto;
		}
}

