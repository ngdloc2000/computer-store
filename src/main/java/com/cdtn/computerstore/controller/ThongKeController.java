package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.thongke.request.ThongKeSanPhamRequest;
import com.cdtn.computerstore.dto.thongke.response.ProductStatisticsByDay;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamBanChayResponse;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamTheoDanhMucResponse;
import com.cdtn.computerstore.service.ThongKeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/thong-ke")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ThongKeController {

    private final ThongKeService thongKeService;

    @GetMapping("/san-pham-theo-danh-muc")
    public ResponseEntity<BaseResponseData> thongKeTheoDanhMuc(@Valid ThongKeSanPhamRequest request) {

        try {
            List<ThongKeSanPhamTheoDanhMucResponse> response = thongKeService.thongKeTheoDanhMuc(request);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new BaseResponseData(500, "Error", null));
        }
    }

    @GetMapping("/san-pham-ban-chay-theo-ngay")
    public ResponseEntity<BaseResponseData> thongKeSanPhamBanChayTheoNgay(@Valid ThongKeSanPhamRequest request) {

        try {
            List<ThongKeSanPhamBanChayResponse> response = thongKeService.thongKeSanPhamBanChayTheoNgay(request);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new BaseResponseData(500, "Error", null));
        }
    }

    @GetMapping("/san-pham-theo-ngay")
    public ResponseEntity<BaseResponseData> productStatisticsByDay(@RequestParam Integer day) {

        try {
            Map<String, Object> productStatisticsByDay = thongKeService.productStatisticsByDay(day);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", productStatisticsByDay));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new BaseResponseData(500, "Error", null));
        }
    }
}
