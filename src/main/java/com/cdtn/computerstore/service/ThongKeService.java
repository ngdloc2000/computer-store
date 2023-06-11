package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.thongke.request.ThongKeSanPhamRequest;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamBanChayResponse;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamTheoDanhMucResponse;
import com.cdtn.computerstore.repository.thongke.ThongKeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThongKeService {

    private final ThongKeRepository thongKeRepository;

    public List<ThongKeSanPhamTheoDanhMucResponse> thongKeTheoDanhMuc(ThongKeSanPhamRequest request) {

        return thongKeRepository.thongKeTheoDanhMuc(request);
    }

    public List<ThongKeSanPhamBanChayResponse> thongKeSanPhamBanChayTheoNgay(ThongKeSanPhamRequest request) {

        List<ThongKeSanPhamBanChayResponse> responses = thongKeRepository.thongKeSanPhamBanChay(request).stream().limit(10).toList();

        return responses;
    }
}
