package com.cdtn.computerstore.dto.thongke.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ThongKeSanPhamRequest {
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
