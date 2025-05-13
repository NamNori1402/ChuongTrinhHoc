package com.thanglong.chonlichthilai.thongbaonghihoc;


import java.util.Arrays;

public enum ReportTypeEnum {
    PDF("pdf", ".pdf"),
    EXCEL("excel", ".xlsx"),
    DOC("word", ".docx");

    private final String code;
    private final String extension;

    ReportTypeEnum(String code, String extension) {
        this.code = code;
        this.extension = extension;
    }

    public String getCode() {
        return code;
    }

    public String getExtension() {
        return extension;
    }

    public static ReportTypeEnum getReportTypeByCode(String code) {
        return Arrays.stream(ReportTypeEnum.values())
                .filter(type -> type.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid report type: " + code));
    }
}
