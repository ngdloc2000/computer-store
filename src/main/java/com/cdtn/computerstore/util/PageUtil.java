package com.cdtn.computerstore.util;

import org.springframework.data.domain.*;

import java.util.List;

public class PageUtil {

    public static <T> Page<T> getPage(List<T> data, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        int total = data.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<T> content = data.subList(start, end);

        return new PageImpl<>(content, pageable, total);
    }
}
