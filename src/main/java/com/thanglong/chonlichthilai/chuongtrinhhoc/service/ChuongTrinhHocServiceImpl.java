package com.thanglong.chonlichthilai.chuongtrinhhoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.CapNhatLoaiHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocResponse;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.LoaiHocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_HocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.mapper.ChuongTrinhHocMapper;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHocRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHoc_HocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHoc_LoaiHocPhanRepository;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.hocphan.HocPhanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChuongTrinhHocServiceImpl implements ChuongTrinhHocService {
	
    @Autowired
    private ChuongTrinhHocRepository chuongTrinhHocRepository;
    
    @Autowired
    private HocPhanRepository hocPhanRepository;
    
    @Autowired
    private ChuongTrinhHoc_HocPhanRepository chuongTrinhHocHocPhanRepository;

    @Autowired
    private ChuongTrinhHoc_LoaiHocPhanRepository ctlhpRepository;
    @Autowired
    private ChuongTrinhHoc_LoaiHocPhanRepository loaiHocPhanRepo;
    
    @Override
    public List<ChuongTrinhHoc> findAll() {
        return chuongTrinhHocRepository.findAll();
    }

    @Override
    public ChuongTrinhHoc findById(Long id) {
        return chuongTrinhHocRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChuongTrinhHoc> findByKhoa(String khoaHoc) {
        // TODO: Triển khai sau nếu cần
        return null;
    }

    @Override
    public List<ChuongTrinhHoc> searchByKeyword(String keyword) {
        return chuongTrinhHocRepository.searchByKeyword(keyword);
    }

    @Override
    public List<ChuongTrinhHoc> findByMaHocPhan(String maHocPhan) {
        return chuongTrinhHocRepository.findByMaHocPhan(maHocPhan);
    }

    @Override
    public List<ChuongTrinhHoc> findByTenHocPhan(String tenHocPhan) {
        return chuongTrinhHocRepository.findByTenHocPhan(tenHocPhan);
    }

    @Override
    public void deleteById(Long id) {
        chuongTrinhHocRepository.deleteById(id);
    }
    
//    @Override
//    public ChuongTrinhHoc save(ChuongTrinhHoc e) {
//        // Tìm chương trình học đã tồn tại
//        ChuongTrinhHoc existing = chuongTrinhHocRepository.findByChuyenNganhAndKhoa(e.getChuyenNganh(), e.getKhoa());
//
//        if (existing != null) {
//            // Nếu đã tồn tại → cập nhật thông tin (nếu có gì khác)
//            existing.setChuyenNganh(e.getChuyenNganh()); // redundant, nhưng an toàn
//            existing.setKhoa(e.getKhoa());
//            // Nếu có thêm trường nào mới sau này → cập nhật ở đây
//            return chuongTrinhHocRepository.save(existing);
//        } else {
//            // Nếu chưa có → tạo mới
//            return chuongTrinhHocRepository.save(e);
//        }
//    }
    @Override
    public List<String> addHocPhanToCTLHP(Long ctlhpId, List<Long> hocPhanIds) {
        List<String> logs = new ArrayList<>();

        ChuongTrinhHoc_LoaiHocPhan ctlhp = ctlhpRepository.findById(ctlhpId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại học phần với ID: " + ctlhpId));

        for (Long hocPhanId : hocPhanIds) {
            boolean exists = chuongTrinhHocHocPhanRepository
                .existsByChuongTrinhHocLoaiHocPhanIdAndHocPhanId(ctlhpId, hocPhanId);

            if (!exists) {
                HocPhan hocPhan = hocPhanRepository.findById(hocPhanId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần ID: " + hocPhanId));

                ChuongTrinhHoc_HocPhan entity = new ChuongTrinhHoc_HocPhan();
                entity.setChuongTrinhHocLoaiHocPhan(ctlhp);                 // ✅ đúng với @ManyToOne bạn đang dùng
                entity.setHocPhan(hocPhan);
                entity.setChuongTrinhHoc(ctlhp.getChuongTrinhHoc());       // gán luôn cho đầy đủ quan hệ
                entity.setLoaiHocPhan(ctlhp.getLoaiHocPhan());             // nếu muốn lưu song song

                chuongTrinhHocHocPhanRepository.save(entity);
                logs.add("✅ Đã thêm học phần ID " + hocPhanId);
            } else {
                logs.add("⏭ Học phần ID " + hocPhanId + " đã tồn tại.");
            }
        }

        return logs;
    }

	@Override
	public ChuongTrinhHocResponse saveFullChuongTrinhHoc(ChuongTrinhHocRequest request) {
	    Optional<ChuongTrinhHoc> optional = chuongTrinhHocRepository.findByChuyenNganhAndKhoa(
	            request.getChuyenNganh(), request.getKhoa());
	    ChuongTrinhHoc ct;
	
	    if (optional.isPresent()) {
	        ct = optional.get();
	        System.out.println("🔁 Đã tìm thấy chương trình học: " + ct.getChuyenNganh() + " - " + ct.getKhoa());
	    } else {
	        ct = ChuongTrinhHoc.builder()
	                .chuyenNganh(request.getChuyenNganh())
	                .khoa(request.getKhoa())
	                .build();
	        chuongTrinhHocRepository.save(ct);
	        System.out.println("🆕 Tạo mới chương trình học: " + ct.getChuyenNganh() + " - " + ct.getKhoa());
	    }
	
	    // ✅ Xử lý loại học phần
	    if (request.getLoaiHocPhans() != null && !request.getLoaiHocPhans().isEmpty()) {
	        for (LoaiHocPhanDTO dto : request.getLoaiHocPhans()) {
	            LoaiHocPhan enumValue = LoaiHocPhan.valueOf(dto.getLoaiHocPhan());
	            Optional<ChuongTrinhHoc_LoaiHocPhan> existing =
	                    loaiHocPhanRepo.findByChuongTrinhHocIdAndLoaiHocPhan(ct.getId(), enumValue);
	
	            if (existing.isPresent()) {
	                ChuongTrinhHoc_LoaiHocPhan loai = existing.get();
	                loai.setSoTinChiToiThieu(dto.getSoTinChiToiThieu());
	                loai.setChuThich(dto.getChuThich());
	                loaiHocPhanRepo.save(loai);
	                System.out.println("✅ Cập nhật tín chỉ loại học phần [" + enumValue + "]: " + dto.getSoTinChiToiThieu());
	            } else {
	                ChuongTrinhHoc_LoaiHocPhan loai = ChuongTrinhHoc_LoaiHocPhan.builder()
	                        .chuongTrinhHoc(ct)
	                        .loaiHocPhan(enumValue)
	                        .soTinChiToiThieu(dto.getSoTinChiToiThieu())
	                        .chuThich(dto.getChuThich())
	                        .build();
	                loaiHocPhanRepo.save(loai);
	                System.out.println("➕ Thêm mới loại học phần [" + enumValue + "]: " + dto.getSoTinChiToiThieu());
	            }
	        }
	    } else {
	        // ✅ Không truyền loại học phần nào → tạo mặc định với null tín chỉ
	        for (LoaiHocPhan loai : LoaiHocPhan.values()) {
	            if (!loaiHocPhanRepo.existsByChuongTrinhHocIdAndLoaiHocPhan(ct.getId(), loai)) {
	                ChuongTrinhHoc_LoaiHocPhan entity = ChuongTrinhHoc_LoaiHocPhan.builder()
	                        .chuongTrinhHoc(ct)
	                        .loaiHocPhan(loai)
	                        .soTinChiToiThieu(null)
	                        .build();
	                loaiHocPhanRepo.save(entity);
	                System.out.println("➕ Thêm mặc định loại học phần [" + loai + "] với số tín chỉ null");
	            }
	        }
	    }
	
	    // ✅ Xử lý học phần
	    if (request.getHocPhans() != null) {
	        for (ChuongTrinhHocRequest.HocPhanGhepDTO dto : request.getHocPhans()) {
	            HocPhan hp = hocPhanRepository.findById(dto.getHocPhanId())
	                    .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần ID: " + dto.getHocPhanId()));
	
	            boolean exists = chuongTrinhHocHocPhanRepository
	                    .existsByChuongTrinhHocIdAndHocPhanId(ct.getId(), dto.getHocPhanId());
	
	            if (!exists) {
	                ChuongTrinhHoc_HocPhan relation = ChuongTrinhHoc_HocPhan.builder()
	                        .chuongTrinhHoc(ct)
	                        .hocPhan(hp)
	                        .loaiHocPhan(dto.getLoaiHocPhan())
	                        .build();
	                chuongTrinhHocHocPhanRepository.save(relation);
	                System.out.println("➕ Gắn học phần [" + hp.getMaHocPhan() + "] vào chương trình học.");
	            } else {
	                System.out.println("⏭ Bỏ qua học phần đã tồn tại [" + hp.getMaHocPhan() + "]");
	            }
	        }
	    }
	    ChuongTrinhHocDTO dto = ChuongTrinhHocMapper.toDTO(ct);
	    return new ChuongTrinhHocResponse("Đã lưu chương trình học đầy đủ", dto);

	}


//    @Override
//    public void updateSoTinChiToiThieu(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan, int soTinChi) {
//        ChuongTrinhHoc_LoaiHocPhan loai = loaiHocPhanRepo
//            .findByChuongTrinhHocIdAndLoaiHocPhan(chuongTrinhHocId, loaiHocPhan)
//            .orElseThrow(() -> new RuntimeException("Loại học phần không tồn tại"));
//        
//        loai.setSoTinChiToiThieu(soTinChi);
//        loaiHocPhanRepo.save(loai);
//    }
//    @Override
//    public void capNhatLoaiHocPhan(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan, CapNhatLoaiHocPhanRequest request) {
//        ChuongTrinhHoc_LoaiHocPhan loai = loaiHocPhanRepo
//            .findByChuongTrinhHocIdAndLoaiHocPhan(chuongTrinhHocId, loaiHocPhan)
//            .orElseThrow(() -> new RuntimeException("Loại học phần không tồn tại trong chương trình học."));
//
//        loai.setSoTinChiToiThieu(request.getSoTinChiToiThieu());
//        loai.setChuThich(request.getChuThich()); // 🆕 cập nhật luôn cả chú thích
//        loai.setMaBatDauDuocTinh(request.getMaBatDauDuocTinh());
//        loaiHocPhanRepo.save(loai);
//    }
    @Override
    public void capNhatLoaiHocPhan(Long chuongTrinhHocId, Long loaiHocPhanId, CapNhatLoaiHocPhanRequest request) {
        ChuongTrinhHoc_LoaiHocPhan loai = loaiHocPhanRepo
            .findByIdAndChuongTrinhHocId(loaiHocPhanId, chuongTrinhHocId)
            .orElseThrow(() -> new RuntimeException("Loại học phần không tồn tại trong chương trình học."));

        loai.setSoTinChiToiThieu(request.getSoTinChiToiThieu());
        loai.setChuThich(request.getChuThich());
        loai.setMaBatDauDuocTinh(request.getMaBatDauDuocTinh());
        loaiHocPhanRepo.save(loai);
    }


    // Thêm phương thức để thiết lập điều kiện loại học phần
    @Override
    @Transactional
    public void themLoaiHocPhanChoChuongTrinh(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan, int soTinChiToiThieu, String maBatDauDuocTinh) {
        ChuongTrinhHoc ct = chuongTrinhHocRepository.findById(chuongTrinhHocId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình học"));

        if (loaiHocPhanRepo.existsByChuongTrinhHocIdAndLoaiHocPhan(chuongTrinhHocId, loaiHocPhan)) {
            throw new RuntimeException("Loại học phần đã tồn tại trong chương trình");
        }

        ChuongTrinhHoc_LoaiHocPhan newLoai = ChuongTrinhHoc_LoaiHocPhan.builder()
            .chuongTrinhHoc(ct)
            .loaiHocPhan(loaiHocPhan)
            .soTinChiToiThieu(soTinChiToiThieu)
            .maBatDauDuocTinh(maBatDauDuocTinh) 
            .build();

        loaiHocPhanRepo.save(newLoai);
    }
    
    @Override  // <-- Thêm annotation @Override
    public List<ChuongTrinhHoc_LoaiHocPhan> getLoaiHocPhanByChuongTrinh(Long chuongTrinhHocId) {
        return loaiHocPhanRepo.findByChuongTrinhHocId(chuongTrinhHocId);
    }
    
    
    @Override
    @Transactional
    public void addHocPhanToChuongTrinh(Long chuongTrinhHocId, Long hocPhanId, LoaiHocPhan loaiHocPhan) {
        ChuongTrinhHoc chuongTrinh = chuongTrinhHocRepository.findById(chuongTrinhHocId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình học"));

        HocPhan hocPhan = hocPhanRepository.findById(hocPhanId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần"));

        // 🔎 Tìm bản ghi loại học phần tương ứng
        ChuongTrinhHoc_LoaiHocPhan ctlhp = loaiHocPhanRepo
            .findByChuongTrinhHocIdAndLoaiHocPhan(chuongTrinhHocId, loaiHocPhan)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại học phần trong chương trình"));

        // Kiểm tra trùng
        if (chuongTrinhHocHocPhanRepository.existsByChuongTrinhHocIdAndHocPhanId(chuongTrinhHocId, hocPhanId)) {
            throw new RuntimeException("Học phần đã tồn tại trong chương trình");
        }

        ChuongTrinhHoc_HocPhan relationship = ChuongTrinhHoc_HocPhan.builder()
            .chuongTrinhHoc(chuongTrinh)
            .hocPhan(hocPhan)
            .loaiHocPhan(loaiHocPhan)
            .chuongTrinhHocLoaiHocPhan(ctlhp) // 🔥 Đây là phần quan trọng
            .build();

        chuongTrinhHocHocPhanRepository.save(relationship);
    }


    @Override
    @Transactional
    public void removeHocPhanFromChuongTrinh(Long chuongTrinhHocId, Long hocPhanId) {
        chuongTrinhHocHocPhanRepository.deleteByChuongTrinhHocIdAndHocPhanId(chuongTrinhHocId, hocPhanId);
    }

    @Override
    public List<HocPhan> getHocPhanByChuongTrinh(Long chuongTrinhHocId) {
        return chuongTrinhHocHocPhanRepository.findByChuongTrinhHocId(chuongTrinhHocId)
            .stream()
            .map(ChuongTrinhHoc_HocPhan::getHocPhan)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<HocPhan> searchHocPhanNotInChuongTrinh(Long chuongTrinhHocId, String keyword) {
        return hocPhanRepository.findByMaHocPhanContainingOrTenHocPhanContaining(keyword, keyword)
            .stream()
            .filter(hocPhan -> !chuongTrinhHocHocPhanRepository.existsByChuongTrinhHocIdAndHocPhanId(chuongTrinhHocId, hocPhan.getId()))
            .collect(Collectors.toList());
    }
}