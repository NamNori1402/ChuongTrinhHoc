package com.thanglong.chonlichthilai.chuongtrinhhoc.mapper;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.*;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.*;
import com.thanglong.chonlichthilai.hocphan.HocPhan;

import java.util.*;
import java.util.stream.Collectors;

public class ChuongTrinhHocMapper {

    public static ChuongTrinhHocDTO toDTO(ChuongTrinhHoc entity) {
        ChuongTrinhHocDTO dto = new ChuongTrinhHocDTO();
        dto.setId(entity.getId());
        dto.setChuyenNganh(entity.getChuyenNganh());
        dto.setKhoa(entity.getKhoa());

        List<LoaiHocPhanDTO> loaiHocPhanDTOs = new ArrayList<>();

        if (entity.getCacLoaiHocPhan() != null) {
            for (ChuongTrinhHoc_LoaiHocPhan lhp : entity.getCacLoaiHocPhan()) {
                LoaiHocPhanDTO lhpDTO = new LoaiHocPhanDTO();
                lhpDTO.setId(lhp.getId());
                lhpDTO.setLoaiHocPhan(lhp.getLoaiHocPhan().getDisplayName());
                lhpDTO.setSoTinChiToiThieu(lhp.getSoTinChiToiThieu());
                lhpDTO.setChuThich(lhp.getChuThich());
                lhpDTO.setMaBatDauDuocTinh(lhp.getMaBatDauDuocTinh());

                // Tổ hợp
                List<ToHopHocPhanDTO> toHopDTOs = new ArrayList<>();
                if (lhp.getToHops() != null) {
                    for (ToHopHocPhan toHop : lhp.getToHops()) {
                        ToHopHocPhanDTO toHopDTO = new ToHopHocPhanDTO();
                        toHopDTO.setId(toHop.getId());
                        toHopDTO.setTenToHop(toHop.getTenToHop());
                        toHopDTO.setSoTinChiToiThieu(toHop.getSoTinChiToiThieu());
                        toHopDTO.setSoLuongMonToiThieu(toHop.getSoLuongMonToiThieu());
                        toHopDTO.setBatBuocHocTatCa(toHop.getBatBuocHocTatCa());

                        List<HocPhanDTO> hpDTOs = new ArrayList<>();
                        if (toHop.getChiTietList() != null) {
                            for (ToHopHocPhanChiTiet chiTiet : toHop.getChiTietList()) {
                                HocPhan hp = chiTiet.getHocPhan();
                                if (hp != null) {
                                    HocPhanDTO hpDTO = convertToHocPhanDTO(hp, null);
                                    hpDTOs.add(hpDTO);
                                }
                            }
                        }
                        toHopDTO.setHocPhans(hpDTOs);

                        // Nhóm môn
                        List<ToHopNhomMonDTO> nhomMonDTOs = new ArrayList<>();
                        if (toHop.getToHopNhomMons() != null) {
                            for (ToHopNhomMon nhom : toHop.getToHopNhomMons()) {
                                ToHopNhomMonDTO nhomDTO = ToHopNhomMonDTO.builder()
                                        .id(nhom.getId())
                                        .tenNhom(nhom.getTenNhom())
                                        .toHopHocPhanId(toHop.getId())
                                        .tenToHop(toHop.getTenToHop())
                                        .hocPhans(
                                            nhom.getChiTietMonHoc().stream()
                                                .map(ct -> convertToHocPhanDTO(ct.getHocPhan(), null))
                                                .collect(Collectors.toList())
                                        )
                                        .build();
                                nhomMonDTOs.add(nhomDTO);
                            }
                        }

                        toHopDTO.setToHopNhomMons(nhomMonDTOs);
                        toHopDTOs.add(toHopDTO);
                    }
                }

                // Học phần ngoài tổ hợp
                List<HocPhanDTO> hocPhanNgoaiToHop = new ArrayList<>();
                if (lhp.getHocPhans() != null) {
                    for (ChuongTrinhHoc_HocPhan cthp : lhp.getHocPhans()) {
                        if (cthp.getToHop() == null && cthp.getHocPhan() != null) {
                            hocPhanNgoaiToHop.add(convertToHocPhanDTO(cthp.getHocPhan(), null));
                        }
                    }
                }

                lhpDTO.setToHops(toHopDTOs);
                lhpDTO.setHocPhansNgoaiToHop(hocPhanNgoaiToHop);
                loaiHocPhanDTOs.add(lhpDTO);
            }
        }

        dto.setLoaiHocPhans(loaiHocPhanDTOs);
        return dto;
    }
    public static LoaiHocPhanDTO toLoaiHocPhanDTO(ChuongTrinhHoc_LoaiHocPhan lhp) {
        LoaiHocPhanDTO lhpDTO = new LoaiHocPhanDTO();
        lhpDTO.setId(lhp.getId());
        lhpDTO.setLoaiHocPhan(lhp.getLoaiHocPhan().getDisplayName());
        lhpDTO.setSoTinChiToiThieu(lhp.getSoTinChiToiThieu());
        lhpDTO.setChuThich(lhp.getChuThich());
        lhpDTO.setMaBatDauDuocTinh(lhp.getMaBatDauDuocTinh());

        // Tổ hợp
        List<ToHopHocPhanDTO> toHopDTOs = new ArrayList<>();
        if (lhp.getToHops() != null) {
            for (ToHopHocPhan toHop : lhp.getToHops()) {
                ToHopHocPhanDTO toHopDTO = new ToHopHocPhanDTO();
                toHopDTO.setId(toHop.getId());
                toHopDTO.setTenToHop(toHop.getTenToHop());
                toHopDTO.setSoTinChiToiThieu(toHop.getSoTinChiToiThieu());
                toHopDTO.setSoLuongMonToiThieu(toHop.getSoLuongMonToiThieu());
                toHopDTO.setBatBuocHocTatCa(toHop.getBatBuocHocTatCa());

                List<HocPhanDTO> hpDTOs = new ArrayList<>();
                if (toHop.getChiTietList() != null) {
                    for (ToHopHocPhanChiTiet chiTiet : toHop.getChiTietList()) {
                        HocPhan hp = chiTiet.getHocPhan();
                        if (hp != null) {
                            hpDTOs.add(convertToHocPhanDTO(hp, null));
                        }
                    }
                }
                toHopDTO.setHocPhans(hpDTOs);

                // Nhóm môn
                List<ToHopNhomMonDTO> nhomMonDTOs = new ArrayList<>();
                if (toHop.getToHopNhomMons() != null) {
                    for (ToHopNhomMon nhom : toHop.getToHopNhomMons()) {
                        ToHopNhomMonDTO nhomDTO = ToHopNhomMonDTO.builder()
                            .id(nhom.getId())
                            .tenNhom(nhom.getTenNhom())
                            .toHopHocPhanId(toHop.getId())
                            .tenToHop(toHop.getTenToHop())
                            .hocPhans(
                                nhom.getChiTietMonHoc().stream()
                                    .map(ct -> convertToHocPhanDTO(ct.getHocPhan(), null))
                                    .collect(Collectors.toList())
                            )
                            .build();
                        nhomMonDTOs.add(nhomDTO);
                    }
                }

                toHopDTO.setToHopNhomMons(nhomMonDTOs);
                toHopDTOs.add(toHopDTO);
            }
        }

        // Học phần ngoài tổ hợp
        List<HocPhanDTO> hocPhanNgoaiToHop = new ArrayList<>();
        if (lhp.getHocPhans() != null) {
            for (ChuongTrinhHoc_HocPhan cthp : lhp.getHocPhans()) {
                if (cthp.getToHop() == null && cthp.getHocPhan() != null) {
                    hocPhanNgoaiToHop.add(convertToHocPhanDTO(cthp.getHocPhan(), null));
                }
            }
        }

        lhpDTO.setToHops(toHopDTOs);
        lhpDTO.setHocPhansNgoaiToHop(hocPhanNgoaiToHop);
        return lhpDTO;
    }

    public static HocPhanDTO convertToHocPhanDTO(HocPhan hp, String loaiHocPhan) {
        HocPhanDTO dto = new HocPhanDTO();
        dto.setId(hp.getId());
        dto.setMaHocPhan(hp.getMaHocPhan());
        dto.setTenHocPhan(hp.getTenHocPhan());
        dto.setTienQuyet(hp.getTienQuyet());
        dto.setNganhPhuTrach(hp.getNganhPhuTrach());
        dto.setLienLac(hp.getLienLac());
        dto.setMaGiangVien(hp.getMaGiangVien());
        dto.setMoRong(hp.getMoRong());
        dto.setSoTinChi(hp.getSoTinChi());
        dto.setMonThayThe(hp.getMonThayThe());
        dto.setMaMonThayThe(hp.getMaMonThayThe());
        dto.setSoCa(hp.getSoCa());
        //dto.setLoaiHocPhan(loaiHocPhan);
        return dto;
    }
}