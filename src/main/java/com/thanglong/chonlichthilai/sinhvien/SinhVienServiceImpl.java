package com.thanglong.chonlichthilai.sinhvien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
//Importing required classes
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHocRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHoc_HocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.utils.ChuongTrinhHocUtils;
import com.thanglong.chonlichthilai.dangky.DangKy;
import com.thanglong.chonlichthilai.dangky.DangKyRepository;
import com.thanglong.chonlichthilai.hocphan.HocPhan;

import lombok.Builder;


//Annotation
@Service

//Class
public class SinhVienServiceImpl implements SinhVienService {

	 @Autowired
	 private SinhVienRepository repository;
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
	
	 @Override
	 public Optional<ChuongTrinhHoc> findChuongTrinhHocCuaSinhVien(String maSinhVien) {
	     SinhVien sv = findByMaSinhVien(maSinhVien);
	     if (sv == null) return Optional.empty();
	
	     String chuyenNganh = ChuongTrinhHocUtils.extractTenChuyenNganhTuNganh(sv.getNganh());
	     String khoa = "K" + sv.getKhoa(); // ✅ Chuẩn hóa lại đúng định dạng
	
	 System.out.println("➡️ Tìm chương trình học với chuyên ngành: " + chuyenNganh + ", khóa: " + khoa);
	
	     return chuongTrinhHocRepository.findByChuyenNganhAndKhoa(chuyenNganh, khoa);
	 }
	
	 @Autowired
	 private BangDiemRepository bangDiemRepository;
	
	 @Autowired
	 private DangKyRepository dangKyRepository;
	
	 @Override
	 public Set<String> getHocPhanDaQua(String maSinhVien) {
	     List<BangDiem> diemList = bangDiemRepository.findByMsv(maSinhVien);
	     return diemList.stream()
	         .filter(bd -> bd.getDiemTongKet() >= 4 ) //&& (bd.getSoLanThi() == null || bd.getSoLanThi() <= 2)
	         .map(BangDiem::getMaHocPhan)
	         .collect(Collectors.toSet());
	 }
	 @Autowired
	 private SinhVienRepository sinhVienRepository;
	 @Autowired
	 private ChuongTrinhHoc_HocPhanRepository chuongTrinhHocHocPhanRepository;
	
	 @Override
	 public Set<String> getHocPhanDangHoc(String maSinhVien) {
	     List<DangKy> dangKyList = dangKyRepository.findByMsv(maSinhVien);
	     return dangKyList.stream()
	         .map(DangKy::getMaHocPhan)
	         .collect(Collectors.toSet());
	 }
	
