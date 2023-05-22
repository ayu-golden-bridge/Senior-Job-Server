package com.seniorjob.seniorjobserver.dto;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LectureDto {

    private Long lecture_id;
    private String author;
    private Integer max_participants;
    private String category;
    private String bank_name;
    private String account_name;
    private String account_number;
    private Integer price;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end_date;
    private String region;
    private String image_url;

    private LocalDateTime createdDate;

    public LectureEntity toEntity() {
        LectureEntity lectureEntity = LectureEntity.builder()
                .lecture_id(lecture_id)
                .author(author)
                .maxParticipants(max_participants)
                .category(category)
                .bank_name(bank_name)
                .account_name(account_name)
                .account_number(account_number)
                .price(price)
                .title(title)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .region(region)
                .image_url(image_url)
                .createdDate(createdDate)
                .build();
        return lectureEntity;
    }

    @Builder
    public LectureDto(Long lecture_id, String author, Integer max_participants, String category,
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