package com.UploadHub.uploadhub.repository;

import com.UploadHub.uploadhub.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void testInsert() {
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("tst_title");
        board.setContent("tst_content");
        board.setWriter("jc");

        boardRepository.save(board);
    }

}