package com.seniorjob.seniorjobserver.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "lecture1")
public class LectureEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@Column(name = "lecture_id")
    private Long lecture_id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "max_participants", nullable = false)
    private Integer max_participants;

    @Column(name = "category")
    private String category;

    @Column(name = "bank_name")
    private String bank_name;

    @Column(name = "account_name")
    private String account_name;

    @Column(name = "account_number")
    private String account_number;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "start_date", columnDefinition = "datetime")
    private LocalDateTime start_date;

    @Column(name = "end_date", columnDefinition = "datetime")
    private LocalDateTime end_date;

    @Column(name = "region")
    private String region;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "created_date", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;




    @Builder
    public LectureEntity(Long lecture_id, String author, Integer max_participants, String category,
                         String bank_name, String account_name, String account_number, Integer price, String title, String content,
                         LocalDateTime start_date, LocalDateTime end_date, String region, String image_url,
                         LocalDateTime createdDate) {
        this.lecture_id = lecture_id;
        this.author = author;
        this.max_participants = max_participants;
        this.category = category;
        this.bank_name = bank_name;
        this.account_name = account_name;
        this.account_number = account_number;
        this.price = price;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.region = region;
        this.image_url = image_url;
        this.createdDate = createdDate;
    }
}