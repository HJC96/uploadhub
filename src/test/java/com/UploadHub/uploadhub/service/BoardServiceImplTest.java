package com.UploadHub.uploadhub.service;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.dto.*;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j2 @Transactional
public class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegisterAndModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(1L)
                .title("Test Title...")
                .content("Test content...")
                .writer("user00")
                .build();
        boardService.register(boardDTO);
        Assertions.assertThat(1L).isEqualTo(boardDTO.getBno());

        boardDTO = BoardDTO.builder()
                        .bno(1L)
                        .title("updated ...1")
                        .content("Updated ...1")
                        .build();

        boardService.modify(boardDTO);
        Assertions.assertThat("updated ...1").isEqualTo(boardDTO.getTitle());

    }
//    @Test
//    public void testList(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .type("tcw")
//                .keyword("1")
//                .page(1)
//                .size(10)
//                .build();
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//        log.info(responseDTO);
//    }
//
    @Test
    public void testRegisterWithImages(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Test Title...")
                .content("Test content...")
                .writer("user00")
                .build();
        boardDTO.setFileNames(Arrays.asList(
                UUID.randomUUID()+"_aaa.jpg",
                UUID.randomUUID()+"_bbb.jpg",
                UUID.randomUUID()+"_ccc.jpg"
        ));
        boardService.register(boardDTO);
        Assertions.assertThat(101L).isEqualTo(boardDTO.getBno());

        boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated...101")
                .content("Updated content 101")
                .build();

        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));
        boardService.modify(boardDTO);
        Assertions.assertThat(Arrays.asList(UUID.randomUUID()+"_zzz.jpg")).isEqualTo(boardDTO.getFileNames());
    }
//    @Test
//    public void testReadAll(){
//        Long bno = 101L;
//        BoardDTO boardDTO = boardService.readOne(bno);
//
//        log.info(boardDTO);
//
//        for(String fileName: boardDTO.getFileNames()){
//            log.info(fileName);
//        }
//    }
//
//    @Test
//    public void testModify2(){
//        BoardDTO boardDTO = BoardDTO.builder()
//                .bno(101L)
//                .title("Updated...101")
//                .content("Updated content 101")
//                .build();
//
//        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));
//        boardService.modify(boardDTO);
//    }
//
//    @Test
//    public void testRemoveAll(){
//        Long bno = 1L;
//        boardService.remove(bno);
//    }
//
//    @Test
//    public void testListWithAll(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .build();
//
//        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);
//
//        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();
//
//        dtoList.forEach(boardListAllDTO -> {
//            log.info(boardListAllDTO.getBno()+":"+boardListAllDTO.getTitle());
//
//            if(boardListAllDTO.getBoardImages() != null){
//                for(BoardImageDTO boardImage: boardListAllDTO.getBoardImages()){
//                    log.info(boardImage);
//                }
//            }
//            log.info("-----------------------------------");
//        });
//    }

}