package com.cdtn.computerstore.dto.thongke.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThongKeSanPhamTheoDanhMucResponse {
    private String tenDanhMuc;
    private Long soLuongSanPham;
    private Long tongTienDaBan;
}
