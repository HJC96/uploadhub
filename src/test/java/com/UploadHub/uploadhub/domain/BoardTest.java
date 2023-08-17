package com.UploadHub.uploadhub.domain;

import com.UploadHub.uploadhub.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트 순서 지정
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    private static Long savedBoardId;

    @Test
    @Order(1) // 순서 1로 지정
    public void testInsertWithImages() {
        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }

        Board savedBoard = boardRepository.save(board);
        savedBoardId = savedBoard.getBno(); // 저장된 게시물 ID를 저장
    }

    @Test
    @Order(2) // 순서 2로 지정
    public void testReadWithImages() {
        if (savedBoardId == null) {
            Assertions.fail("게시물 ID가 저장되지 않았습니다."); // ID가 null이면 테스트 실패
        }

        Optional<Board> result = boardRepository.findByIdWithImages(savedBoardId);

        Board board = result.orElseThrow();

        log.info(board);
        log.info("---------------");
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
    }
}
