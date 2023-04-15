package com.cdtn.computerstore.dto.product.mapper;

import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.response.ProductDetail;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.entity.Specification;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.enums.SpecificationEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product createProduct(ProductCreationForm form) {

        return Product.builder()
                .categoryId(form.getCategoryId())
                .name(form.getName())
                .imageMain(form.getImageMain())
                .brand(ProductEnum.Brand.checkValue(form.getBrand()))
                .description(form.getDescription())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .sold(0)
                .status(ProductEnum.Status.ACTIVE.getValue())
                .featured(ProductEnum.Featured.checkValue(form.getFeatured()))
                .warranty(form.getWarranty())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void updateProduct(Product product, ProductCreationForm form) {

        product.setCategoryId(form.getCategoryId());
        product.setName(form.getName());
        product.setImageMain(form.getImageMain());
        product.setBrand(ProductEnum.Brand.checkValue(form.getBrand()));
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setQuantity(form.getQuantity());
        product.setStatus(ProductEnum.Status.ACTIVE.getValue());
        product.setFeatured(ProductEnum.Featured.checkValue(form.getFeatured()));
        product.setWarranty(form.getWarranty());
        product.setUpdatedAt(LocalDateTime.now());
    }

    public ProductDetail createProductDetail(Product product, Specification specification, Category category) {

        return ProductDetail.builder()
                .productId(product.getId())
                .categoryName(category.getName())
                .productName(product.getName())
                .imageMain(product.getImageMain())
                .brand(ProductEnum.Brand.getNameByValue(product.getBrand()))
                .price(product.getPrice())
                .discount(product.getDiscount())
                .quantity(product.getQuantity())
                .sold(product.getSold())
                .status(product.getStatus())
                .featured(product.getFeatured())
                .warranty(product.getWarranty())
                .createdAt(product.getCreatedAt())
                .color(ProductEnum.Color.getNameByValue(specification.getColor()))
                .sizeDescription(specification.getSizeDescription())
                .battery(specification.getBattery())
                .weight(specification.getWeight())
                .numberOfButtons(specification.getNumberOfButtons())
                .mouseType(SpecificationEnum.MouseType.getNameByValue(specification.getMouseType()))
                .mouseHandType(specification.getMouseHandType())
                .mouseConnectionType(specification.getMouseConnectionType())
                .cpuModel(specification.getCpuModel())
                .cpuSeries(SpecificationEnum.CpuSeries.getNameByValue(specification.getCpuSeries()))
                .cpuSpeed(specification.getCpuSpeed())
                .cpuSocket(SpecificationEnum.CpuSocket.getNameByValue(specification.getCpuSocket()))
                .cpuNumberOfProcessors(specification.getCpuNumberOfProcessors())
                .cpuNumberOfProcessingThreads(specification.getCpuNumberOfProcessingThreads())
                .ramModel(specification.getRamModel())
                .ramSeries(SpecificationEnum.RamSeries.getNameByValue(specification.getRamSeries()))
                .ramBus(specification.getRamBus())
                .ramTiming(specification.getRamTiming())
                .ramVoltage(specification.getRamVoltage())
                .ramCapacity(specification.getRamCapacity())
                .monitorModel(specification.getMonitorModel())
                .monitorSize(SpecificationEnum.MonitorSize.getNameByValue(specification.getMonitorSize()))
                .monitorResolution(SpecificationEnum.MonitorResolution.getNameByValue(specification.getMonitorResolution()))
                .monitorPanel(SpecificationEnum.MonitorPanel.getNameByValue(specification.getMonitorPanel()))
                .monitorType(specification.getMonitorType())
                .monitorBrightness(specification.getMonitorBrightness())
                .monitorRefreshRate(SpecificationEnum.MonitorRefreshRate.getNameByValue(specification.getMonitorRefreshRate()))
                .monitorResponseTime(specification.getMonitorResponseTime())
                .hardDiskModel(specification.getHardDiskModel())
                .hardDiskSeries(SpecificationEnum.HardDiskSeries.getNameByValue(specification.getHardDiskSeries()))
                .hardDiskType(SpecificationEnum.HardDiskType.getNameByValue(specification.getHardDiskType()))
                .hardDiskCapacity(SpecificationEnum.HardDiskCapacity.getNameByValue(specification.getHardDiskCapacity()))
                .hardDiskConnectionType(SpecificationEnum.HardDiskConnectionType.getNameByValue(specification.getHardDiskConnectionType()))
                .hardDiskReadingSpeed(specification.getHardDiskReadingSpeed())
                .hardDiskWritingSpeed(specification.getHardDiskWritingSpeed())
                .vgaModel(specification.getVgaModel())
                .keyboardModel(specification.getKeyboardModel())
                .laptopStorage(specification.getLaptopStorage())
                .laptopOperatingSystem(specification.getLaptopOperatingSystem())
                .laptopSeries(SpecificationEnum.LaptopSeries.getNameByValue(specification.getLaptopSeries()))
                .laptopMaxNumberOfStoragePorts(specification.getLaptopMaxNumberOfStoragePorts())
                .laptopConnectionPort(specification.getLaptopConnectionPort())
                .laptopOutputPort(specification.getLaptopOutputPort())
                .build();
    }
}
