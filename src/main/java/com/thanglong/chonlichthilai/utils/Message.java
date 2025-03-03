package com.thanglong.chonlichthilai.utils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanglong.chonlichthilai.tkb.TKB;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
	private int code;
    private String content;
    private String extent;
}