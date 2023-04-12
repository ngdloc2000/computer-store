package com.cdtn.computerstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "specification")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
