package com.UploadHub.uploadhub.repository;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.domain.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

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

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
    }

    @Test
    public void testSerach1(){
        Pageable pageable = PageRequest.of(1,10,Sort.by("bno").descending());
        boardRepository.search1(pageable);
    }

    @Test
    public void testDataInsert(){
        IntStream.range(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..."+i)
                    .writer("user" + (i%10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testSearchReplyCount(){
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types,keyword,pageable);

        //total pages
        log.info(result.getTotalPages());
        //pag size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());
        result.getContent().forEach(board->log.info(board));
    }
}