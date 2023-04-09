package com.cdtn.computerstore.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectOptionResponse {
    private Integer value;
    private String name;
}
