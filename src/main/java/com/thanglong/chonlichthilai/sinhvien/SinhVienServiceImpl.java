package com.thanglong.chonlichthilai.sinhvien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//Importing required classes
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.bangdiem.BangDiemRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_HocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhanChiTiet;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMon;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMonChiTiet;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHocRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHoc_HocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.utils.ChuongTrinhHocUtils;
import com.thanglong.chonlichthilai.dangky.DangKy;
import com.thanglong.chonlichthilai.dangky.DangKyRepository;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.hocphan.HocPhanRepository;
import com.thanglong.chonlichthilai.hocphan.HocPhanThayThe;
import com.thanglong.chonlichthilai.hocphan.HocPhanThayTheDTO;
import com.thanglong.chonlichthilai.hocphan.HocPhanThayTheRepository;

import lombok.Builder;


//Annotation
@Service

//Class
public class SinhVienServiceImpl implements SinhVienService {

	 @Autowired
	 private SinhVienRepository repository;
	 @Autowired
	 private HocPhanRepository hocPhanRepository;

	 // Save operation
	 @Override
	 public SinhVien save(SinhVien e){
	     return repository.save(e);
	 }
	 @Override
	 public List<SinhVien> saveAll(List<SinhVien> list) {
	     if (list == null || list.isEmpty()) {
	         return Collections.emptyList();
	     }
	     return repository.saveAll(list);
	 }
	 // Read operation
	 @Override public List<SinhVien> findAll(){
	     return (List<SinhVien>) repository.findAll();
	 }
	 @Override
	 public SinhVien findFirstByMaSinhVien(String maSinhVien) {
	     return repository.findFirstByMaSinhVien(maSinhVien);
	 }
	 // Read operation
	 @Override public SinhVien findById(Long id){
	     return repository.findById(id).orElse(null);
	 }
	 
	 @Override public SinhVien findByMaSinhVien(String maSinhVien){
	     return repository.findByMaSinhVien(maSinhVien).orElse(null);
	 }
	 // Update operation
	 @Override
	 public SinhVien update(SinhVien e, Long Id) {
		 SinhVien sinhVien= repository.findById(Id).get();
		 sinhVien.setDienThoai1(e.getDienThoai1());
		 sinhVien.setDienThoai2(e.getDienThoai2());
		 sinhVien.setEmail1(e.getEmail1());
		 sinhVien.setEmail2(e.getEmail2());
		 sinhVien.setTen(e.getTen());
		 sinhVien.setHoTenDem(e.getHoTenDem());
		 sinhVien.setTrangThai(e.getTrangThai());
		 sinhVien.setGhiChu(e.getGhiChu());
	     return repository.save(sinhVien);
	 }
	 // Delete operation
	 @Override
	 public void deleteById(Long Id){
		 repository.deleteById(Id);
	 }
	 @Override
	 public void deleteByMaSinhVien(String maSinhVien){
		 repository.deleteByMaSinhVien(maSinhVien);
	 }
	 
	 @Autowired
	 private ChuongTrinhHocRepository chuongTrinhHocRepository;
	 @Autowired
	 private BangDiemRepository bangDiemRepository;	
	 @Autowired
	 private DangKyRepository dangKyRepository;
	 @Autowired
	 private SinhVienRepository sinhVienRepository;
	 @Autowired
	 private ChuongTrinhHoc_HocPhanRepository chuongTrinhHocHocPhanRepository;	
	 @Autowired
	 private HocPhanThayTheRepository hocPhanThayTheRepository;	
	 
	 @Override
	 public Optional<ChuongTrinhHoc> findChuongTrinhHocCuaSinhVien(String maSinhVien) {
	     SinhVien sv = findByMaSinhVien(maSinhVien);
	     if (sv == null) return Optional.empty();
	
	     String chuyenNganh = ChuongTrinhHocUtils.extractTenChuyenNganhTuNganh(sv.getNganh());
	     String khoa = "K" + sv.getKhoa(); // ✅ Chuẩn hóa lại đúng định dạng
	
	 System.out.println("➡️ Tìm chương trình học với chuyên ngành: " + chuyenNganh + ", khóa: " + khoa);
	
	     return chuongTrinhHocRepository.findByChuyenNganhAndKhoa(chuyenNganh, khoa);
	 }
	
