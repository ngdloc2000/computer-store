package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.enums.SpecificationEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enum")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EnumController {

    @GetMapping("/brandProduct")
    public ResponseEntity<BaseResponseData> getBrandProduct() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", ProductEnum.Brand.getList()));
    }

    @GetMapping("/colorProduct")
    public ResponseEntity<BaseResponseData> getColorProduct() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", ProductEnum.Color.getList()));
    }

    @GetMapping("/mouseType")
    public ResponseEntity<BaseResponseData> getMouseType() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.MouseType.getList()));
    }

    @GetMapping("/cpuSeries")
    public ResponseEntity<BaseResponseData> getCpuSeries() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.CpuSeries.getList()));
    }

    @GetMapping("/cpuSocket")
    public ResponseEntity<BaseResponseData> getCpuSocket() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.CpuSocket.getList()));
    }

    @GetMapping("/ramSeries")
    public ResponseEntity<BaseResponseData> getRamSeries() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.RamSeries.getList()));
    }

    @GetMapping("/ramCapacity")
    public ResponseEntity<BaseResponseData> getRamCapacity() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.RamCapacity.getList()));
    }

    @GetMapping("/getMonitorSize")
    public ResponseEntity<BaseResponseData> getMonitorSize() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.MonitorSize.getList()));
    }

    @GetMapping("/monitorResolution")
    public ResponseEntity<BaseResponseData> getMonitorResolution() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.MonitorResolution.getList()));
    }

    @GetMapping("/monitorPanel")
    public ResponseEntity<BaseResponseData> getMonitorPanel() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.MonitorPanel.getList()));
    }

    @GetMapping("/monitorRefreshRate")
    public ResponseEntity<BaseResponseData> getMonitorRefreshRate() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.MonitorRefreshRate.getList()));
    }

    @GetMapping("/hardDiskSeries")
    public ResponseEntity<BaseResponseData> getHardDiskSeries() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.HardDiskSeries.getList()));
    }

    @GetMapping("/hardDiskType")
    public ResponseEntity<BaseResponseData> getHardDiskType() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.HardDiskType.getList()));
    }

    @GetMapping("/hardDiskConnectionType")
    public ResponseEntity<BaseResponseData> getHardDiskConnectionType() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.HardDiskConnectionType.getList()));
    }

    @GetMapping("/hardDiskCapacity")
    public ResponseEntity<BaseResponseData> getHardDiskCapacity() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.HardDiskCapacity.getList()));
    }

    @GetMapping("/laptopSeries")
    public ResponseEntity<BaseResponseData> getLaptopSeries() {
        return ResponseEntity.ok(new BaseResponseData(200, "Success", SpecificationEnum.LaptopSeries.getList()));
    }
}
