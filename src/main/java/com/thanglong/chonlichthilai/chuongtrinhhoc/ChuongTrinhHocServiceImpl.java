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
    public List<ChuongTrinhHoc> findByKhoa(String khoaHoc) {
        // TODO: Triển khai sau nếu cần
        return null;
    }

    @Override
    public List<ChuongTrinhHoc> findByMonHoc(String maHocPhan, String tenMonHoc) {
        // Triển khai theo nhu cầu hoặc có thể throw UnsupportedOperationException
        // nếu không sử dụng phương thức này
        throw new UnsupportedOperationException("Phương thức này chưa được triển khai");
    }

    @Override
    public List<ChuongTrinhHoc> searchByKeyword(String keyword) {
        return chuongTrinhHocRepository.searchByKeyword(keyword);
    }

    @Override
    public List<ChuongTrinhHoc> findByMaHocPhan(String maHocPhan) {
        return chuongTrinhHocRepository.findByMonHocList_MaHocPhanContaining(maHocPhan);
    }

    @Override
    public List<ChuongTrinhHoc> findByTenMonHoc(String tenMonHoc) {
        return chuongTrinhHocRepository.findByMonHocList_TenMonHocContaining(tenMonHoc);
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
    public void removeMonHocFromChuongTrinh(Long chuongTrinhHocId, Long monHocId) {
        ChuongTrinhHoc chuongTrinhHoc = chuongTrinhHocRepository.findById(chuongTrinhHocId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình học"));

        boolean isRemoved = chuongTrinhHoc.getMonHocList().removeIf(
            monHoc -> monHoc.getId().equals(monHocId)
        );
        
        if (!isRemoved) {
            throw new RuntimeException("Không tìm thấy môn học trong chương trình");
        }

        chuongTrinhHocRepository.save(chuongTrinhHoc); 
    }
}