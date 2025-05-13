package com.thanglong.chonlichthilai.chuongtrinhhoc.service;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopNhomMonDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.*;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.HocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.mapper.ChuongTrinhHocMapper;
import com.thanglong.chonlichthilai.hocphan.HocPhanWithThayTheDTO;
import com.thanglong.chonlichthilai.hocphan.HocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

@Service
public class ToHopHocPhanServiceImpl implements ToHopHocPhanService {

    @Autowired
    private ToHopHocPhanRepository toHopHocPhanRepository;

    @Autowired
    private ToHopHocPhanChiTietRepository toHopHocPhanChiTietRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    @Autowired
    private ChuongTrinhHoc_LoaiHocPhanRepository ctlhpRepository;
    @Override
    public List<ToHopHocPhan> findEntitiesByChuongTrinhHocLoaiHocPhanId(Long ctLHPId) {
        return toHopHocPhanRepository.findByChuongTrinhHocLoaiHocPhan_Id(ctLHPId);
    }

    @Override
    public List<ToHopHocPhanDTO> findByChuongTrinhHocLoaiHocPhanId(Long ctLHPId) {
        return toHopHocPhanRepository.findByChuongTrinhHocLoaiHocPhan_Id(ctLHPId)
            .stream()
            .map(toHop -> {
                // Danh sách học phần thuộc tổ hợp
                List<HocPhanWithThayTheDTO> hocPhans = Optional.ofNullable(toHop.getChiTietList())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(ct -> ChuongTrinhHocMapper.convertToHocPhanWithThayTheDTO(ct.getHocPhan()))
                    .collect(Collectors.toList());

                // Các nhóm môn trong tổ hợp học phần
                List<ToHopNhomMonDTO> toHopNhomMonDTOs = Optional.ofNullable(toHop.getToHopNhomMons())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(nhom -> {
                        List<HocPhanWithThayTheDTO> hocPhanTrongNhom = Optional.ofNullable(nhom.getChiTietMonHoc())
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(ct -> ChuongTrinhHocMapper.convertToHocPhanWithThayTheDTO(ct.getHocPhan()))
                            .collect(Collectors.toList());

                        return ToHopNhomMonDTO.builder()
                            .id(nhom.getId())
                            .tenNhom(nhom.getTenNhom())
                            .toHopHocPhanId(toHop.getId())
                            .tenToHop(toHop.getTenToHop())
                            .hocPhans(hocPhanTrongNhom)
                            .build();
                    })
                    .collect(Collectors.toList());

                return ToHopHocPhanDTO.builder()
                    .id(toHop.getId())
                    .tenToHop(toHop.getTenToHop())
                    .soTinChiToiThieu(toHop.getSoTinChiToiThieu())
                    .soLuongMonToiThieu(toHop.getSoLuongMonToiThieu())
                    .batBuocHocTatCa(toHop.getBatBuocHocTatCa())
                    .hocPhans(hocPhans)
                    .toHopNhomMons(toHopNhomMonDTOs)
                    .build();
            })
            .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public ToHopHocPhan createToHopHocPhan(ToHopHocPhanRequest request) {
        ChuongTrinhHoc_LoaiHocPhan ctlhp = ctlhpRepository.findById(request.getChuongTrinhHocLoaiHocPhanId())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy CTLHPhan"));

        // ✅ Kiểm tra tổ hợp đã tồn tại chưa (trùng tên trong cùng CTLHPhan)
        Optional<ToHopHocPhan> optional = toHopHocPhanRepository
            .findByChuongTrinhHocLoaiHocPhan_IdAndTenToHopIgnoreCase(
                request.getChuongTrinhHocLoaiHocPhanId(),
                request.getTenToHop().trim()
            );

        ToHopHocPhan toHop;

        if (optional.isPresent()) {
            // 🔁 Cập nhật tổ hợp nếu đã tồn tại
            toHop = optional.get();
            toHop.setSoTinChiToiThieu(request.getSoTinChiToiThieu());
            toHop.setSoLuongMonToiThieu(request.getSoLuongMonToiThieu());
            toHop.setBatBuocHocTatCa(request.getBatBuocHocTatCa());
            toHop = toHopHocPhanRepository.save(toHop);

            // Xóa chi tiết cũ để ghi đè
            toHopHocPhanChiTietRepository.deleteByToHopHocPhan_Id(toHop.getId());
        } else {
            // 🆕 Tạo mới nếu chưa có
            toHop = ToHopHocPhan.builder()
                .tenToHop(request.getTenToHop().trim())
                .soTinChiToiThieu(request.getSoTinChiToiThieu())
                .soLuongMonToiThieu(request.getSoLuongMonToiThieu())
                .batBuocHocTatCa(request.getBatBuocHocTatCa())
                .chuongTrinhHocLoaiHocPhan(ctlhp)
                .build();
            toHop = toHopHocPhanRepository.save(toHop);
        }

        // ✅ Gán lại học phần nếu có danh sách học phần truyền vào
        if (request.getHocPhanIds() != null && !request.getHocPhanIds().isEmpty()) {
            for (Long hocPhanId : request.getHocPhanIds()) {
                HocPhan hocPhan = hocPhanRepository.findById(hocPhanId)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần ID: " + hocPhanId));
                ToHopHocPhanChiTiet chiTiet = ToHopHocPhanChiTiet.builder()
                        .toHopHocPhan(toHop)
                        .hocPhan(hocPhan)
                        .build();
                toHopHocPhanChiTietRepository.save(chiTiet);
            }
        }

        return toHop;
    }

    @Override
    @Transactional
    public ToHopHocPhan updateToHopHocPhan(Long id, ToHopHocPhanRequest request) {
        ToHopHocPhan toHop = toHopHocPhanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tổ hợp"));

        toHop.setTenToHop(request.getTenToHop());
        toHop.setSoTinChiToiThieu(request.getSoTinChiToiThieu());
        toHop.setSoLuongMonToiThieu(request.getSoLuongMonToiThieu());
        toHop.setBatBuocHocTatCa(request.getBatBuocHocTatCa());
        toHopHocPhanRepository.save(toHop);

        // Xóa chi tiết cũ
        toHopHocPhanChiTietRepository.deleteByToHopHocPhan_Id(id);

        // Lưu chi tiết mới
        if (request.getHocPhanIds() != null) {
            for (Long hocPhanId : request.getHocPhanIds()) {
                HocPhan hocPhan = hocPhanRepository.findById(hocPhanId)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần"));
                ToHopHocPhanChiTiet chiTiet = ToHopHocPhanChiTiet.builder()
                        .toHopHocPhan(toHop)
                        .hocPhan(hocPhan)
                        .build();
                toHopHocPhanChiTietRepository.save(chiTiet);
            }
        }

        return toHop;
    }

    @Override
    @Transactional
    public void deleteToHopHocPhan(Long id) {
        toHopHocPhanChiTietRepository.deleteByToHopHocPhan_Id(id);
        toHopHocPhanRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public List<String> addHocPhansToToHop(Long toHopId, List<Long> hocPhanIds) {
        ToHopHocPhan toHop = toHopHocPhanRepository.findById(toHopId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy tổ hợp"));

        List<String> logs = new ArrayList<>();

        for (Long hocPhanId : hocPhanIds) {
            HocPhan hocPhan = hocPhanRepository.findById(hocPhanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần"));

            boolean exists = toHopHocPhanChiTietRepository
                .findByToHopHocPhanId(toHopId).stream()
                .anyMatch(ct -> ct.getHocPhan().getId().equals(hocPhanId));

            if (exists) {
                logs.add("⏭ Học phần `" + hocPhan.getMaHocPhan() + "` đã tồn tại trong tổ hợp.");
            } else {
                ToHopHocPhanChiTiet chiTiet = ToHopHocPhanChiTiet.builder()
                    .toHopHocPhan(toHop)
                    .hocPhan(hocPhan)
                    .build();
                toHopHocPhanChiTietRepository.save(chiTiet);
                logs.add("✅ Đã thêm học phần `" + hocPhan.getMaHocPhan() + "` vào tổ hợp.");
            }
        }

        return logs;
    }

    
    @Override
    @Transactional
    public void removeHocPhanFromToHop(Long toHopId, Long hocPhanId) {
    	toHopHocPhanChiTietRepository.deleteByToHopHocPhan_IdAndHocPhan_Id(toHopId, hocPhanId);
    }


}
