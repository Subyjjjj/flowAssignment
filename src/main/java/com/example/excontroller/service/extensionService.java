package com.example.excontroller.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.excontroller.dto.extensionDto;
import com.example.excontroller.entity.extension;
import com.example.excontroller.entity.extensionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class extensionService {
    private final extensionRepository extensionRepository;
    
    @Transactional
    public Long update(long seq, extensionDto dto){
        extension ex = extensionRepository.findById(seq).
                            orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다." + seq));

        ex.update(dto.getText(), dto.getFlag());
        return seq;
    }

    @Transactional
    public void checkTextDuplication(String text){
        boolean textDuplicate = extensionRepository.existsByText(text);
        if (textDuplicate) {
            throw new IllegalStateException("이미 존재하는 확장자입니다.");
        }
    }

    @Transactional
    public int delete(long seq){
        Optional<extension> ex = extensionRepository.findById(seq);

        if (ex.isPresent()){
            extensionRepository.delete(ex.get());
            return 1;
        } else {
            return 0;
        }

    }
}
