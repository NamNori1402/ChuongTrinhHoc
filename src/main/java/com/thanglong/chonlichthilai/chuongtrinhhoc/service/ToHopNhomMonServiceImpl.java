package com.thanglong.chonlichthilai.chuongtrinhhoc.service;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.HocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopNhomMonDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMon;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMonChiTiet;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ToHopHocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ToHopNhomMonChiTietRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ToHopNhomMonRepository;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.hocphan.HocPhanRepository;
import com.thanglong.chonlichthilai.hocphan.HocPhanWithThayTheDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.mapper.ChuongTrinhHocMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToHopNhomMonServiceImpl implements ToHopNhomMonService {

    private final ToHopNhomMonRepository nhomMonRepo;
    private final ToHopNhomMonChiTietRepository chiTietRepo;
    private final HocPhanRepository hocPhanRepository;
    private final ToHopHocPhanRepository toHopHocPhanRepository;
    @Override
    @Transactional
    
    public ToHopNhomMon create(ToHopNhomMon toHopNhomMon) {
        if (toHopNhomMon.getToHopHocPhan() == null || toHopNhomMon.getToHopHocPhan().getId() == null) {
            throw new RuntimeException("Thiếu thông tin tổ hợp học phần cha (toHopHocPhan)");
        }

        Long toHopId = toHopNhomMon.getToHopHocPhan().getId();

        // ✅ Tìm xem đã tồn tại tổ hợp nhóm môn cùng tên trong cùng tổ hợp học phần chưa
        Optional<ToHopNhomMon> existing = nhomMonRepo.findByToHopHocPhanId(toHopId).stream()
            .filter(n -> n.getTenNhom().equalsIgnoreCase(toHopNhomMon.getTenNhom()))
            .findFirst();

        if (existing.isPresent()) {
            // ✅ Đã tồn tại → cập nhật tên nếu cần (hoặc return như cũ)
            ToHopNhomMon nhom = existing.get();
            nhom.setTenNhom(toHopNhomMon.getTenNhom()); // nếu có nhu cầu đổi tên thì giữ dòng này
            return nhomMonRepo.save(nhom); // cập nhật và trả về
        }

        // ✅ Nếu chưa tồn tại → tạo mới
        return nhomMonRepo.save(toHopNhomMon);
    }

 // Trong ToHopNhomMonServiceImpl
    @Override
    public List<ToHopNhomMonDTO> getByToHopId(Long toHopId) {
        List<ToHopNhomMon> nhoms = nhomMonRepo.findByToHopHocPhanId(toHopId);
        return nhoms.stream().map(n -> {
            List<HocPhanWithThayTheDTO> hocPhans = Optional.ofNullable(n.getChiTietMonHoc())
                .orElse(Collections.emptyList())
                .stream()
                .map(ct -> ChuongTrinhHocMapper.convertToHocPhanWithThayTheDTO(ct.getHocPhan()))
                .collect(Collectors.toList());

            return ToHopNhomMonDTO.builder()
                .id(n.getId())
                .tenNhom(n.getTenNhom())
                .toHopHocPhanId(n.getToHopHocPhan().getId())
                .tenToHop(n.getToHopHocPhan().getTenToHop())
                .hocPhans(hocPhans)
                .build();
        }).collect(Collectors.toList());
    }
  
    @Override
    @Transactional
    public void deleteToHopNhomMon(Long toHopNhomMonId) {
        // Kiểm tra tồn tại
        ToHopNhomMon nhom = nhomMonRepo.findById(toHopNhomMonId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm môn với ID: " + toHopNhomMonId));

        // Xóa chi tiết học phần trong nhóm trước
        chiTietRepo.deleteByToHopNhomMon_Id(toHopNhomMonId);

        // Sau đó xóa nhóm môn
        nhomMonRepo.delete(nhom);
    }

    @Override
    @Transactional
    public List<String> addHocPhanToNhomMon(Long nhomMonId, List<Long> hocPhanIds) {
        ToHopNhomMon nhom = nhomMonRepo.findById(nhomMonId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm môn"));

        List<String> logs = new ArrayList<>();

        for (Long hocPhanId : hocPhanIds) {
            HocPhan hocPhan = hocPhanRepository.findById(hocPhanId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần ID: " + hocPhanId));

            boolean exists = chiTietRepo.findByToHopNhomMon_Id(nhomMonId).stream()
                .anyMatch(ct -> ct.getHocPhan().getId().equals(hocPhanId));

            if (exists) {
                logs.add("⏭ Học phần `" + hocPhan.getMaHocPhan() + "` đã tồn tại trong nhóm.");
            } else {
                ToHopNhomMonChiTiet chiTiet = ToHopNhomMonChiTiet.builder()
                    .toHopNhomMon(nhom)
                    .hocPhan(hocPhan)
                    .build();
                chiTietRepo.save(chiTiet);
                logs.add("✅ Đã thêm học phần `" + hocPhan.getMaHocPhan() + "` vào nhóm.");
            }
        }

        return logs;
    }

    @Transactional
    public void removeHocPhanFromNhomMon(Long toHopNhomMonId, Long hocPhanId) {
        ToHopNhomMonChiTiet chiTiet = chiTietRepo.findByToHopNhomMon_Id(toHopNhomMonId).stream()
            .filter(ct -> ct.getHocPhan().getId().equals(hocPhanId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần trong nhóm môn"));

        chiTietRepo.delete(chiTiet);
    }


}
