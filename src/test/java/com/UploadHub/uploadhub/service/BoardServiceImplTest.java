package com.UploadHub.uploadhub.service;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.dto.BoardDTO;
import com.UploadHub.uploadhub.dto.PageRequestDTO;
import com.UploadHub.uploadhub.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j2
public class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test Title...")
                .content("Test content...")
                .writer("user00")
                .build();
        Long bno = boardService.register(boardDTO);
    }
    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                        .bno(91L)
                        .title("updated ...91")
                        .content("Updated ...")
                        .build();

        boardService.modify(boardDTO);
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }
}