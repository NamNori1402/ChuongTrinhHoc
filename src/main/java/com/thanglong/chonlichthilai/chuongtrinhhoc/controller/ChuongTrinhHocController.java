package com.thanglong.chonlichthilai.chuongtrinhhoc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.AddHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.CapNhatLoaiHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocResponse;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.LoaiHocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ThemNhieuHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopNhomMonDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMon;
import com.thanglong.chonlichthilai.chuongtrinhhoc.mapper.ChuongTrinhHocMapper;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHocRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHoc_HocPhanRepository;
import com.thanglong.chonlichthilai.chuongtrinhhoc.service.ChuongTrinhHocService;
import com.thanglong.chonlichthilai.chuongtrinhhoc.service.ToHopHocPhanService;
import com.thanglong.chonlichthilai.chuongtrinhhoc.service.ToHopNhomMonService;
import com.thanglong.chonlichthilai.hocphan.HocPhan;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
@RestController
@RequestMapping("/api/v1/chuongtrinhhoc")
public class ChuongTrinhHocController {

    @Autowired
    private ChuongTrinhHocService service;
    @Autowired
    private ToHopNhomMonService toHopNhomMonService;
    @PostMapping("/full")
    public ResponseEntity<ChuongTrinhHocResponse> saveFull(
        @RequestBody ChuongTrinhHocRequest request,
        HttpServletRequest httpRequest) {

//        if (!AuthorizationUtils.isAdmin(httpRequest) && !AuthorizationUtils.isThuKy(httpRequest)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
//                new ChuongTrinhHocResponse("Bạn không có quyền thêm/sửa chương trình học", null));
//        }

        ChuongTrinhHocResponse result = service.saveFullChuongTrinhHoc(request);
        return ResponseEntity.ok(result);
    }

    @Autowired
    private ChuongTrinhHoc_HocPhanRepository chuongTrinhHocHocPhanRepository;
        
