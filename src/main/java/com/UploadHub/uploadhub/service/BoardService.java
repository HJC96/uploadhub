package com.UploadHub.uploadhub.service;

import com.UploadHub.uploadhub.dto.BoardDTO;
import com.UploadHub.uploadhub.dto.PageRequestDTO;
import com.UploadHub.uploadhub.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);
    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    
}
