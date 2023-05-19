package com.seniorjob.seniorjobserver.dto;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import lombok.*;
import java.util.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LectureDto {

    private Integer lecture_id;
    private Integer create_uid;
    private String participant_uid;
    private Integer participant_limit;
    private String category;
    private String bank_name;
    private String bank_number;
    private String title;
    private String content;
    private Date start_date;
    private Date end_date;
    private Integer price;
    private String region;
    private String img_key;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public LectureEntity toEntity() {
        LectureEntity lectureEntity = LectureEntity.builder()
                .lecture_id(lecture_id)
                .create_uid(create_uid)
                .participant_uid(participant_uid)
                .participant_limit(participant_limit)
                .category(category)
                .bank_name(bank_name)
                .bank_number(bank_number)
                .title(title)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .price(price)
                .region(region)
                .img_key(img_key)
                .create_date(create_date)
                .update_date(update_date)
                .build();
        return lectureEntity;
    }

    @Builder
    public LectureDto(Integer lecture_id, Integer create_uid, String participant_uid, Integer participant_limit, String category,
                      String bank_name, String bank_number, String title, String content, Date start_date, Date end_date,
                      Integer price, String region, String imgKey, LocalDateTime create_date, LocalDateTime update_date) {
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
        this.create_date = create_date;
        this.update_date = update_date;
    }
}