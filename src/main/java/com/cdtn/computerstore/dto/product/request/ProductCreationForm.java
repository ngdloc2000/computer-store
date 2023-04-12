package com.cdtn.computerstore.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductCreationForm {

//    Thông tin bảng Product
    @NotNull
    private Long categoryId;
    @NotBlank
    private String name;
    private String imageMain;
    private Integer brand;
    private String description;
    private Long price;
    private Double discount;
    private Integer quantity;
    @NotNull
    private Integer featured;
    private Integer warranty;

//    Thông tin bảng Specificatio
    private Long productId;
    private Integer color;
    private String sizeDescription;
    private String battery;
    private String weight;
    private Integer numberOfButtons;
    private Integer mouseType;
    private String mouseHandType;
    private String mouseConnectionType;
    private String cpuModel;
    private Integer cpuSeries;
    private Double cpuSpeed;
    private Integer cpuSocket;
    private Integer cpuNumberOfProcessors;
    private Integer cpuNumberOfProcessingThreads;
    private String ramModel;
    private Integer ramSeries;
    private Integer ramBus;
    private Integer ramTiming;
    private Double ramVoltage;
    private Integer ramCapacity;
    private String monitorModel;
    private Integer monitorSeries;
    private Integer monitorSize;
    private Integer monitorResolution;
    private Integer monitorPanel;
    private String monitorType;
    private String monitorBrightness;
    private Integer monitorRefreshRate;
    private String monitorResponseTime;
    private String hardDiskModel;
    private Integer hardDiskSeries;
    private Integer hardDiskType;
    private Integer hardDiskCapacity;
    private Integer hardDiskConnectionType;
    private Integer hardDiskReadingSpeed;
    private Integer hardDiskWritingSpeed;
    private String vgaModel;
    private String keyboardModel;
    private String laptopStorage;
    private String laptopOperatingSystem;
    private Integer laptopSeries;
    private String laptopMaxNumberOfStoragePorts;
    private String laptopConnectionPort;
    private String laptopOutputPort;
}
