package com.cdtn.computerstore.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    private Long productId;
    private Long categoryId;
    private String categoryName;
    private String productName;
    private List<String> imageList;
    private String brand;
    private Long retailPrice;
    private Long latestPrice;
    private Double discount;
    private Integer quantity;
    private Integer sold;
    private Integer status;
    private Integer featured;
    private Integer warranty;
    private LocalDateTime createdAt;
    private String color;
    private String sizeDescription;
    private String battery;
    private String weight;
    private Integer numberOfButtons;
    private String mouseType;
    private String mouseHandType;
    private String mouseConnectionType;
    private String cpuModel;
    private String cpuSeries;
    private Double cpuSpeed;
    private String cpuSocket;
    private Integer cpuNumberOfProcessors;
    private Integer cpuNumberOfProcessingThreads;
    private String ramModel;
    private String ramSeries;
    private Integer ramBus;
    private Integer ramTiming;
    private Double ramVoltage;
    private String ramCapacity;
    private String monitorModel;
    private String monitorSize;
    private String monitorResolution;
    private String monitorPanel;
    private String monitorType;
    private String monitorBrightness;
    private String monitorRefreshRate;
    private String monitorResponseTime;
    private String hardDiskModel;
    private String hardDiskSeries;
    private String hardDiskType;
    private String hardDiskCapacity;
    private String hardDiskConnectionType;
    private Integer hardDiskReadingSpeed;
    private Integer hardDiskWritingSpeed;
    private String vgaModel;
    private String keyboardModel;
    private String laptopStorage;
    private String laptopOperatingSystem;
    private String laptopSeries;
    private String laptopMaxNumberOfStoragePorts;
    private String laptopConnectionPort;
    private String laptopOutputPort;
}
