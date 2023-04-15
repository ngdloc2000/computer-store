package com.cdtn.computerstore.enums;

import com.cdtn.computerstore.dto.enums.SelectOptionResponse;
import com.cdtn.computerstore.exception.StoreException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecificationEnum {

    public enum MouseType {
        WIRELESS(0, "Wireless"),
        WIRED(1, "Wired");

        private final Integer value;
        private final String name;

        MouseType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MouseType.values())
                        .map(MouseType::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Mouse type not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(MouseType.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MouseType.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(MouseType::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("MouseType not found with value " + value));
            }
            return null;
        }
    }

    public enum CpuSeries {
        CORE_I3(1, "Core i3"),
        CORE_I5(2, "Core i5"),
        CORE_I7(3, "Core i7"),
        CORE_I9(4, "Core i9"),
        PENTIUM(5, "Pentium"),
        RYZEN_3(6, "Ryzen 3"),
        RYZEN_5(7, "Ryzen 5"),
        RYZEN_7(8, "Ryzen 7"),
        RYZEN_9(9, "Ryzen 9");

        private final Integer value;
        private final String name;

        CpuSeries(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(CpuSeries.values())
                        .map(CpuSeries::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Cpu series not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(CpuSeries.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(CpuSeries.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(CpuSeries::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("CpuSeries not found with value " + value));
            }
            return null;
        }
    }

    public enum CpuSocket {
        SOCKET_1150(1, "1150"),
        SOCKET_1151(2, "1151"),
        SOCKET_1151_V2(3, "1151-v2"),
        SOCKET_1200(4, "1200"),
        SOCKET_1700(5, "1700"),
        SOCKET_2066(6, "2066"),
        SOCKET_AM4(7, "AM4"),
        SOCKET_AM5(8, "AM5"),
        SOCKET_TR4(9, "TR4"),
        SOCKET_SWRX8(9, "sWRX8");

        private final Integer value;
        private final String name;

        CpuSocket(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(CpuSocket.values())
                        .map(CpuSocket::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Cpu socket not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(CpuSocket.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(CpuSocket.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(CpuSocket::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("CpuSocket not found with value " + value));
            }
            return null;
        }
    }

    public enum RamSeries {
        AORUS(1, "AORUS"),
        AEGIS(2, "Aegis"),
        BOLT(3, "BOLT"),
        BALLISTIX(4, "Ballistix"),
        CRAS(5, "CRAS"),
        DOMINATOR(6, "Dominator"),
        EVO(7, "EVO"),
        FURY(8, "Fury"),
        FURY_BEAST(9, "Fury Beast"),
        FURY_RENEGADE(10, "Fury Renegade"),
        PREMIER(11, "PREMIER"),
        PREDATOR(12, "Predator"),
        RIPJAWS(13, "RipJaws"),
        TRIDENT(14, "Trident"),
        VENGEANCE(15, "Vengeance"),
        XPG(16, "XPG"),
        ZEUS(17, "Zeus");

        private final Integer value;
        private final String name;

        RamSeries(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(RamSeries.values())
                        .map(RamSeries::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Ram series not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(RamSeries.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(RamSeries.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(RamSeries::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("RamSeries not found with value " + value));
            }
            return null;
        }
    }

    public enum MonitorSize {
        SIZE_14(1, "14\""),
        SIZE_15_6(2, "15.6\""),
        SIZE_18_5(3, "18.5\""),
        SIZE_19(4, "19\""),
        SIZE_19_5(5, "19.5\""),
        SIZE_20_7(6, "20.7\""),
        SIZE_21_45(7, "21.45\""),
        SIZE_21_5(8, "21.5\""),
        SIZE_22(9, "22\""),
        SIZE_23(10, "23\""),
        SIZE_23_5(11, "23.5\""),
        SIZE_23_6(12, "23.6\""),
        SIZE_23_8(13, "23.8\""),
        SIZE_24(14, "24\""),
        SIZE_24_5(15, "24.5\""),
        SIZE_25(16, "25\""),
        SIZE_27(17, "27\""),
        SIZE_27_6(18, "27.6\""),
        SIZE_27_9(19, "27.9\""),
        SIZE_28(20, "28\""),
        SIZE_29(21, "29\""),
        SIZE_30(22, "30\""),
        SIZE_31_5(23, "31.5\""),
        SIZE_32(24, "32\""),
        SIZE_34(25, "34\""),
        SIZE_35(26, "35\""),
        SIZE_37_5(27, "37.5\""),
        SIZE_38(28, "38\""),
        SIZE_43(29, "43\""),
        SIZE_48(30, "48\""),
        SIZE_49(31, "49\""),
        SIZE_49_5(32, "49.5\""),
        SIZE_55(33, "55\"");

        private final Integer value;
        private final String name;

        MonitorSize(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorSize.values())
                        .map(MonitorSize::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Monitor size not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(MonitorSize.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorSize.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(MonitorSize::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("MonitorSize not found with value " + value));
            }
            return null;
        }
    }

    public enum MonitorResolution {
        RESOLUTION_1366_768(1, "1366 x 768"),
        RESOLUTION_1440_900(2, "1440 x 900"),
        RESOLUTION_1600_900(3, "1600 x 900"),
        RESOLUTION_1920_1080(4, "1920 x 1080"),
        RESOLUTION_1920_1200(5, "1920 x 1200"),
        RESOLUTION_2560_1080(6, "2560 x 1080"),
        RESOLUTION_2560_1440(7, "2560 x 1440"),
        RESOLUTION_2560_1600(8, "2560 x 1600"),
        RESOLUTION_2560_2880(9, "2560 x 2880"),
        RESOLUTION_3440_1440(10, "3440 x 1440"),
        RESOLUTION_3840_1600(11, "3840 x 1600"),
        RESOLUTION_3840_2160(12, "3840 x 2160"),
        RESOLUTION_3840_2560(13, "3840 x 2560"),
        RESOLUTION_5120_1440(14, "5120 x 1440");

        private final Integer value;
        private final String name;

        MonitorResolution(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorResolution.values())
                        .map(MonitorResolution::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Monitor resolution not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(MonitorResolution.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorResolution.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(MonitorResolution::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("MonitorResolution not found with value " + value));
            }
            return null;
        }
    }

    public enum MonitorPanel {
        FAST_IPS(1, "Fast IPS"),
        IPS(2, "IPS"),
        MVA(3, "MVA"),
        NANO_IPS(4, "Nano IPS"),
        OLED(5, "OLED"),
        PLS(6, "PLS"),
        RAPID_IPS(7, "RAPID IPS"),
        TN(8, "TN"),
        VA(9, "VA");

        private final Integer value;
        private final String name;

        MonitorPanel(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorPanel.values())
                        .map(MonitorPanel::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Monitor panel not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(MonitorPanel.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorPanel.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(MonitorPanel::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("MonitorPanel not found with value " + value));
            }
            return null;
        }
    }

    public enum MonitorRefreshRate {
        RATE_100(1, "100Hz"),
        RATE_120(2, "120Hz"),
        RATE_140(3, "140Hz"),
        RATE_160(4, "160Hz"),
        RATE_165(5, "165Hz"),
        RATE_170(6, "170Hz"),
        RATE_175(7, "175Hz"),
        RATE_200(8, "200Hz"),
        RATE_220(9, "220Hz"),
        RATE_240(10, "240Hz"),
        RATE_250(11, "250Hz"),
        RATE_280(12, "280Hz"),
        RATE_360(13, "360Hz"),
        RATE_60(14, "60Hz"),
        RATE_75(15, "75Hz");

        private final Integer value;
        private final String name;

        MonitorRefreshRate(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorRefreshRate.values())
                        .map(MonitorRefreshRate::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Monitor refresh rate not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(MonitorRefreshRate.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(MonitorRefreshRate.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(MonitorRefreshRate::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("MonitorRefreshRate not found with value " + value));
            }
            return null;
        }
    }

    public enum HardDiskSeries {
        AORUS(1, "AORUS"),
        FIRECUDA(2, "FireCuda"),
        GOLD(3, "Gold"),
        BLACK(4, "Black"),
        BLUE(5, "Blue"),
        ELEMENT(6, "Element"),
        EVO(7, "EVO"),
        NEO(8, "NEO");

        private final Integer value;
        private final String name;

        HardDiskSeries(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskSeries.values())
                        .map(HardDiskSeries::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Hard disk series not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(HardDiskSeries.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskSeries.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(HardDiskSeries::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("HardDiskSeries not found with value " + value));
            }
            return null;
        }
    }

    public enum HardDiskType {
        HDD(1, "HDD"),
        SSD(2, "SSD"),
        EXTERNAL_SSD(3, "SSD gắn ngoài"),
        EXTERNAL_HDD(4, "HDD gắn ngoài");

        private final Integer value;
        private final String name;

        HardDiskType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskType.values())
                        .map(HardDiskType::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Hard disk type not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(HardDiskType.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskType.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(HardDiskType::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("HardDiskType not found with value " + value));
            }
            return null;
        }
    }

    public enum HardDiskConnectionType {
        TYPE_M2_NVME(1, "M.2 NVMe"),
        TYPE_M2_SATA(2, "M.2 SATA"),
        TYPE_SATA_3(3, "SATA 3"),
        TYPE_USB_3_0(4, "USB 3.0"),
        TYPE_C(5, "USB Type C");

        private final Integer value;
        private final String name;

        HardDiskConnectionType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskConnectionType.values())
                        .map(HardDiskConnectionType::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Hard disk connection type not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(HardDiskConnectionType.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskConnectionType.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(HardDiskConnectionType::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("HardDiskConnectionType not found with value " + value));
            }
            return null;
        }
    }

    public enum HardDiskCapacity {
        CAPACITY_1TB(1, "1TB"),
        CAPACITY_2TB(2, "2TB"),
        CAPACITY_16GB(3, "16GB"),
        CAPACITY_32GB(4, "32GB");

        private final Integer value;
        private final String name;

        HardDiskCapacity(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskCapacity.values())
                        .map(HardDiskCapacity::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Hard disk capacity not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(HardDiskCapacity.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(HardDiskCapacity.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(HardDiskCapacity::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("HardDiskCapacity not found with value " + value));
            }
            return null;
        }
    }

    public enum LaptopSeries {
        NONE(0, "Không có"),
        ALIENWARE(1, "Alienware"),
        G_SERIES(2, "G Series"),
        G_SERIES_GAMING(3, "G Series Gaming"),
        INSPRIRON(4, "Inspiron"),
        LATITUDE(5, "Latitude"),
        VOSTRO(6, "Vostro"),
        XPS(7, "XPS"),
        ZENBOOK(8, "ZenBook");

        private final Integer value;
        private final String name;

        LaptopSeries(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(LaptopSeries.values())
                        .map(LaptopSeries::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Laptop series not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(LaptopSeries.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(LaptopSeries.values())
                        .filter(e -> e.getValue().equals(value))
                        .map(LaptopSeries::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("LaptopSeries not found with value " + value));
            }
            return null;
        }
    }
}
