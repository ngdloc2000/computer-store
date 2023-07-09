package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.thongke.request.ThongKeSanPhamRequest;
import com.cdtn.computerstore.dto.thongke.response.ProductStatisticsByDay;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamBanChayResponse;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamTheoDanhMucResponse;
import com.cdtn.computerstore.repository.thongke.ThongKeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public Map<String, Object> productStatisticsByDay(Integer day){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(day);

        List<LocalDate> times = new ArrayList<>();
        List<Long> sold = new ArrayList<>();
        Map<LocalDate, Long> dateValueMap = new HashMap<>();
        Iterable<ProductStatisticsByDay> productStatisticsByDay = thongKeRepository.productStatisticsByDay(day);
        for (ProductStatisticsByDay data : productStatisticsByDay) {
            dateValueMap.put(LocalDate.parse(data.getDateTime().toString()), data.getTotalSold());
        }
        Map<LocalDate, Long> result = processDateRange(startDate, endDate, dateValueMap);
        System.out.println(dateValueMap);
        for (LocalDate date : result.keySet()) {
            times.add(date);
            sold.add(result.get(date));
        }
        Map<String, Object> object = new HashMap<>();
        object.put("dateTime", times);
        object.put("sold", sold);
        return object;
    }

    public static Map<LocalDate, Long> processDateRange(LocalDate startDate, LocalDate endDate, Map<LocalDate, Long> dateValueMap) {
        Map<LocalDate, Long> result = new TreeMap<LocalDate, Long>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            result.put(currentDate, dateValueMap.getOrDefault(currentDate, 0L));
            currentDate = currentDate.plusDays(1);
        }
        return result;
    }
}
