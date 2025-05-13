package com.thanglong.chonlichthilai.utils.upload;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadModel {

    private String extraField;

    private MultipartFile[] files;

    //getters and setters
}