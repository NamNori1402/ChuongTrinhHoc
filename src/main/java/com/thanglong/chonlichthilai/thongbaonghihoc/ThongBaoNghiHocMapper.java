package com.thanglong.chonlichthilai.thongbaonghihoc;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper dùng để chuyển đổi giữa entity ThongBaoNghiHoc và DTO ThongBaoNghiHocDto.
 */
@Component
public class ThongBaoNghiHocMapper {

    /**
     * Chuyển đổi từ entity ThongBaoNghiHoc sang DTO.
     * @param entity Đối tượng entity cần chuyển đổi.
     * @return Đối tượng DTO tương ứng.
     */
    public ThongBaoNghiHocDto toDto(ThongBaoNghiHoc entity) {
        if (entity == null) {
            return null;
        }
        return new ThongBaoNghiHocDto(
                entity.getId(),
                entity.getMaGiangVien(),
                entity.getMaTKBChiTiet(),
                entity.getHocBu(),
                entity.getLyDo(),
                entity.getNgayGuiThongBao()
        );
    }

    /**
     * Chuyển đổi từ DTO ThongBaoNghiHocDto sang entity.
     * @param dto Đối tượng DTO cần chuyển đổi.
     * @return Đối tượng entity tương ứng.
     */
    public ThongBaoNghiHoc toEntity(ThongBaoNghiHocDto dto) {
        if (dto == null) {
            return null;
        }
        ThongBaoNghiHoc entity = new ThongBaoNghiHoc();
        entity.setId(dto.getId());
        entity.setMaGiangVien(dto.getMaGiangVien());
        entity.setMaTKBChiTiet(dto.getMaTKBChiTiet());
        entity.setHocBu(dto.getHocBu());
        entity.setLyDo(dto.getLyDo());
        entity.setNgayGuiThongBao(dto.getNgayGuiThongBao());
        return entity;
    }

    /**
     * Chuyển đổi danh sách entity sang danh sách DTO.
     * @param entityList Danh sách entity cần chuyển đổi.
     * @return Danh sách DTO tương ứng.
     */
    public List<ThongBaoNghiHocDto> toDtoList(List<ThongBaoNghiHoc> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Chuyển đổi danh sách DTO sang danh sách entity.
     * @param dtoList Danh sách DTO cần chuyển đổi.
     * @return Danh sách entity tương ứng.
     */
    public List<ThongBaoNghiHoc> toEntityList(List<ThongBaoNghiHocDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}