package com.UploadHub.uploadhub.service;

import com.UploadHub.uploadhub.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j2
public class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;
    @Test
    public void testRegister(){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("ReplyDTO Text")
                .replyer("replyer")
                .build();
        log.info(replyService.register(replyDTO));
    }
}