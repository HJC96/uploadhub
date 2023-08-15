package com.UploadHub.uploadhub.repository.search;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.domain.BoardListReplyCountDTO;
import com.UploadHub.uploadhub.domain.QBoard;
import com.UploadHub.uploadhub.domain.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board; //Q도메인 객체

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    /* QueryDSL을 사용하여 특정 검색 조건에 따른 게시글을 조회하는 기능을 구현한 메서드 */
    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        // QBoard는 QueryDSL에서 생성된 Q 클래스입니다. 게시판 테이블에 대한 쿼리를 작성할 때 사용합니다.
        QBoard board = QBoard.board;

        // JPQLQuery는 JPA 쿼리 객체를 표현합니다.
        JPQLQuery<Board> query = from(board);

        // 검색 조건 (types) 및 검색어 (keyword)가 주어졌는지 검사합니다.
        if((types != null && types.length > 0) && keyword !=null ) {

            // BooleanBuilder는 QueryDSL에서 사용하는 조건문 빌더입니다. 다양한 조건을 조합할 때 유용하게 사용됩니다.
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            // 각 검색 조건에 따라 해당하는 필드에 검색어를 포함하는 조건을 추가합니다.
            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }

            // 만들어진 조건들을 쿼리 객체에 추가합니다.
            query.where(booleanBuilder);
        }

        // bno(게시글 번호)가 0보다 큰 게시글만 조회합니다.
        query.where(board.bno.gt(0L));

        // 페이징 처리를 위해 pageable 객체를 쿼리 객체에 적용합니다.
        this.getQuerydsl().applyPagination(pageable, query);

        // 조건에 해당하는 게시글 리스트를 조회합니다.
        List<Board> list = query.fetch();

        // 조건에 해당하는 게시글의 총 개수를 조회합니다.
        long count = query.fetchCount();

        // 조회된 게시글 리스트와 총 개수, 페이징 정보를 가지는 PageImpl 객체를 반환합니다.
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        // QueryDSL 엔터티 Q 클래스 인스턴스 생성
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        // 기본 쿼리 시작
        JPQLQuery<Board> query = from(board);

        // board와 reply 테이블을 조인하는 구문. board 엔터티 기준으로 left join 수행
        query.leftJoin(reply).on(reply.board.eq(board));

        // 쿼리 결과를 board로 그룹화
        query.groupBy(board);

        // 검색 조건 및 검색어 존재 여부 확인
        if((types != null && types.length > 0) && keyword !=null ) {

            // QueryDSL의 조건문 빌더 인스턴스 생성
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            // 사용자가 입력한 검색 조건에 따라 동적으로 검색어 적용
            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword)); // 제목에 검색어 포함 조건
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword)); // 내용에 검색어 포함 조건
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword)); // 작성자에 검색어 포함 조건
                        break;
                }
            }

            // 만들어진 조건들을 기본 쿼리에 추가
            query.where(booleanBuilder);
        }

        // 게시글 번호(bno)가 0보다 큰 게시글만을 대상으로 쿼리
        query.where(board.bno.gt(0L));

        // 결과를 DTO 형식으로 매핑하며, 댓글의 개수(replyCount)도 함께 선택
        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardListReplyCountDTO.class,
                board.bno,
                board.title,
                board.writer,
                board.regDate,
                reply.count().as("replyCount")
        ));

        // pageable 정보(페이지 번호, 사이즈 등)를 쿼리에 적용하여 페이징 처리
        this.getQuerydsl().applyPagination(pageable, query);

        // 조건에 해당하는 게시글 리스트 조회
        List<BoardListReplyCountDTO> dtolist = dtoQuery.fetch();

        // 조건에 해당하는 게시글의 총 개수 조회
        long count = query.fetchCount();

        // 조회된 리스트, 페이징 정보, 총 개수를 포함한 PageImpl 객체 반환
        return new PageImpl<>(dtolist, pageable, count);
    }


}