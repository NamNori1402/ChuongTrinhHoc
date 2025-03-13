package com.thanglong.chonlichthilai.covanhoctap.cvht;
//Importing required classes
import java.util.List;




//Interface

public interface CoVanHocTapService {
	List<CoVanHocTap> findByMaKy(String maKy);
}