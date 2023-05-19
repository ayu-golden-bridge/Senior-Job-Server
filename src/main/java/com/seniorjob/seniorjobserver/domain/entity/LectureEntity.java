package com.seniorjob.seniorjobserver.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "lecture")
public class LectureEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Integer lecture_id;

    @Column(name = "create_uid")
    private Integer create_uid;

    @Column(name = "participant_uid", columnDefinition = "TEXT")
    private String participant_uid;

    @Column(name = "participant_limit")
    private Integer participant_limit;

    @Column(name = "category")
    private String category;

    @Column(name = "bank_name")
    private String bank_name;

    @Column(name = "bank_number", columnDefinition = "MEDIUMTEXT")
    private String bank_number;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "price")
    private Integer price;

    @Column(name = "region")
    private String region;

    @Column(name = "img_key", columnDefinition = "TEXT")
    private String img_key;

    @Column(nullable = false)
    private LocalDateTime update_date;

    @Column(nullable = false)
    private LocalDateTime create_date;

    @Builder
    public LectureEntity(Integer lecture_id, Integer create_uid, String participant_uid, Integer participant_limit, String category,
                         String bank_name, String bank_number, String title, String content, Date start_date, Date end_date,
                         Integer price, String region, String img_key, LocalDateTime update_date,
                         LocalDateTime create_date) {
        this.lecture_id = lecture_id;
        this.create_uid = create_uid;
        this.participant_uid = participant_uid;
        this.participant_limit = participant_limit;
        this.category = category;
        this.bank_name = bank_name;
        this.bank_number = bank_number;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.region = region;
        this.img_key = img_key;
        this.update_date = update_date;
        this.create_date = create_date;
    }
}