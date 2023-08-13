package com.UploadHub.uploadhub.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  persistence provider must assign primary keys for the entity using a database identity column.
    private Long bno;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;

    /*일반적으로 엔티티 객체는 가능하면 immutable하게 설계하는 것이 좋지만, 강제 사항은 아니다.*/
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