	 @Override
	 public List<LoaiHocPhanConThieuDTO> getHocPhanConThieu(String maSinhVien) {
		    SinhVien sv = sinhVienRepository.findFirstByMaSinhVien(maSinhVien);
		    if (sv == null) throw new RuntimeException("Không tìm thấy sinh viên");

		    String tenChuyenNganh = ChuongTrinhHocUtils.extractTenChuyenNganhTuNganh(sv.getNganh());
		    String khoa = "K" + sv.getKhoa();

		    ChuongTrinhHoc chuongTrinh = chuongTrinhHocRepository
		        .findByChuyenNganhAndKhoa(tenChuyenNganh, khoa)
		        .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình học"));

		    List<BangDiem> bangDiems = bangDiemRepository.findByMsv(maSinhVien);
		    Set<String> hocPhanQua = bangDiems.stream()
		        .filter(d -> d.getDiemTongKet() >= 4)
		        .map(BangDiem::getMaHocPhan)
		        .collect(Collectors.toSet());

		    List<DangKy> dangKyList = dangKyRepository.findByMsv(maSinhVien);
		    Set<String> hocPhanDangHoc = dangKyList.stream()
		        .map(DangKy::getMaHocPhan)
		        .collect(Collectors.toSet());

		    Set<String> hocPhanDaHocOrDangHoc = new HashSet<>(hocPhanQua);
		    hocPhanDaHocOrDangHoc.addAll(hocPhanDangHoc);

		    List<LoaiHocPhanConThieuDTO> ketQua = new ArrayList<>();

		    for (ChuongTrinhHoc_LoaiHocPhan lhp : chuongTrinh.getCacLoaiHocPhan()) {
		        List<HocPhanConThieuDTO> listLoai = new ArrayList<>();

		        for (ToHopHocPhan toHop : lhp.getToHops()) {
		            List<ToHopHocPhanChiTiet> chiTietList = Optional.ofNullable(toHop.getChiTietList()).orElse(List.of());

		            // Nếu tổ hợp có nhóm môn thì ưu tiên xử lý theo nhóm môn
		            List<ToHopNhomMon> nhomMons = Optional.ofNullable(toHop.getToHopNhomMons()).orElse(List.of());
		            if (!nhomMons.isEmpty()) {
		                boolean chuaHoanThanhNhomNao = nhomMons.stream()
		                    .anyMatch(nhom -> nhom.getChiTietMonHoc().stream()
		                        .anyMatch(ct -> !hocPhanDaHocOrDangHoc.contains(ct.getHocPhan().getMaHocPhan())));

		                if (chuaHoanThanhNhomNao) {
		                    listLoai.add(HocPhanConThieuDTO.builder()
		                        .loai("Tổ hợp: " + toHop.getTenToHop())
		                        .noiDung("Chưa hoàn thành một nhóm môn bất kỳ trong tổ hợp " + toHop.getTenToHop())
		                        .ghiChu("Cần học đủ toàn bộ học phần của một nhóm môn bất kì")
		                        .build());
		                }

		                continue;
		            }

		            if (Boolean.TRUE.equals(toHop.getBatBuocHocTatCa())) {
		                for (ToHopHocPhanChiTiet chiTiet : chiTietList) {
		                    HocPhan hp = chiTiet.getHocPhan();
		                    if (!hocPhanDaHocOrDangHoc.contains(hp.getMaHocPhan())) {
		                        listLoai.add(HocPhanConThieuDTO.builder()
		                            .loai("Tổ hợp: " + toHop.getTenToHop())
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
		                            .monThayThe(hp.getMonThayThe())
		                            .maMonThayThe(hp.getMaMonThayThe())
		                            .soCa(hp.getSoCa())
		                            .build());
		                    }
		                }
		            } else if (toHop.getSoTinChiToiThieu() != null) {
		                int tongTinChiDaDat = chiTietList.stream()
		                    .filter(ct -> hocPhanQua.contains(ct.getHocPhan().getMaHocPhan()))
		                    .mapToInt(ct -> Optional.ofNullable(ct.getHocPhan().getSoTinChi()).orElse(0))
		                    .sum();

		                if (tongTinChiDaDat < toHop.getSoTinChiToiThieu()) {
		                    int thieu = toHop.getSoTinChiToiThieu() - tongTinChiDaDat;
		                    listLoai.add(HocPhanConThieuDTO.builder()
		                        .loai("Tổ hợp: " + toHop.getTenToHop())
		                        .noiDung("Thiếu " + thieu + " tín chỉ trong tổ hợp"+ toHop.getTenToHop())
		                        .ghiChu("Đã tích lũy " + tongTinChiDaDat + " / " + toHop.getSoTinChiToiThieu() + " tín chỉ tối thiểu")
		                        .build());
		                }
		            } else if (toHop.getSoLuongMonToiThieu() != null) {
		                long soLuongDaHoc = chiTietList.stream()
		                    .filter(ct -> hocPhanQua.contains(ct.getHocPhan().getMaHocPhan()))
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

		        // ✅ Xử lý điều kiện số tín chỉ tối thiểu cho loại học phần
		        if (lhp.getSoTinChiToiThieu() != null && lhp.getSoTinChiToiThieu() > 0) {
		            List<ChuongTrinhHoc_HocPhan> tatCaHpTrongCT = chuongTrinhHocHocPhanRepository.findByChuongTrinhHocId(chuongTrinh.getId());

		            List<ChuongTrinhHoc_HocPhan> hpLoaiNay = tatCaHpTrongCT.stream()
		                .filter(cthp -> cthp.getLoaiHocPhan().equals(lhp.getLoaiHocPhan()))
		                .collect(Collectors.toList());

		            Set<String> maHpLoaiKhac = tatCaHpTrongCT.stream()
		                .filter(cthp -> !cthp.getLoaiHocPhan().equals(lhp.getLoaiHocPhan()))
		                .map(cthp -> cthp.getHocPhan().getMaHocPhan())
		                .collect(Collectors.toSet());

		            int tongTinChi = hpLoaiNay.stream()
		                .filter(cthp -> hocPhanQua.contains(cthp.getHocPhan().getMaHocPhan()))
		                .mapToInt(cthp -> Optional.ofNullable(cthp.getHocPhan().getSoTinChi()).orElse(0))
		                .sum();

		            if (lhp.getMaBatDauDuocTinh() != null && !lhp.getMaBatDauDuocTinh().isBlank()) {
		                String prefix = lhp.getMaBatDauDuocTinh();

		                tongTinChi += hocPhanQua.stream()
		                    .filter(ma -> ma.startsWith(prefix))
		                    .filter(ma -> !maHpLoaiKhac.contains(ma))
		                    .map(ma -> bangDiems.stream()
		                        .filter(bd -> bd.getMaHocPhan().equals(ma))
		                        .findFirst()
		                        .map(BangDiem::getSoTinChi)
		                        .orElse(0))
		                    .mapToInt(Integer::intValue)
		                    .sum();
		            }

		            if (tongTinChi < lhp.getSoTinChiToiThieu()) {
		                listLoai.add(HocPhanConThieuDTO.builder()
		                    .loai("Loại học phần: " + lhp.getLoaiHocPhan().getDisplayName())
		                    .noiDung("Thiếu " + (lhp.getSoTinChiToiThieu() - tongTinChi) + " tín chỉ")
		                    .ghiChu("Đã tích lũy " + tongTinChi + " / " + lhp.getSoTinChiToiThieu())
		                    .build());
		            }
		        } else {
		            // ❗ Nếu không có điều kiện tín chỉ tối thiểu thì xử lý từng học phần như cũ
		            List<ChuongTrinhHoc_HocPhan> hocPhanNgoaiToHop = chuongTrinhHocHocPhanRepository
		                .findByChuongTrinhHocIdAndLoaiHocPhanAndToHopIsNull(chuongTrinh.getId(), lhp.getLoaiHocPhan());

		            for (ChuongTrinhHoc_HocPhan wrapper : hocPhanNgoaiToHop) {
		                HocPhan hp = wrapper.getHocPhan();
		                if (!hocPhanDaHocOrDangHoc.contains(hp.getMaHocPhan())) {
		                    listLoai.add(HocPhanConThieuDTO.builder()
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
		                        .monThayThe(hp.getMonThayThe())
		                        .maMonThayThe(hp.getMaMonThayThe())
		                        .soCa(hp.getSoCa())
		                        .build());
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
	 
	 @Override
	 public List<HocPhanConThieuDTO> getHocPhanThiLaiHocLai(String maSinhVien) {
	     List<HocPhanConThieuDTO> ketQua = new ArrayList<>();

	     List<BangDiem> diemList = bangDiemRepository.findByMsv(maSinhVien);

	     for (BangDiem diem : diemList) {
	         double diemTK = diem.getDiemTongKet();
	         Integer soLanThi = diem.getSoLanThi();
	         HocPhan hp = diem.getHocPhan();

	         if (hp == null) continue;

	         if (diemTK >= 0 && diemTK < 4) {
	             if (soLanThi != null && soLanThi <= 1) {
	                 // ✅ Thi lại
	                 ketQua.add(HocPhanConThieuDTO.builder()
	                     .loai("Thi lại")
	                     .noiDung(hp.getTenHocPhan())
	                     .ghiChu("Điểm tổng kết: " + diemTK + ", số lần thi: " + soLanThi)
	                     .id(hp.getId())
	                     .maHocPhan(hp.getMaHocPhan())
	                     .tenHocPhan(hp.getTenHocPhan())
	                     .tienQuyet(hp.getTienQuyet())
	                     .nganhPhuTrach(hp.getNganhPhuTrach())
	                     .lienLac(hp.getLienLac())
	                     .maGiangVien(hp.getMaGiangVien())
	                     .moRong(hp.getMoRong())
	                     .soTinChi(hp.getSoTinChi())
	                     .monThayThe(hp.getMonThayThe())
	                     .maMonThayThe(hp.getMaMonThayThe())
	                     .soCa(hp.getSoCa())
	                     .build());
	             } else {
	                 // ✅ Học lại vì quá số lần thi
	                 ketQua.add(buildHocLaiDTO(hp, diemTK, soLanThi));
	             }
	         } else if (diemTK == -1) {
	             // ✅ Học lại do không có điểm
	             ketQua.add(buildHocLaiDTO(hp, diemTK, soLanThi));
	         }
	     }

	     return ketQua;
	 }

	 private HocPhanConThieuDTO buildHocLaiDTO(HocPhan hp, double diemTK, Integer soLanThi) {
	     return HocPhanConThieuDTO.builder()
	         .loai("Học lại")
	         .noiDung(hp.getTenHocPhan())
	         .ghiChu("Điểm tổng kết: " + diemTK + (soLanThi != null ? ", số lần thi: " + soLanThi : ""))
	         .id(hp.getId())
	         .maHocPhan(hp.getMaHocPhan())
	         .tenHocPhan(hp.getTenHocPhan())
	         .tienQuyet(hp.getTienQuyet())
	         .nganhPhuTrach(hp.getNganhPhuTrach())
	         .lienLac(hp.getLienLac())
	         .maGiangVien(hp.getMaGiangVien())
	         .moRong(hp.getMoRong())
	         .soTinChi(hp.getSoTinChi())
	         .monThayThe(hp.getMonThayThe())
	         .maMonThayThe(hp.getMaMonThayThe())
	         .soCa(hp.getSoCa())
	         .build();
	 }

}

