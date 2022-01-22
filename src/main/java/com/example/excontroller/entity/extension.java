package com.example.excontroller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class extension {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(length = 20, nullable = false)
    private String text;

    @Column(length = 1, nullable = false)
    private String flag;

    @Builder
    public extension(Long seq, String text, String flag) {
        this.seq = seq;
        this.text = text;
        this.flag = flag;
    }

    public void update(String text, String flag){
        this.text = text;
        this.flag = flag;
    }
}