	 @Override
	 public List<LoaiHocPhanConThieuDTO> getHocPhanConThieu(String maSinhVien) { //chỉ kiểm tra các học phần chưa học hoặc đang học chưa có điểm.
		    SinhVien sv = sinhVienRepository.findFirstByMaSinhVien(maSinhVien);
		    if (sv == null) throw new RuntimeException("Không tìm thấy sinh viên");
		       
		    List<HocPhan> hocPhanList = hocPhanRepository.findAll();
		    Map<String, HocPhan> hocPhanMap = hocPhanList.stream()
		        .collect(Collectors.toMap(HocPhan::getMaHocPhan, Function.identity()));

		    
		    String tenChuyenNganh = ChuongTrinhHocUtils.extractTenChuyenNganhTuNganh(sv.getNganh());
		    String khoa = "K" + sv.getKhoa();

		    ChuongTrinhHoc chuongTrinh = chuongTrinhHocRepository
		        .findByChuyenNganhAndKhoa(tenChuyenNganh, khoa)
		        .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình học"));


		    List<BangDiem> bangDiems = bangDiemRepository.findByMsv(maSinhVien);

		    Map<String, Float> diemTongKetTheoMa = bangDiems.stream()
		    	    .collect(Collectors.toMap(BangDiem::getMaHocPhan, BangDiem::getDiemTongKet, (a, b) -> a));
		    
		    int khoaSinhVien;
		    try {
		        khoaSinhVien = Integer.parseInt(sv.getKhoa());
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("Khoa sinh viên không hợp lệ: " + sv.getKhoa());
		    }
		    
		 // Tạo map học phần thay thế: mã gốc -> danh sách học phần thay thế
		    Map<String, List<HocPhanThayTheDTO>> hocPhanThayTheMap = hocPhanThayTheRepository.findAll().stream()
		        .collect(Collectors.groupingBy(
		            thayThe -> thayThe.getHocPhanGoc().getMaHocPhan(),
		            Collectors.mapping(
		                thayThe -> HocPhanThayTheDTO.builder()
		                    .maHocPhanGoc(thayThe.getHocPhanGoc().getMaHocPhan())
		                    .maHocPhanThayThe(thayThe.getHocPhanThayThe().getMaHocPhan())
		                    .tenHocPhanThayThe(thayThe.getHocPhanThayThe().getTenHocPhan())
		                    .soTinChi(thayThe.getHocPhanThayThe().getSoTinChi())
		                    .khoaApDungTu(thayThe.getKhoaApDungTu())
		                    .khoaApDungDen(thayThe.getKhoaApDungDen())
		                    .build(),
		                Collectors.toList()
		            )
		        ));
		    Map<String, String> maThayTheToGoc = new HashMap<>();
	        for (HocPhanThayThe thayThe : hocPhanThayTheRepository.findAll()) {
	            if (thayThe.getKhoaApDungTu() != null && thayThe.getKhoaApDungTu() > khoaSinhVien) continue;
	            if (thayThe.getKhoaApDungDen() != null && thayThe.getKhoaApDungDen() < khoaSinhVien) continue;
	            maThayTheToGoc.put(
	                thayThe.getHocPhanThayThe().getMaHocPhan(),
	                thayThe.getHocPhanGoc().getMaHocPhan()
	            );
	        }

		    // Các mã học phần đã tích lũy 
		    Set<String> hocPhanQua = diemTongKetTheoMa.entrySet().stream()
		        .filter(entry -> entry.getValue() >= 0 )
		        .map(Map.Entry::getKey)
		        .collect(Collectors.toSet());
		    			    
		    // Thêm các mã gốc tương ứng nếu sinh viên đã học mã thay thế
		    Set<String> hocPhanQuaDayDu = new HashSet<>(hocPhanQua);
		    for (String ma : hocPhanQua) {
		        if (maThayTheToGoc.containsKey(ma)) {
		            hocPhanQuaDayDu.add(maThayTheToGoc.get(ma)); // thêm mã gốc tương ứng nếu học mã thay thế
		        }
		    }

		    List<DangKy> dangKyList = dangKyRepository.findByMsv(maSinhVien);
		    Set<String> hocPhanDangHoc = dangKyList.stream()
		        .map(DangKy::getMaHocPhan)
		        .collect(Collectors.toSet());

		    Set<String> hocPhanDaHocOrDangHoc = new HashSet<>(hocPhanQuaDayDu);
		    		// ✅ Chỉ thêm học phần đang học nếu chưa có điểm
		    for (String maDangHoc : hocPhanDangHoc) {
		        if (!diemTongKetTheoMa.containsKey(maDangHoc)) {
		            hocPhanDaHocOrDangHoc.add(maDangHoc);
		         // Nếu là học phần thay thế, thêm mã gốc tương ứng
		            if (maThayTheToGoc.containsKey(maDangHoc)) {
		                hocPhanDaHocOrDangHoc.add(maThayTheToGoc.get(maDangHoc));
		            }
		        }
		    }
		    
	        // Lọc học phần thay thế hợp lệ
	        for (Map.Entry<String, List<HocPhanThayTheDTO>> entry : hocPhanThayTheMap.entrySet()) {
	            List<HocPhanThayTheDTO> filtered = entry.getValue().stream()
	                .filter(dto -> (dto.getKhoaApDungTu() == null || khoaSinhVien >= dto.getKhoaApDungTu()) &&
	                               (dto.getKhoaApDungDen() == null || khoaSinhVien <= dto.getKhoaApDungDen()))
	                .collect(Collectors.toList());
	            hocPhanThayTheMap.put(entry.getKey(), filtered);
	        }    
	        
		    List<LoaiHocPhanConThieuDTO> ketQua = new ArrayList<>();
		    Set<LoaiHocPhan> loaiHocPhanConThieu = new HashSet<>();

		    for (ChuongTrinhHoc_LoaiHocPhan lhp : chuongTrinh.getCacLoaiHocPhan()) {
		        List<HocPhanConThieuDTO> listLoai = new ArrayList<>();

		        for (ToHopHocPhan toHop : lhp.getToHops()) {
		        	List<ToHopHocPhanChiTiet> chiTietList = Optional.ofNullable(toHop.getChiTietList()).orElse(List.of());

		            // Nếu tổ hợp có nhóm môn thì ưu tiên xử lý theo nhóm môn
		            List<ToHopNhomMon> nhomMons = Optional.ofNullable(toHop.getToHopNhomMons()).orElse(List.of());
		            if (!nhomMons.isEmpty()) {
		            	boolean daHoanThanhMotNhom = nhomMons.stream()
		            		    .anyMatch(nhom -> nhom.getChiTietMonHoc().stream()
		            		        .allMatch(ct -> hocPhanDaHocOrDangHoc.contains(ct.getHocPhan().getMaHocPhan())));

		            		if (!daHoanThanhMotNhom) {
		            		    listLoai.add(HocPhanConThieuDTO.builder()
		            		        .loai("Tổ hợp: " + toHop.getTenToHop())
		            		        .noiDung("Chưa hoàn thành một nhóm môn bất kỳ trong tổ hợp " + toHop.getTenToHop())
		            		        .ghiChu("Cần học đủ toàn bộ học phần của một nhóm môn bất kỳ")
		            		        .build());
		            		}
		                continue;
		            }

		            if (Boolean.TRUE.equals(toHop.getBatBuocHocTatCa())) {
		            	for (ToHopHocPhanChiTiet chiTiet : chiTietList) {
		                    HocPhan hp = chiTiet.getHocPhan();		                
		                    if (!hocPhanDaHocOrDangHoc.contains(hp.getMaHocPhan())) {
		                        HocPhanConThieuDTO dto = HocPhanConThieuDTO.builder()
		                            .loai("Tổ hợp: " + toHop.getTenToHop())
		                            .noiDung(hp.getTenHocPhan())
		                            .ghiChu("Thiếu học phần này trong tổ hợp" + toHop.getTenToHop())
		                            .id(hp.getId())
		                            .maHocPhan(hp.getMaHocPhan())
		                            .tenHocPhan(hp.getTenHocPhan())
		                            .tienQuyet(hp.getTienQuyet())
		                            .nganhPhuTrach(hp.getNganhPhuTrach())
		                            .lienLac(hp.getLienLac())
		                            .maGiangVien(hp.getMaGiangVien())
		                            .moRong(hp.getMoRong())
		                            .soTinChi(hp.getSoTinChi())
		                            .soCa(hp.getSoCa())
		                            .build();
		                        
		                        // Thêm danh sách học phần thay thế nếu có
		                        if (hocPhanThayTheMap.containsKey(hp.getMaHocPhan())) {
		                            dto.setDanhSachThayThe(hocPhanThayTheMap.get(hp.getMaHocPhan()));
		                        }
		                        
		                        listLoai.add(dto);
		                    }
		                }
		            } else if (toHop.getSoTinChiToiThieu() != null) {
		                int tongTinChiDaDat = chiTietList.stream()
		                    .filter(ct -> hocPhanDaHocOrDangHoc.contains(ct.getHocPhan().getMaHocPhan()))
		                    .mapToInt(ct -> Optional.ofNullable(ct.getHocPhan().getSoTinChi()).orElse(0))
		                    .sum();

		                if (tongTinChiDaDat < toHop.getSoTinChiToiThieu()) {
		                    int thieu = toHop.getSoTinChiToiThieu() - tongTinChiDaDat;
		                    listLoai.add(HocPhanConThieuDTO.builder()
		                        .loai("Tổ hợp: " + toHop.getTenToHop())
		                        .noiDung("Thiếu " + thieu + " tín chỉ trong tổ hợp "+ toHop.getTenToHop())
		                        .ghiChu("Đã tích lũy " + tongTinChiDaDat + " / " + toHop.getSoTinChiToiThieu() + " tín chỉ tối thiểu")
		                        .build());
		                }
		            } else if (toHop.getSoLuongMonToiThieu() != null) {
		                long soLuongDaHoc = chiTietList.stream()
		                    .filter(ct -> hocPhanDaHocOrDangHoc.contains(ct.getHocPhan().getMaHocPhan()))
		                    .count();

		                if (soLuongDaHoc < toHop.getSoLuongMonToiThieu()) {
		                    long thieu = toHop.getSoLuongMonToiThieu() - soLuongDaHoc;
		                    listLoai.add(HocPhanConThieuDTO.builder()
		                        .loai("Tổ hợp: " + toHop.getTenToHop())
		                        .noiDung("Thiếu " + thieu + " học phần trong tổ hợp " + toHop.getTenToHop())
		                        .ghiChu("Đã học " + soLuongDaHoc + " / " + toHop.getSoLuongMonToiThieu() + " học phần")
		                        .build());
		                }
		            }
		        }

		        if (lhp.getSoTinChiToiThieu() != null && lhp.getMaBatDauDuocTinh() != null) {
		            int tongTinChi = 0;

		            // Các học phần thuộc loại này trong chương trình học
		            List<ChuongTrinhHoc_HocPhan> hpLoaiNay = lhp.getHocPhans();
		            
		            // Các mã học phần đã nằm trong loại học phần khác (dùng để loại trừ)
		            Set<String> maHpLoaiKhac = chuongTrinh.getCacLoaiHocPhan().stream()
		                .filter(l -> !l.getId().equals(lhp.getId()))
		                .flatMap(l -> l.getHocPhans().stream())
		                .map(cthp -> cthp.getHocPhan().getMaHocPhan())
		                .collect(Collectors.toSet());
		            
		            if (lhp.getMaBatDauDuocTinh() != null && !lhp.getMaBatDauDuocTinh().isBlank()) {
		            	// Mỗi prefix (maBatDauDuocTinh) phân tách bằng dấu phẩy
			            for (String prefix : lhp.getMaBatDauDuocTinh().split(",")) {
			                final String currentPrefix = prefix.trim(); // Tạo biến final local
			                
			                // Học phần đã học (có điểm)
			                tongTinChi += bangDiems.stream()
			                	.filter(bd -> bd.getDiemTongKet() >= 0)
			                    .map(BangDiem::getMaHocPhan)
			                    .filter(ma -> ma.startsWith(currentPrefix))
			                    .filter(ma -> !maHpLoaiKhac.contains(ma)) // loại trừ học phần đã thuộc loại khác
			                    .filter(ma -> hpLoaiNay.stream().noneMatch(cthp -> cthp.getHocPhan().getMaHocPhan().equals(ma))) // tránh trùng với học phần thuộc loại này
			                    .mapToInt(ma -> {
			                        BangDiem bd = bangDiems.stream().filter(b -> b.getMaHocPhan().equals(ma)).findFirst().orElse(null);
			                        return (bd != null) ? bd.getSoTinChi() : 0;
			                    })
			                    .sum();
			                
			               
			             // Học phần đang học (trong danh sách đăng ký)
			                tongTinChi += dangKyList.stream()
			                    .map(DangKy::getMaHocPhan)
			                    .filter(ma -> ma.startsWith(currentPrefix))
			                    .filter(ma -> !maHpLoaiKhac.contains(ma)) // loại trừ học phần đã thuộc loại khác
			                    .filter(ma -> hpLoaiNay.stream().noneMatch(cthp -> cthp.getHocPhan().getMaHocPhan().equals(ma))) // tránh trùng
			                    .filter(ma -> !hocPhanQuaDayDu.contains(ma)) // không đếm lại học phần đã học
			                    .mapToInt(ma -> {
			                        HocPhan hp = hocPhanMap.get(ma);
			                        return (hp != null) ? hp.getSoTinChi() : 0;
			                    })
			                    .sum();

			            }
		            }		            

		            if (tongTinChi < lhp.getSoTinChiToiThieu()) {
		                loaiHocPhanConThieu.add(lhp.getLoaiHocPhan());
		            }
		        }

		        else {
		            // ❗ Nếu không có điều kiện tín chỉ tối thiểu thì xử lý từng học phần như cũ
		            List<ChuongTrinhHoc_HocPhan> hocPhanNgoaiToHop = chuongTrinhHocHocPhanRepository
		                .findByChuongTrinhHocIdAndLoaiHocPhanAndToHopIsNull(chuongTrinh.getId(), lhp.getLoaiHocPhan());

		            for (ChuongTrinhHoc_HocPhan wrapper : hocPhanNgoaiToHop) {
		                HocPhan hp = wrapper.getHocPhan();		             
		                if (!hocPhanDaHocOrDangHoc.contains(hp.getMaHocPhan())) {
		                    HocPhanConThieuDTO dto = HocPhanConThieuDTO.builder()
		                        .loai("Học phần ngoài tổ hợp")
		                        .noiDung(hp.getTenHocPhan())
		                        .ghiChu("Chưa học")
		                        .id(hp.getId())
		                        .maHocPhan(hp.getMaHocPhan())
		                        .tenHocPhan(hp.getTenHocPhan())
		                        .tienQuyet(hp.getTienQuyet())
		                        .nganhPhuTrach(hp.getNganhPhuTrach())
		                        .lienLac(hp.getLienLac())
		                        .maGiangVien(hp.getMaGiangVien())
		                        .moRong(hp.getMoRong())
		                        .soTinChi(hp.getSoTinChi())
		                        .soCa(hp.getSoCa())
		                        .build();
		                    
		                    // Thêm danh sách học phần thay thế nếu có
		                    if (hocPhanThayTheMap.containsKey(hp.getMaHocPhan())) {
		                        dto.setDanhSachThayThe(hocPhanThayTheMap.get(hp.getMaHocPhan()));
		                    }
		                    
		                    listLoai.add(dto);
		                }
		            }
		        }
		        
		        if (!listLoai.isEmpty()) {
		            ketQua.add(LoaiHocPhanConThieuDTO.builder()
		                .tenLoaiHocPhan(lhp.getLoaiHocPhan().getDisplayName())
		                .hocPhansConThieu(listLoai)
		                .build());
		        }
		    }
		    return ketQua;
		}

}

