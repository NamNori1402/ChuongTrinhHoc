package com.thanglong.chonlichthilai.chuongtrinhhoc.service;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhan;

import java.util.List;

	public interface ToHopHocPhanService {
		List<ToHopHocPhanDTO> findByChuongTrinhHocLoaiHocPhanId(Long ctLHPId);    
		List<ToHopHocPhan> findEntitiesByChuongTrinhHocLoaiHocPhanId(Long ctLHPId);

		ToHopHocPhan createToHopHocPhan(ToHopHocPhanRequest request);
	    ToHopHocPhan updateToHopHocPhan(Long id, ToHopHocPhanRequest request);
	    void deleteToHopHocPhan(Long id);
	    
	    List<String> addHocPhansToToHop(Long toHopId, List<Long> hocPhanIds);
	    void removeHocPhanFromToHop(Long toHopId, Long hocPhanId);
	
	}
