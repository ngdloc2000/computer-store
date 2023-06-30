package com.cdtn.computerstore.dto.thongke.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProductStatisticsByDay {
    LocalDate getDateTime();
    Long getTotalSold();
}
