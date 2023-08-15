package com.UploadHub.uploadhub.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage>{ // 정렬을 위해 Comparable 인터페이스 사용
    @Id
    private String uuid;
    private String fileName;
    private int ord;

    @ManyToOne
    private Board board;

    @Override
    public int compareTo(BoardImage other){
        return this.ord - other.ord;
    }

    // Board 객체를 나중에 지정할 수 있게 하는 이유는 Board 엔티티 삭제 시에 BoardImage 객체의 참조도 변경하기 위해서
    public void changeBoard(Board board){
        this.board = board;
    }

}
