package com.seniorjob.seniorjobserver.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class LectureEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String writer;

    @Builder
    public LectureEntity(Long id, Date createDate, Date modifiedDate, String content, String title, String writer) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.writer = writer;


    }
}