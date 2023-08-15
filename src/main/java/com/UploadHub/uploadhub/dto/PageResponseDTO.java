package com.UploadHub.uploadhub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
public class PageResponseDTO<E> {

    // 현재 페이지 번호
    private int page;

    // 한 페이지에 표시할 데이터의 개수
    private int size;

    // 전체 데이터의 개수
    private int total;

    // 페이징 네비게이션에서 표시될 시작 페이지 번호
    private int start;

    // 페이징 네비게이션에서 표시될 끝 페이지 번호
    private int end;

    // 이전 페이지 네비게이션 버튼의 존재 여부
    private boolean prev;

    // 다음 페이지 네비게이션 버튼의 존재 여부
    private boolean next;

    // 현재 페이지에 표시될 데이터 리스트
    private List<E> dtoList;

    // 생성자의 메서드 이름을 'withAll'로 지정하는 Builder 어노테이션
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        // 전체 데이터 개수가 0 이하면 초기화를 중단
        if(total <= 0) {
            return;
        }

        // 현재 페이지와 페이지 크기 설정
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        // 전체 데이터 개수와 DTO 리스트 설정
        this.total = total;
        this.dtoList = dtoList;

        // 네비게이션의 끝 페이지 계산
        this.end = (int)(Math.ceil(this.page/10.0))*10;

        // 네비게이션의 시작 페이지 계산
        this.start = this.end - 9;

        // 마지막 페이지 계산
        int last = (int)(Math.ceil((total/(double)size)));

        // 마지막 페이지와 네비게이션의 끝 페이지 중 작은 값으로 끝 페이지 설정
        this.end = end > last ? last: end;

        // 이전 페이지 버튼의 존재 여부 결정
        this.prev = this.start > 1;

        // 다음 페이지 버튼의 존재 여부 결정
        this.next = total > this.end * this.size;
    }
}

