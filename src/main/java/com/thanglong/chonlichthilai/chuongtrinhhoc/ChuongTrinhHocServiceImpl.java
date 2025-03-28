package com.thanglong.chonlichthilai.chuongtrinhhoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChuongTrinhHocServiceImpl implements ChuongTrinhHocService {

    @Autowired
    private ChuongTrinhHocRepository chuongTrinhHocRepository;
    
    @Autowired
    private MonHocRepository monHocRepository;

    @Override
    public ChuongTrinhHoc save(ChuongTrinhHoc e) {
        return chuongTrinhHocRepository.save(e);
    }

    @Override
    public List<ChuongTrinhHoc> findAll() {
        return chuongTrinhHocRepository.findAll();
    }

    @Override
    public ChuongTrinhHoc findById(Long id) {
        return chuongTrinhHocRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        chuongTrinhHocRepository.deleteById(id);
    }

    @Override
    public void addMonHocToChuongTrinh(Long chuongTrinhHocId, MonHoc monHoc) {
        ChuongTrinhHoc chuongTrinhHoc = chuongTrinhHocRepository.findById(chuongTrinhHocId).orElse(null);
        if (chuongTrinhHoc != null) {
            monHocRepository.save(monHoc);
            chuongTrinhHoc.getMonHocList().add(monHoc);
            chuongTrinhHocRepository.save(chuongTrinhHoc);
        }
    }

	@Override
	public List<ChuongTrinhHoc> findByKhoa(String khoaHoc) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void removeMonHocFromChuongTrinh(Long chuongTrinhHocId, Long monHocId) {
	    // 1. Tìm chương trình học (dùng orElseThrow như tin nhắn trước)
	    ChuongTrinhHoc chuongTrinhHoc = chuongTrinhHocRepository.findById(chuongTrinhHocId)
	        .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình học"));

	    // 2. Xóa môn học bằng removeIf (đơn giản như giải pháp mới)
	    boolean isRemoved = chuongTrinhHoc.getMonHocList().removeIf(
	        monHoc -> monHoc.getId().equals(monHocId)
	    );
	    
	    if (!isRemoved) {
	        throw new RuntimeException("Không tìm thấy môn học trong chương trình");
	    }

	    // 3. Lưu lại (không xóa từ MonHocRepository)
	    chuongTrinhHocRepository.save(chuongTrinhHoc); 
	}
}
