package com.thanglong.chonlichthilai.email;


//Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

//Class
public class EmailDetails {
 private String recipient;
 private String msgBody;
 private String subject;
 private String attachment;
}