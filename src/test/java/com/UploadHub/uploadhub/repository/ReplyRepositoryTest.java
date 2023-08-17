//package com.UploadHub.uploadhub.repository;
//
//import com.UploadHub.uploadhub.domain.Board;
//import com.UploadHub.uploadhub.domain.Reply;
//import com.UploadHub.uploadhub.dto.ReplyDTO;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Log4j2
//public class ReplyRepositoryTest {
//    @Autowired
//    private ReplyRepository replyRepository;
//
//    @Test
//    public void testInsertAndBoardReplies(){
//        // Insert
//        Long bno = 93L;
//
//        Board board = Board.builder().bno(bno).build();
//        Reply reply = Reply.builder()
//                .board(board)
//                .replyText("댓글....")
//                .replyer("replyer1...")
//                .build();
//        replyRepository.save(reply);
//
//        // Fetch and Verify
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
//        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
//
//        result.getContent().forEach(r -> {
//            log.info(r);
//        });
//    }
//}