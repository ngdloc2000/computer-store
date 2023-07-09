package com.cdtn.computerstore.dto.thongke.response;

import com.cdtn.computerstore.enums.ProductEnum;
import lombok.Data;

@Data
public class ThongKeSanPhamBanChayResponse {
    private String tenSanPham;
    private Long soLuongSanPham;
    private String brand;

    public ThongKeSanPhamBanChayResponse(String tenSanPham, Long soLuongSanPham, Integer brand) {
        this.tenSanPham = tenSanPham;
        this.soLuongSanPham = soLuongSanPham;
        this.brand = ProductEnum.Brand.getNameByValue(brand);
    }
}
