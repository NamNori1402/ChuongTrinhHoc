package com.thanglong.chonlichthilai.hocphan;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HocPhanThayTheService {
    List<HocPhanThayTheDTO> getAllHocPhanThayThe();
    void addHocPhanThayThe(HocPhanThayTheDTO dto);
    void deleteHocPhanThayThe(String maHocPhanGoc, String maHocPhanThayThe); // Giữ nguyên đầu vào là mã học phần (tiện cho frontend)
    void deleteById(Long id);
	List<HocPhanThayThe> addMultipleHocPhanThayThe(List<HocPhanThayTheDTO> dtos);
	
	
}