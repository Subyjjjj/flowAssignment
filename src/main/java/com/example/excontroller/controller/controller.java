package com.example.excontroller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.excontroller.dto.extensionDto;
import com.example.excontroller.entity.extension;
import com.example.excontroller.entity.extensionRepository;
import com.example.excontroller.service.extensionService;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class controller {
    
    private final extensionRepository extensionRepository;
    private final extensionService extensionService;

    @RequestMapping(value = "/extensions", method = RequestMethod.GET)
    public List<extension> selectAll(){
        List<extension> selectAll = extensionRepository.findAll();

        log.info(selectAll.toString());
        return selectAll;
    }

    @RequestMapping(value = "/extension/{seq}", method = RequestMethod.PUT)
    public long updateExtension(@PathVariable long seq, @RequestBody extensionDto exDto){ 
        
        return extensionService.update(seq, exDto);
    }

    @RequestMapping(value = "/extension/{text}", method = RequestMethod.POST)
    public extension saveExtension(@PathVariable String text){
        extensionService.checkTextDuplication(text);        

        final extension ex = extension.builder()
                                .seq(0L)
                                .text(text)
                                .flag("E")
                                .build();

        return extensionRepository.save(ex);
    }

    @RequestMapping(value = "/extension/{seq}", method = RequestMethod.DELETE)
    public int deleteExtension(@PathVariable long seq){        
        return extensionService.delete(seq);
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam MultipartFile[] uploadFile){
        Map<String, Object> response = new HashMap<>();

        List<extension> selectAll = extensionRepository.findAll();
        List<extension> result = selectAll.stream().filter(a -> a.getFlag().equals("Y") || a.getFlag().equals("E"))
                                        .collect(Collectors.toList());

        for (MultipartFile file : uploadFile){
            String extension = "";

            if (!file.isEmpty()){
                extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

                for (extension ex : result){
                    if (ex.getText().equals(extension)){
                        response.put("result", "FAIL");
                        response.put("reason", "금지된 확장자입니다. ::: " + extension);
                        return response;                        
                    }
                }
            }
        }

        response.put("result", "SUCCESS");
        return response;
    }
}
