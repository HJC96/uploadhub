package com.UploadHub.uploadhub.repository.search;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.domain.BoardListReplyCountDTO;
import com.UploadHub.uploadhub.dto.BoardListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);
}
