package com.UploadHub.uploadhub.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  persistence provider must assign primary keys for the entity using a database identity column.
    private Long bno;
    private String title;
    private String content;
    private String writer;
}
