package com.UploadHub.uploadhub.domain;

import com.UploadHub.uploadhub.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testInsertAndReadWithImages() {
        // Insert part
        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }

        Board savedBoard = boardRepository.save(board);
        Long savedBoardId = savedBoard.getBno();

        Assertions.assertThat(savedBoardId).isNotNull();

        // Read part
        Optional<Board> result = boardRepository.findByIdWithImages(savedBoardId);
        Assertions.assertThat(result).isPresent();

        Board retrievedBoard = result.orElseThrow();

        Assertions.assertThat(retrievedBoard.getTitle()).isEqualTo(board.getTitle());
        Assertions.assertThat(retrievedBoard.getContent()).isEqualTo(board.getContent());
        Assertions.assertThat(retrievedBoard.getWriter()).isEqualTo(board.getWriter());

        log.info(retrievedBoard);
        log.info("---------------");
        for (BoardImage boardImage : retrievedBoard.getImageSet()) {
            log.info(boardImage);
        }
    }
}
