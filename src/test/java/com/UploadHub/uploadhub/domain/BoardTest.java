package com.UploadHub.uploadhub.domain;

import com.UploadHub.uploadhub.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class BoardTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testInsertWithImages(){
        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for(int i=0;i<3;i++){
            board.addImage(UUID.randomUUID().toString(),"file"+i+".jpg");
        }
        boardRepository.save(board);
    }

    @Test
    public void testReadWithImages(){
        Optional<Board> result = boardRepository.findByIdWithImages(102L);

        Board board = result.orElseThrow();

        log.info(board);
        log.info("---------------");
        for(BoardImage boardImage:board.getImageSet()){
            log.info(boardImage);
        }
    }

}