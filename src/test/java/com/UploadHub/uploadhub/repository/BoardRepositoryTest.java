package com.UploadHub.uploadhub.repository;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.domain.BoardListReplyCountDTO;
import com.UploadHub.uploadhub.dto.BoardListAllDTO;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Test
    @Transactional
    public void testInsertAndSelectAndUpdateAndDelete() {
        // Insert part
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("tst_title");
        board.setContent("tst_content");
        board.setWriter("jc");

        Board savedBoard = boardRepository.save(board);
        Assertions.assertThat(savedBoard.getBno()).isEqualTo(1L);

        // Select part
        Long bno = 1L;
        Optional<Board> result = boardRepository.findById(bno);

        Board selectedBoard = result.orElseThrow();
        Assertions.assertThat(selectedBoard.getBno()).isEqualTo(bno);

        // Update part
        selectedBoard.change("update..title 100", "update content..100");

        boardRepository.save(selectedBoard);
        boardRepository.flush();

        Assertions.assertThat(selectedBoard.getTitle()).isEqualTo("update..title 100");
        Assertions.assertThat(selectedBoard.getContent()).isEqualTo("update content..100");

        log.info("Reg Date: " + selectedBoard.getRegDate());
        log.info("Mod Date: " + selectedBoard.getModDate());

        // Delete part
        boardRepository.deleteById(bno);

        // Optionally, ensure that it's deleted
        Assertions.assertThat(boardRepository.findById(bno)).isNotPresent();
    }







//
//    @Test
//    public void testPaging(){
//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//        Page<Board> result = boardRepository.findAll(pageable);
//    }
//
//    @Test
//    public void testSerach1(){
//        Pageable pageable = PageRequest.of(1,10,Sort.by("bno").descending());
//        boardRepository.search1(pageable);
//    }
//
//    @Test
//    public void testDataInsert(){
//        IntStream.range(1,10).forEach(i->{
//            Board board = Board.builder()
//                    .title("title..." + i)
//                    .content("content..."+i)
//                    .writer("user" + (i%10))
//                    .build();
//            Board result = boardRepository.save(board);
//            log.info("BNO: " + result.getBno());
//        });
//    }
//
//    @Test
//    public void testSearchReplyCount(){
//        String[] types = {"t", "c", "w"};
//        String keyword = "1";
//        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
//        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types,keyword,pageable);
//
//        //total pages
//        log.info(result.getTotalPages());
//        //pag size
//        log.info(result.getSize());
//        //pageNumber
//        log.info(result.getNumber());
//        //prev next
//        log.info(result.hasPrevious() + ": " + result.hasNext());
//        result.getContent().forEach(board->log.info(board));
//    }
//
//    @Transactional
//    @Commit
//    @Test
//    public void testModifyImages(){
//        Optional<Board> result = boardRepository.findByIdWithImages(1L);
//        Board board = result.orElseThrow();
//
//        board.clearImages();
//
//        for(int i=0;i<2;i++){
//            board.addImage(UUID.randomUUID().toString(), "updatefile"+i+".jpg");
//        }
//        boardRepository.save(board);
//    }
//
//    @Test
//    @Transactional
//    @Commit
//    public void testRemoveAll(){
//        Long bno = 103L;
//        replyRepository.deleteByBoard_Bno(bno); // 댓글 먼저 삭제 이후
//        boardRepository.deleteById(bno); // 엔티티 삭제
//
//    }
//
//    @Test
//    public void testInsertAll(){
//        for(int i=1;i<=10;i++){
//            Board board = Board.builder()
//                    .title("Title..."+i)
//                    .content("Content..."+i)
//                    .writer("writer..."+i)
//                    .build();
//            for(int j=0;j<3;j++){
//                if(i%5==0){
//                    continue;
//                }
//                board.addImage(UUID.randomUUID().toString(),i+"file"+j+".jpg");
//            }
//            boardRepository.save(board);
//        }
//    }
//
//    @Test
//    @Transactional
//    public void testSearchImageReplyCount(){
//        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
////        boardRepository.searchWithAll(null, null, pageable);
//        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);
//        log.info("-----------------------------");
//        log.info(result.getTotalElements());
//
//        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
//    }
}