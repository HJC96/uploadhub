package com.UploadHub.uploadhub.repository.search;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

@SpringBootTest
@Log4j2
public class BoardSearchImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    public void testSearchAll2(){
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("tst_title");
        board.setContent("tst_content");
        board.setWriter("jc");

        Board savedBoard = boardRepository.save(board);
        Assertions.assertThat(savedBoard.getBno()).isEqualTo(1L);

        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        // Assertions for Page attributes
//        Assertions.assertThat(result.getTotalPages()).isGreaterThan(0); // 예상되는 페이지 수에 따라 수정
//        Assertions.assertThat(result.getSize()).isEqualTo(10);
//        Assertions.assertThat(result.getNumber()).isEqualTo(0);

        // Assertions for prev/next
        Assertions.assertThat(result.hasPrevious()).isFalse(); // 첫 페이지이므로 이전 페이지는 없음
        // Assertions.assertThat(result.hasNext()).isTrue(); // 다음 페이지의 유무는 데이터에 따라 다를 수 있습니다. 필요하면 추가하세요.

        // Assertions for content
        Assertions.assertThat(result.getContent()).isEmpty();
//        result.getContent().forEach(board2 -> {
//            Assertions.assertThat(board2).isNotNull();
//            log.info(board2);
//        });
    }
}
