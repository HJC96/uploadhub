package com.UploadHub.uploadhub.repository;

import com.UploadHub.uploadhub.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void testInsertAndSelectAndUpdateAndDelete() {
        // Insert part
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("tst_title");
        board.setContent("tst_content");
        board.setWriter("jc");

        Board savedBoard = boardRepository.save(board);
        Assertions.assertEquals(1L, savedBoard.getBno());

        // Select part
        Long bno = 1L;
        Optional<Board> result = boardRepository.findById(bno);

        Board selectedBoard = result.orElseThrow();
        Assertions.assertEquals(bno, selectedBoard.getBno());

        // Update part
        selectedBoard.change("update..title 100", "update content..100");

        boardRepository.save(selectedBoard);
        boardRepository.flush();
        log.info("Reg Date: " + selectedBoard.getRegDate());
        log.info("Mod Date: " + selectedBoard.getModDate());

        //Delete part
        boardRepository.deleteById(bno);

    }
}