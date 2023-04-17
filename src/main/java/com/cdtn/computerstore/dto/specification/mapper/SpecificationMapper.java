package com.cdtn.computerstore.dto.specification.mapper;

import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.entity.Specification;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.enums.SpecificationEnum;
import org.springframework.stereotype.Component;

@Component
public class SpecificationMapper {

    public Specification createSpecification(Long productId, ProductCreationForm form) {

        return Specification.builder()
                .productId(productId)
                .color(ProductEnum.Color.checkValue(form.getColor()))
                .sizeDescription(form.getSizeDescription())
                .battery(form.getBattery())
                .weight(form.getWeight())
                .numberOfButtons(form.getNumberOfButtons())
                .mouseType(SpecificationEnum.MouseType.checkValue(form.getMouseType()))
                .mouseHandType(form.getMouseHandType())
                .mouseConnectionType(form.getMouseConnectionType())
                .cpuModel(form.getCpuModel())
                .cpuSeries(SpecificationEnum.CpuSeries.checkValue(form.getCpuSeries()))
                .cpuSpeed(form.getCpuSpeed())
                .cpuSocket(SpecificationEnum.CpuSocket.checkValue(form.getCpuSocket()))
                .cpuNumberOfProcessors(form.getCpuNumberOfProcessors())
                .cpuNumberOfProcessingThreads(form.getCpuNumberOfProcessingThreads())
                .ramModel(form.getRamModel())
                .ramSeries(SpecificationEnum.RamSeries.checkValue(form.getRamSeries()))
                .ramBus(form.getRamBus())
                .ramTiming(form.getRamTiming())
                .ramVoltage(form.getRamVoltage())
                .ramCapacity(SpecificationEnum.RamCapacity.checkValue(form.getRamCapacity()))
                .monitorModel(form.getMonitorModel())
                .monitorSize(SpecificationEnum.MonitorSize.checkValue(form.getMonitorSize()))
                .monitorResolution(SpecificationEnum.MonitorResolution.checkValue(form.getMonitorResolution()))
                .monitorPanel(SpecificationEnum.MonitorPanel.checkValue(form.getMonitorPanel()))
                .monitorType(form.getMonitorType())
                .monitorBrightness(form.getMonitorBrightness())
                .monitorRefreshRate(SpecificationEnum.MonitorRefreshRate.checkValue(form.getMonitorRefreshRate()))
                .monitorResponseTime(form.getMonitorResponseTime())
                .hardDiskSeries(SpecificationEnum.HardDiskSeries.checkValue(form.getHardDiskSeries()))
                .hardDiskType(SpecificationEnum.HardDiskType.checkValue(form.getHardDiskType()))
                .hardDiskCapacity(SpecificationEnum.HardDiskCapacity.checkValue(form.getHardDiskCapacity()))
                .hardDiskConnectionType(SpecificationEnum.HardDiskConnectionType.checkValue(form.getHardDiskConnectionType()))
                .hardDiskReadingSpeed(form.getHardDiskReadingSpeed())
                .hardDiskWritingSpeed(form.getHardDiskWritingSpeed())
                .vgaModel(form.getVgaModel())
                .keyboardModel(form.getKeyboardModel())
                .laptopStorage(form.getLaptopStorage())
                .laptopOperatingSystem(form.getLaptopOperatingSystem())
                .laptopSeries(SpecificationEnum.LaptopSeries.checkValue(form.getLaptopSeries()))
                .laptopMaxNumberOfStoragePorts(form.getLaptopMaxNumberOfStoragePorts())
                .laptopConnectionPort(form.getLaptopConnectionPort())
                .laptopOutputPort(form.getLaptopOutputPort())
                .build();
    }

    public void updateSpecification(Specification specification, ProductCreationForm form) {

        specification.setColor(ProductEnum.Color.checkValue(form.getColor()));
        specification.setSizeDescription(form.getSizeDescription());
        specification.setBattery(form.getBattery());
        specification.setWeight(form.getWeight());
        specification.setNumberOfButtons(form.getNumberOfButtons());
        specification.setMouseType(SpecificationEnum.MouseType.checkValue(form.getMouseType()));
        specification.setMouseHandType(form.getMouseHandType());
        specification.setMouseConnectionType(form.getMouseConnectionType());
        specification.setCpuModel(form.getCpuModel());
        specification.setCpuSeries(SpecificationEnum.CpuSeries.checkValue(form.getCpuSeries()));
        specification.setCpuSpeed(form.getCpuSpeed());
        specification.setCpuSocket(SpecificationEnum.CpuSocket.checkValue(form.getCpuSocket()));
        specification.setCpuNumberOfProcessors(form.getCpuNumberOfProcessors());
        specification.setCpuNumberOfProcessingThreads(form.getCpuNumberOfProcessingThreads());
        specification.setRamModel(form.getRamModel());
        specification.setRamSeries(SpecificationEnum.RamSeries.checkValue(form.getRamSeries()));
        specification.setRamBus(form.getRamBus());
        specification.setRamTiming(form.getRamTiming());
        specification.setRamVoltage(form.getRamVoltage());
        specification.setRamCapacity(SpecificationEnum.RamCapacity.checkValue(form.getRamCapacity()));
        specification.setMonitorModel(form.getMonitorModel());
        specification.setMonitorSize(SpecificationEnum.MonitorSize.checkValue(form.getMonitorSize()));
        specification.setMonitorResolution(SpecificationEnum.MonitorResolution.checkValue(form.getMonitorResolution()));
        specification.setMonitorPanel(SpecificationEnum.MonitorPanel.checkValue(form.getMonitorPanel()));
        specification.setMonitorType(form.getMonitorType());
        specification.setMonitorBrightness(form.getMonitorBrightness());
        specification.setMonitorRefreshRate(SpecificationEnum.MonitorRefreshRate.checkValue(form.getMonitorRefreshRate()));
        specification.setMonitorResponseTime(form.getMonitorResponseTime());
        specification.setHardDiskSeries(SpecificationEnum.HardDiskSeries.checkValue(form.getHardDiskSeries()));
        specification.setHardDiskType(SpecificationEnum.HardDiskType.checkValue(form.getHardDiskType()));
        specification.setHardDiskCapacity(SpecificationEnum.HardDiskCapacity.checkValue(form.getHardDiskCapacity()));
        specification.setHardDiskConnectionType(SpecificationEnum.HardDiskConnectionType.checkValue(form.getHardDiskConnectionType()));
        specification.setHardDiskReadingSpeed(form.getHardDiskReadingSpeed());
        specification.setHardDiskWritingSpeed(form.getHardDiskWritingSpeed());
        specification.setVgaModel(form.getVgaModel());
        specification.setKeyboardModel(form.getKeyboardModel());
        specification.setLaptopStorage(form.getLaptopStorage());
        specification.setLaptopOperatingSystem(form.getLaptopOperatingSystem());
        specification.setLaptopSeries(SpecificationEnum.LaptopSeries.checkValue(form.getLaptopSeries()));
        specification.setLaptopMaxNumberOfStoragePorts(form.getLaptopMaxNumberOfStoragePorts());
        specification.setLaptopConnectionPort(form.getLaptopConnectionPort());
        specification.setLaptopOutputPort(form.getLaptopOutputPort());
    }
}
