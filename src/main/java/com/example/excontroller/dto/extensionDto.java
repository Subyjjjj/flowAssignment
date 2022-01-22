package com.example.excontroller.dto;

import com.example.excontroller.entity.extension;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class extensionDto {
    
    private String text;
    private String flag;

    @Builder
    public extensionDto(String text, String flag){
        this.text = text;
        this.flag = flag;
    }

    public extension toEntity() {
        return extension.builder()
                    .text(text)
                    .flag(flag)
                    .build();
    }  
}
