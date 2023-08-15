package com.UploadHub.uploadhub.service;

import com.UploadHub.uploadhub.domain.BoardListReplyCountDTO;
import com.UploadHub.uploadhub.dto.BoardDTO;
import com.UploadHub.uploadhub.dto.PageRequestDTO;
import com.UploadHub.uploadhub.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);
    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    //댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    
}
