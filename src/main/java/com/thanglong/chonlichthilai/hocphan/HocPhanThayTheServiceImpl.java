package com.thanglong.chonlichthilai.hocphan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.*;
@Service
@RequiredArgsConstructor
public class HocPhanThayTheServiceImpl implements HocPhanThayTheService {
    private final HocPhanThayTheRepository hocPhanThayTheRepository;
    private final HocPhanRepository hocPhanRepository;

    @Override
    public List<HocPhanThayTheDTO> getAllHocPhanThayThe() {
        return hocPhanThayTheRepository.findAll().stream()
            .map(entity -> {
                HocPhanThayTheDTO dto = new HocPhanThayTheDTO();
                // Vẫn trả về mã học phần cho frontend (nhưng lưu trữ bằng id)
                dto.setMaHocPhanGoc(entity.getHocPhanGoc().getMaHocPhan());
                dto.setMaHocPhanThayThe(entity.getHocPhanThayThe().getMaHocPhan());
                dto.setTenHocPhanThayThe(entity.getHocPhanThayThe().getTenHocPhan());
                dto.setSoTinChi(entity.getHocPhanThayThe().getSoTinChi()); 
                dto.setKhoaApDungTu(entity.getKhoaApDungTu());
                dto.setKhoaApDungDen(entity.getKhoaApDungDen());
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public void addHocPhanThayThe(HocPhanThayTheDTO dto) {
        // Tìm HocPhan bằng mã học phần (để lấy id)
        HocPhan hocPhanGoc = hocPhanRepository.findByMaHocPhan(dto.getMaHocPhanGoc())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần gốc: " + dto.getMaHocPhanGoc()));
        
        HocPhan hocPhanThayThe = hocPhanRepository.findByMaHocPhan(dto.getMaHocPhanThayThe())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần thay thế: " + dto.getMaHocPhanThayThe()));
        
        // ✅ Kiểm tra trùng
        boolean daTonTai = hocPhanThayTheRepository
            .findByHocPhanGoc(hocPhanGoc)
            .stream()
            .anyMatch(e -> e.getHocPhanThayThe().getId().equals(hocPhanThayThe.getId()));
        
        if (daTonTai) {
            throw new RuntimeException("Học phần thay thế đã tồn tại cho cặp này");
        }
        // Tạo entity với tham chiếu id
        HocPhanThayThe entity = HocPhanThayThe.builder()
            .hocPhanGoc(hocPhanGoc)
            .hocPhanThayThe(hocPhanThayThe)
            .khoaApDungTu(dto.getKhoaApDungTu())
            .khoaApDungDen(dto.getKhoaApDungDen())
            .build();

        hocPhanThayTheRepository.save(entity);
    }
    @Override
	 public List<HocPhanThayThe> addMultipleHocPhanThayThe(List<HocPhanThayTheDTO> dtos) {
	     List<HocPhanThayThe> entities = new ArrayList<>();
	     
	     for (HocPhanThayTheDTO dto : dtos) {
	         // Tìm học phần gốc
	         HocPhan hocPhanGoc = hocPhanRepository.findByMaHocPhan(dto.getMaHocPhanGoc())
	             .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần gốc: " + dto.getMaHocPhanGoc()));
	         
	         // Tìm học phần thay thế
	         HocPhan hocPhanThayThe = hocPhanRepository.findByMaHocPhan(dto.getMaHocPhanThayThe())
	             .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần thay thế: " + dto.getMaHocPhanThayThe()));
	         
	         // Tạo entity
	         HocPhanThayThe entity = HocPhanThayThe.builder()
	             .hocPhanGoc(hocPhanGoc)
	             .hocPhanThayThe(hocPhanThayThe)
	             .khoaApDungTu(dto.getKhoaApDungTu())
	             .khoaApDungDen(dto.getKhoaApDungDen())
	             .build();
	             
	         entities.add(entity);
	     }
	     
	     return hocPhanThayTheRepository.saveAll(entities);
	 }
    @Override
    @Transactional
    public void deleteHocPhanThayThe(String maHocPhanGoc, String maHocPhanThayThe) {
        HocPhan hocPhanGoc = hocPhanRepository.findByMaHocPhan(maHocPhanGoc)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần gốc: " + maHocPhanGoc));
        
        HocPhan hocPhanThayThe = hocPhanRepository.findByMaHocPhan(maHocPhanThayThe)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần thay thế: " + maHocPhanThayThe));

        int deletedCount = hocPhanThayTheRepository.deleteByHocPhanGocAndHocPhanThayThe(hocPhanGoc, hocPhanThayThe);
        
        if (deletedCount == 0) {
            throw new RuntimeException("Không tìm thấy quan hệ thay thế giữa " + maHocPhanGoc + " và " + maHocPhanThayThe);
        }
    }
    public void deleteById(Long id) {
        if (!hocPhanThayTheRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy học phần thay thế với ID = " + id);
        }
        hocPhanThayTheRepository.deleteById(id);
    }
}