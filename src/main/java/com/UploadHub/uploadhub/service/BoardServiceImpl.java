package com.UploadHub.uploadhub.service;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.dto.BoardDTO;
import com.UploadHub.uploadhub.dto.PageRequestDTO;
import com.UploadHub.uploadhub.dto.PageResponseDTO;
import com.UploadHub.uploadhub.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO,Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Board board = boardRepository.findById(bno).get();
        return modelMapper.map(board,BoardDTO.class);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardDTO.getBno()).get();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }
    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
        List<BoardDTO> dtoList = result.getContent().stream().map(board->modelMapper.map(board,BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