    @Autowired
    private ChuongTrinhHocRepository chuongTrinhHocRepository;
    @GetMapping
    public List<ChuongTrinhHocDTO> getAllChuongTrinhHoc() {
        List<ChuongTrinhHoc> entities = chuongTrinhHocRepository.findAll();
        return entities.stream()
                       .map(ChuongTrinhHocMapper::toDTO)
                       .collect(Collectors.toList());
    }
//
//    @GetMapping("/{id}")
//    public ChuongTrinhHoc findById(@PathVariable Long id) {
//        return service.findById(id);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
        @PathVariable Long id,
        HttpServletRequest httpRequest) {

//        if (!AuthorizationUtils.isAdmin(httpRequest) && !AuthorizationUtils.isThuKy(httpRequest)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền xóa chương trình học.");
//        }

        service.deleteById(id);
        return ResponseEntity.ok().body("Đã xóa chương trình học ID: " + id);
    }
    
    @GetMapping("/{id}/full-dto")
    public ResponseEntity<?> getFullChuongTrinhHoc(@PathVariable Long id) {
        ChuongTrinhHoc ct = service.findById(id);
        if (ct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chương trình học");
        }

        ChuongTrinhHocDTO dto = ChuongTrinhHocMapper.toDTO(ct);
        return ResponseEntity.ok(dto);
    }
    //thêm nhiều học phần vào chương trình học cùng lúc
    @PostMapping("/{id}/hocphans")
    public ResponseEntity<?> addHocPhanList(
        @PathVariable Long id,
        @RequestBody List<AddHocPhanRequest> requests,
        HttpServletRequest request) {

//        if (!AuthorizationUtils.isAdmin(request) && !AuthorizationUtils.isThuKy(request)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền thêm học phần.");
//        }

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
    @PostMapping("/ctlhp/{id}/hocphan")
    public ResponseEntity<?> addHocPhanToLoaiHocPhan(
        @PathVariable Long id,
        @RequestBody List<Long> hocPhanIds) {	

        try {
            List<String> logs = service.addHocPhanToCTLHP(id, hocPhanIds);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
        }
    }


 // Thêm class DTO mới
    @Data
    public static class ThemLoaiHocPhanRequest {
        private LoaiHocPhan loaiHocPhan;
        private int soTinChiToiThieu;
        private String maBatDauDuocTinh;

        // getters và setters
        public LoaiHocPhan getLoaiHocPhan() {
            return loaiHocPhan;
        }
        
        public void setLoaiHocPhan(LoaiHocPhan loaiHocPhan) {
            this.loaiHocPhan = loaiHocPhan;
        }
        
        public int getSoTinChiToiThieu() {
            return soTinChiToiThieu;
        }
        
        public void setSoTinChiToiThieu(int soTinChiToiThieu) {
            this.soTinChiToiThieu = soTinChiToiThieu;
        }
    }
 // API thêm loại học phần vào chương trình
    @PostMapping("/{id}/loaihocphan")
    public ResponseEntity<?> themLoaiHocPhan(
        @PathVariable Long id,
        @RequestBody ThemLoaiHocPhanRequest request,
        HttpServletRequest httpRequest) {

//        if (!AuthorizationUtils.isAdmin(httpRequest) && !AuthorizationUtils.isThuKy(httpRequest)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền thêm loại học phần.");
//        }

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
//    @PutMapping("/{id}/loaihocphan/{loaiHocPhan}")
//    public ResponseEntity<?> capNhatLoaiHocPhan(
//        @PathVariable Long id,
//        @PathVariable LoaiHocPhan loaiHocPhan,
//        @RequestBody CapNhatLoaiHocPhanRequest request,
//        HttpServletRequest httpRequest) {
//
        
//
//        try {
//            service.capNhatLoaiHocPhan(id, loaiHocPhan, request);
//            return ResponseEntity.ok().body("✅ Đã cập nhật loại học phần thành công");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
//        }
//    }
    @PutMapping("/{id}/loaihocphan/{loaiHocPhanId}")
    public ResponseEntity<?> capNhatLoaiHocPhan(
        @PathVariable Long id,
        @PathVariable Long loaiHocPhanId,  // Đổi từ LoaiHocPhan sang Long
        @RequestBody CapNhatLoaiHocPhanRequest request,
        HttpServletRequest httpRequest) {

        try {
            service.capNhatLoaiHocPhan(id, loaiHocPhanId, request);
            return ResponseEntity.ok().body("✅ Đã cập nhật loại học phần thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
        }
    }

 // API lấy danh sách loại học phần của chương trình
    @GetMapping("/{id}/loaihocphan")
    public ResponseEntity<List<LoaiHocPhanDTO>> getLoaiHocPhanByChuongTrinh(@PathVariable Long id) {
        List<ChuongTrinhHoc_LoaiHocPhan> list = service.getLoaiHocPhanByChuongTrinh(id);
        List<LoaiHocPhanDTO> dtoList = list.stream()
            .map(ChuongTrinhHocMapper::toLoaiHocPhanDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }



    
    @DeleteMapping("/{chuongTrinhId}/hocphan/{hocPhanId}")
    public ResponseEntity<?> removeHocPhan(
        @PathVariable Long chuongTrinhId,
        @PathVariable Long hocPhanId,
        HttpServletRequest request) {

//        if (!AuthorizationUtils.isAdmin(request) && !AuthorizationUtils.isThuKy(request)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền xóa học phần.");
//        }

        try {
            service.removeHocPhanFromChuongTrinh(chuongTrinhId, hocPhanId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/hocphan")
    public ResponseEntity<List<HocPhan>> getHocPhanByChuongTrinh(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHocPhanByChuongTrinh(id));
    }
    
    @GetMapping("/{id}/hocphan/search")
    public ResponseEntity<List<HocPhan>> searchHocPhanNotInChuongTrinh(
        @PathVariable Long id,
        @RequestParam String keyword) {
        
        return ResponseEntity.ok(service.searchHocPhanNotInChuongTrinh(id, keyword));
    }
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
            
            return ResponseEntity.ok(results != null ? results : Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    } 
    
	@Autowired
	private ToHopHocPhanService toHopHocPhanService;
	
	// Lấy danh sách tổ hợp trong 1 loại học phần
	@GetMapping("/ctlhp/{ctlhpId}/tohop")
	public ResponseEntity<List<ToHopHocPhanDTO>> getToHopByCTLHP(@PathVariable Long ctlhpId) {
	    return ResponseEntity.ok(toHopHocPhanService.findByChuongTrinhHocLoaiHocPhanId(ctlhpId));
	}
	
	//tạo mới tổ hợp học phần
	@PostMapping("/tohop")
	public ResponseEntity<?> createOrUpdateToHopHocPhan(@RequestBody ToHopHocPhanRequest request) {
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
	
	//cập nhật tổ hợp học phần
	@PutMapping("/tohop/{toHopId}")
	public ResponseEntity<?> updateToHopHocPhan(@PathVariable Long toHopId,
	                                            @RequestBody ToHopHocPhanRequest request) {
	    try {
	        toHopHocPhanService.updateToHopHocPhan(toHopId, request);
	        return ResponseEntity.ok("✅ Cập nhật tổ hợp thành công");
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
	    }
	}
	//xóa tổ hợp học phần
	@DeleteMapping("/tohop/{toHopId}")
	public ResponseEntity<?> deleteToHopHocPhan(@PathVariable Long toHopId) {
	    try {
	        toHopHocPhanService.deleteToHopHocPhan(toHopId);
	        return ResponseEntity.ok("✅ Đã xóa tổ hợp");
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
	    }
	}
	
	
	//thêm học phần vào tổ hợp học phần
	@PostMapping("/tohop/{toHopId}/hocphan")
	public ResponseEntity<?> addHocPhanToToHop(
	    @PathVariable Long toHopId,
	    @RequestBody List<Long> hocPhanIds) {
	
	    try {
	        List<String> result = toHopHocPhanService.addHocPhansToToHop(toHopId, hocPhanIds);
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("❌ Lỗi: " + e.getMessage());
	    }
	}
	
	//xóa học phần khỏi tổ hợp học phần
	@DeleteMapping("/tohop/{toHopId}/hocphan/{hocPhanId}")
	public ResponseEntity<?> removeHocPhanFromToHop(
	    @PathVariable Long toHopId,
	    @PathVariable Long hocPhanId
	) {
	    toHopHocPhanService.removeHocPhanFromToHop(toHopId, hocPhanId);
	    return ResponseEntity.ok("✅ Đã xóa học phần khỏi tổ hợp");
	}

	//tạo mới tổ hợp nhóm môn
	@PostMapping("/tohopnhommon")
	public ResponseEntity<?> createOrUpdateToHopNhomMon(@RequestBody ToHopNhomMon toHopNhomMon) {
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
	//lấy tất cả tổ hợp nhóm môn theo toHopHocPhanId
	@GetMapping("/tohop/{toHopId}/tohopnhommon")
	public ResponseEntity<List<ToHopNhomMonDTO>> getNhomMonsByToHop(@PathVariable Long toHopId) {
	  List<ToHopNhomMonDTO> nhoms = toHopNhomMonService.getByToHopId(toHopId);
	  return ResponseEntity.ok(nhoms);
	}
	
	//lấy thông tin tổ hợp nhóm môn
	//@GetMapping("/tohopnhommon/{toHopId}")
	//public ResponseEntity<List<ToHopNhomMonDTO>> getToHopNhomMonByToHop(@PathVariable Long toHopId) {
	//    return ResponseEntity.ok(toHopNhomMonService.getByToHopId(toHopId));
	//}
	//xóa tổ hợp nhóm môn
	@DeleteMapping("/tohopnhommon/{id}")
	public ResponseEntity<?> deleteNhomMon(@PathVariable Long id) {
	    toHopNhomMonService.deleteToHopNhomMon(id);
	    return ResponseEntity.ok("Đã xóa tổ hợp nhóm môn thành công");
	}
	
	//thêm học phần vào tổ hợp nhóm môn
	@PostMapping("/tohopnhommon/{id}/hocphan")
	public ResponseEntity<?> addHocPhanToNhomMon(@PathVariable Long id, @RequestBody List<Long> hocPhanIds) {
	    List<String> resultLogs = toHopNhomMonService.addHocPhanToNhomMon(id, hocPhanIds);
	    return ResponseEntity.ok(resultLogs);
	}
	
	//xóa học phần khỏi tổ hợp nhóm môn
	@DeleteMapping("/tohopnhommon/{nhomMonId}/hocphan/{hocPhanId}")
	public ResponseEntity<String> removeHocPhan(@PathVariable Long nhomMonId, @PathVariable Long hocPhanId) {
	    toHopNhomMonService.removeHocPhanFromNhomMon(nhomMonId, hocPhanId);
	    return ResponseEntity.ok("Đã xóa học phần khỏi nhóm môn");
	}

}