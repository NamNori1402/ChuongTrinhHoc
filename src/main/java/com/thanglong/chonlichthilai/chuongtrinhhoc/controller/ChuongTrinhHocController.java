package com.thanglong.chonlichthilai.chuongtrinhhoc.controller;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.*;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.*;
import com.thanglong.chonlichthilai.chuongtrinhhoc.mapper.ChuongTrinhHocMapper;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHocRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHoc_HocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.service.ChuongTrinhHocService;
import com.thanglong.chonlichthilai.chuongtrinhhoc.service.ToHopHocPhanService;
import com.thanglong.chonlichthilai.chuongtrinhhoc.service.ToHopNhomMonService;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.hocphan.HocPhanWithThayTheDTO;
import com.thanglong.chonlichthilai.utils.AuthorizationUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/chuongtrinhhoc")
public class ChuongTrinhHocController {

    @Autowired
    private ChuongTrinhHocService service;
    @Autowired
    private ToHopNhomMonService toHopNhomMonService;
    @Autowired
    private ChuongTrinhHoc_HocPhanRepository chuongTrinhHocHocPhanRepository;
    @Autowired
    private ChuongTrinhHocRepository chuongTrinhHocRepository;
    @Autowired
    private ToHopHocPhanService toHopHocPhanService;

    // ==== CHƯƠNG TRÌNH HỌC ====

