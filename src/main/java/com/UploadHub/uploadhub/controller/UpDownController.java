package com.UploadHub.uploadhub.controller;

import com.UploadHub.uploadhub.dto.upload.UploadFileDTO;
import com.UploadHub.uploadhub.dto.upload.UploadResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {
    @Value("${com.UploadHub.upload.path}")  // 파일 업로드 경로 가져오기
    private String uploadPath;

    @ApiOperation(value = "Upload POST", notes = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO){
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null){  // 파일이 존재할 경우
            final List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile -> {  // 각 파일에 대해
                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);
                String uuid = UUID.randomUUID().toString();  // 고유한 ID 생성
                Path savePath = Paths.get(uploadPath,uuid+"_"+originalName);  // 저장 경로 설정
                boolean image = false;
                try{
                    multipartFile.transferTo(savePath);  // 파일 저장
                    // 이미지 파일의 종류라면
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image = true;
                        File thumbFile = new File(uploadPath,"s_"+uuid+"_"+originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(),thumbFile,200,200);  // 썸네일 생성
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                list.add(UploadResultDTO.builder().uuid(uuid).filename(originalName).img(image).build());
            });
            return list;
        }
        return null;  // 파일이 없으면 null 반환
    }

    @ApiOperation(value = "view 파일", notes = "GET 방식으로 첨부파일 조회")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);  // 파일 리소스 생성

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));  // 콘텐츠 타입 설정
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();  // 에러 시 500 응답
        }
        return ResponseEntity.ok().headers(headers).body(resource);  // 파일 리소스 반환
    }

    @ApiOperation(value = "remove 파일", notes = "DELETE 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;
        try{
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            // 섬네일이 존재한다면, 섬네일도 삭제
            if(contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath+File.separator +"s_"+fileName);
                thumbnailFile.delete();
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        resultMap.put("result",removed);
        return resultMap;
    }
}