    @PostMapping("/full")
    public ResponseEntity<ChuongTrinhHocResponse> saveFull(@RequestBody ChuongTrinhHocRequest request, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ChuongTrinhHocResponse("❌ Bạn không có quyền thêm/sửa chương trình học", null));
        }
        return ResponseEntity.ok(service.saveFullChuongTrinhHoc(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền xóa chương trình học.");
        }
        service.deleteById(id);
        return ResponseEntity.ok("✅ Đã xóa chương trình học ID: " + id);
    }

    @GetMapping
    public List<ChuongTrinhHocSummaryDTO> getAllChuongTrinhHoc() {
        List<ChuongTrinhHoc> entities = chuongTrinhHocRepository.findAll();
        return entities.stream()
                       .map(ChuongTrinhHocMapper::toSummaryDTO)
                       .collect(Collectors.toList());
    }


    @GetMapping("/{id}/full-dto")
    public ResponseEntity<?> getFullChuongTrinhHoc(@PathVariable Long id) {
        ChuongTrinhHoc ct = service.findById(id);
        if (ct == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chương trình học");
        return ResponseEntity.ok(ChuongTrinhHocMapper.toDTO(ct));
    }

    // ==== HỌC PHẦN ====

  //thêm nhiều học phần vào chương trình học cùng lúc
    @PostMapping("/{id}/hocphans")
    public ResponseEntity<?> addHocPhanList(
        @PathVariable Long id,
        @RequestBody List<AddHocPhanRequest> requests,
        HttpServletRequest request) {

        if (!AuthorizationUtils.isAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền thêm học phần.");
        }

        List<String> logs = new ArrayList<>();

        for (AddHocPhanRequest req : requests) {
            try {
                boolean exists = chuongTrinhHocHocPhanRepository
                    .existsByChuongTrinhHocIdAndHocPhanId(id, req.getHocPhanId());

                if (!exists) {
                    service.addHocPhanToChuongTrinh(id, req.getHocPhanId(), req.getLoaiHocPhan());
                    logs.add("✅ Đã thêm học phần ID " + req.getHocPhanId());
                } else {
                    logs.add("⏭ Học phần ID " + req.getHocPhanId() + " đã tồn tại trong chương trình, bỏ qua.");
                }
            } catch (Exception e) {
                logs.add("❌ Lỗi khi xử lý học phần ID " + req.getHocPhanId() + ": " + e.getMessage());
            }
        }

        return ResponseEntity.ok(logs);
    }

  //thêm nhiều học phần vào 1 loại học phần
    @PostMapping("/{id}/hocphans/them-nhieu")
    public ResponseEntity<?> addNhieuHocPhan(
        @PathVariable Long id,
        @RequestBody ThemNhieuHocPhanRequest request,
        HttpServletRequest httpRequest) {
    	if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm học phần.");
        }

        List<String> logs = new ArrayList<>();
        LoaiHocPhan loai = request.getLoaiHocPhan();

        for (Long hocPhanId : request.getHocPhanIds()) {
            try {
                boolean exists = chuongTrinhHocHocPhanRepository
                    .existsByChuongTrinhHocIdAndHocPhanId(id, hocPhanId);

                if (!exists) {
                    service.addHocPhanToChuongTrinh(id, hocPhanId, loai);
                    logs.add("✅ Đã thêm học phần ID " + hocPhanId);
                } else {
                    logs.add("⏭ Học phần ID " + hocPhanId + " đã tồn tại trong chương trình.");
                }
            } catch (Exception e) {
                logs.add("❌ Lỗi khi xử lý học phần ID " + hocPhanId + ": " + e.getMessage());
            }
        }

        return ResponseEntity.ok(logs);
    }

        @DeleteMapping("/{chuongTrinhId}/hocphan/{hocPhanId}")
        public ResponseEntity<?> removeHocPhan(
            @PathVariable Long chuongTrinhId,
            @PathVariable Long hocPhanId,
            HttpServletRequest httpRequest) {
        	if (!AuthorizationUtils.isAdmin(httpRequest)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền xóa học phần.");
            }

            try {
                service.removeHocPhanFromChuongTrinh(chuongTrinhId, hocPhanId);
                return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id}/hocphan")
        public ResponseEntity<List<HocPhanWithThayTheDTO>> getHocPhanByChuongTrinh(@PathVariable Long id) {
            List<HocPhan> hocPhans = service.getHocPhanByChuongTrinh(id);
            List<HocPhanWithThayTheDTO> dtos = hocPhans.stream()
                .map(hp -> ChuongTrinhHocMapper.convertToHocPhanWithThayTheDTO(hp))
                .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }


    // ==== LOẠI HỌC PHẦN ====
    
    @PostMapping("/{id}/loaihocphan")
    public ResponseEntity<?> themLoaiHocPhan(@PathVariable Long id, @RequestBody ThemLoaiHocPhanRequest request, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm loại học phần.");
        }
        try {
            service.themLoaiHocPhanChoChuongTrinh(
                id,
                request.getLoaiHocPhan(),
                request.getSoTinChiToiThieu(),
                request.getMaBatDauDuocTinh()
            );
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/ctlhp/{id}/hocphan")
    public ResponseEntity<?> addHocPhanToLoaiHocPhan(@PathVariable Long id, @RequestBody List<Long> hocPhanIds, HttpServletRequest httpRequest) {	
    	if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm học phần.");
        }
        try {
            List<String> logs = service.addHocPhanToCTLHP(id, hocPhanIds);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
        }
    }
    @PutMapping("/{id}/loaihocphan/{loaiHocPhanId}")
    public ResponseEntity<?> capNhatLoaiHocPhan(@PathVariable Long id, @PathVariable Long loaiHocPhanId, @RequestBody CapNhatLoaiHocPhanRequest request, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền cập nhật loại học phần.");
        }
        service.capNhatLoaiHocPhan(id, loaiHocPhanId, request);
        return ResponseEntity.ok("✅ Đã cập nhật loại học phần thành công");
    }

    @GetMapping("/{id}/loaihocphan")
    public ResponseEntity<List<LoaiHocPhanDTO>> getLoaiHocPhanByChuongTrinh(@PathVariable Long id) {
        List<ChuongTrinhHoc_LoaiHocPhan> list = service.getLoaiHocPhanByChuongTrinh(id);
        List<LoaiHocPhanDTO> dtoList = list.stream().map(ChuongTrinhHocMapper::toLoaiHocPhanDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // ==== TỔ HỢP / NHÓM MÔN ====

    @PostMapping("/tohop")
	public ResponseEntity<?> createOrUpdateToHopHocPhan(@RequestBody ToHopHocPhanRequest request, HttpServletRequest httpRequest) {
    	if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm tổ hợp.");
        }
	    try {
	        ToHopHocPhan toHop = toHopHocPhanService.createToHopHocPhan(request);
	
	        // Kiểm tra xem đây là cập nhật hay tạo mới
	        Optional<ToHopHocPhan> existing = toHopHocPhanService
	            .findEntitiesByChuongTrinhHocLoaiHocPhanId(request.getChuongTrinhHocLoaiHocPhanId()) // gọi hàm trả về Entity
	            .stream()
	            .filter(t -> t.getTenToHop().equalsIgnoreCase(request.getTenToHop().trim()))
	            .findFirst();
	
	        String message = existing.isPresent() ?
	            "✅ Đã cập nhật tổ hợp: " + toHop.getTenToHop() :
	            "✅ Đã tạo tổ hợp mới: " + toHop.getTenToHop();
	
	        return ResponseEntity.ok(message);
	
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
	    }
	}

    @DeleteMapping("/tohop/{toHopId}")
    public ResponseEntity<?> deleteToHopHocPhan(@PathVariable Long toHopId, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền xóa tổ hợp.");
        }
        toHopHocPhanService.deleteToHopHocPhan(toHopId);
        return ResponseEntity.ok("✅ Đã xóa tổ hợp");
    }

    @PostMapping("/tohop/{toHopId}/hocphan")
    public ResponseEntity<?> addHocPhanToToHop(@PathVariable Long toHopId, @RequestBody List<Long> hocPhanIds, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm học phần vào tổ hợp.");
        }
        List<String> result = toHopHocPhanService.addHocPhansToToHop(toHopId, hocPhanIds);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/tohop/{toHopId}/hocphan/{hocPhanId}")
    public ResponseEntity<?> removeHocPhanFromToHop(@PathVariable Long toHopId, @PathVariable Long hocPhanId, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền xóa học phần khỏi tổ hợp.");
        }
        toHopHocPhanService.removeHocPhanFromToHop(toHopId, hocPhanId);
        return ResponseEntity.ok("✅ Đã xóa học phần khỏi tổ hợp");
    }

  //tạo mới tổ hợp nhóm môn
  	@PostMapping("/tohopnhommon")
  	public ResponseEntity<?> createOrUpdateToHopNhomMon(@RequestBody ToHopNhomMon toHopNhomMon, HttpServletRequest httpRequest) {
  		if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm tổ hợp nhóm môn.");
        }
  	    try {
  	        ToHopNhomMon saved = toHopNhomMonService.create(toHopNhomMon);
  	
  	        // Kiểm tra xem đây là cập nhật hay tạo mới
  	        Long toHopId = toHopNhomMon.getToHopHocPhan() != null ? toHopNhomMon.getToHopHocPhan().getId() : null;
  	
  	        if (toHopId == null) {
  	            return ResponseEntity.badRequest().body("❌ Thiếu thông tin tổ hợp học phần (toHopHocPhan.id)");
  	        }
  	
  	        boolean isUpdate = toHopNhomMonService.getByToHopId(toHopId).stream()
  	            .anyMatch(n -> n.getId().equals(saved.getId())); // nếu đã tồn tại ID thì là cập nhật
  	
  	        String message = isUpdate ?
  	            "✅ Đã cập nhật tổ hợp nhóm môn: " + saved.getTenNhom() :
  	            "✅ Đã tạo mới tổ hợp nhóm môn: " + saved.getTenNhom();
  	        
  	        return ResponseEntity.ok(message);
  	
  	    } catch (Exception e) {
  	        return ResponseEntity.badRequest().body("❌ Lỗi khi tạo tổ hợp nhóm môn: " + e.getMessage());
  	    }
  	}
 // Lấy danh sách tổ hợp trong 1 loại học phần
 	@GetMapping("/ctlhp/{ctlhpId}/tohop")
 	public ResponseEntity<List<ToHopHocPhanDTO>> getToHopByCTLHP(@PathVariable Long ctlhpId) {
 	    return ResponseEntity.ok(toHopHocPhanService.findByChuongTrinhHocLoaiHocPhanId(ctlhpId));
 	}
    @DeleteMapping("/tohopnhommon/{id}")
    public ResponseEntity<?> deleteNhomMon(@PathVariable Long id, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền xóa tổ hợp nhóm môn.");
        }
        toHopNhomMonService.deleteToHopNhomMon(id);
        return ResponseEntity.ok("✅ Đã xóa tổ hợp nhóm môn");
    }

    @PostMapping("/tohopnhommon/{id}/hocphan")
    public ResponseEntity<?> addHocPhanToNhomMon(@PathVariable Long id, @RequestBody List<Long> hocPhanIds, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền thêm học phần vào nhóm môn.");
        }
        List<String> resultLogs = toHopNhomMonService.addHocPhanToNhomMon(id, hocPhanIds);
        return ResponseEntity.ok(resultLogs);
    }
  //cập nhật tổ hợp học phần
  	@PutMapping("/tohop/{toHopId}")
  	public ResponseEntity<?> updateToHopHocPhan(@PathVariable Long toHopId, @RequestBody ToHopHocPhanRequest request, HttpServletRequest httpRequest) {
  		 if (!AuthorizationUtils.isAdmin(httpRequest)) {
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền cập nhật tổ hợp học phần.");
         }
  		try {
  	        toHopHocPhanService.updateToHopHocPhan(toHopId, request);
  	        return ResponseEntity.ok("✅ Cập nhật tổ hợp thành công");
  	    } catch (Exception e) {
  	        return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
  	    }
  	}
  //lấy tất cả tổ hợp nhóm môn theo toHopHocPhanId
  	@GetMapping("/tohop/{toHopId}/tohopnhommon")
  	public ResponseEntity<List<ToHopNhomMonDTO>> getNhomMonsByToHop(@PathVariable Long toHopId) {
  	  List<ToHopNhomMonDTO> nhoms = toHopNhomMonService.getByToHopId(toHopId);
  	  return ResponseEntity.ok(nhoms);
  	}
    @DeleteMapping("/tohopnhommon/{nhomMonId}/hocphan/{hocPhanId}")
    public ResponseEntity<?> removeHocPhanFromNhomMon(@PathVariable Long nhomMonId, @PathVariable Long hocPhanId, HttpServletRequest httpRequest) {
        if (!AuthorizationUtils.isAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Bạn không có quyền xóa học phần khỏi nhóm môn.");
        }
        toHopNhomMonService.removeHocPhanFromNhomMon(nhomMonId, hocPhanId);
        return ResponseEntity.ok("✅ Đã xóa học phần khỏi nhóm môn");
    }

    // ==== TÌM KIẾM ====

  //search chương trình học theo học phần
    @GetMapping("/search")
    public ResponseEntity<?> searchMonHoc(
        @RequestParam(required = false) String maHocPhan,
        @RequestParam(required = false) String tenHocPhan,
        @RequestParam(required = false) String keyword) {
        
        try {
            List<ChuongTrinhHoc> results;
            
            if (keyword != null && !keyword.isEmpty()) {
                results = service.searchByKeyword(keyword);
            } else if (maHocPhan != null && !maHocPhan.isEmpty()) {
                results = service.findByMaHocPhan(maHocPhan);
            } else if (tenHocPhan != null && !tenHocPhan.isEmpty()) {
                results = service.findByTenHocPhan(tenHocPhan);
            } else {
                return ResponseEntity.badRequest().body("Vui lòng nhập ít nhất một tiêu chí tìm kiếm");
            }

            List<ChuongTrinhHocDTO> dtoResults = results.stream()
                .map(ChuongTrinhHocMapper::toDTO)
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(dtoResults != null ? dtoResults : Collections.emptyList());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }

}
