package com.seniorjob.seniorjobserver.dto;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.domain.entity.LectureEntity.LectureStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LectureDto {

    private Long create_id;
    private String creator;
    private Integer max_participants;
    private Integer current_participants;
    private String category;
    private String bank_name;
    private String account_name;
    private String account_number;
    private Integer price;
    private String title;
    private String content;
    private String cycle;
    private Integer count;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end_date;

    private String region;
    private String image_url;

    private LocalDateTime createdDate;

    private LectureEntity.LectureStatus status;

    public LectureEntity.LectureStatus getStatus() {
        return status;
    }

    public void setStatus(LectureEntity.LectureStatus status) {
        this.status = status;
    }

    public LectureEntity toEntity() {
        LectureEntity lectureEntity = LectureEntity.builder()
                .create_id(create_id)
                .creator(creator)
                .maxParticipants(max_participants)
                .currentParticipants(current_participants)
                .category(category)
                .bank_name(bank_name)
                .account_name(account_name)
                .account_number(account_number)
                .price(price)
                .title(title)
                .content(content)
                .cycle(cycle)
                .count(count)
                .start_date(start_date)
                .end_date(end_date)
                .region(region)
                .image_url(image_url)
                .createdDate(createdDate)
                .build();
        return lectureEntity;
    }

    @Builder
    public LectureDto(Long create_id, String creator, Integer max_participants, Integer current_participants, String category,
                      String bank_name, String account_name, String account_number, Integer price, String title, String content,
                      String cycle, Integer count, LocalDateTime start_date, LocalDateTime end_date, String region, String image_url,
                      LocalDateTime createdDate) {
        this.create_id = create_id;
        this.creator = creator;
        this.max_participants = max_participants;
        this.current_participants = current_participants;
        this.category = category;
        this.bank_name = bank_name;
        this.account_name = account_name;
        this.account_number = account_number;
        this.price = price;
        this.title = title;
        this.content = content;
        this.cycle = cycle;
        this.count = count;
        this.start_date = start_date;
        this.end_date = end_date;
        this.region = region;
        this.image_url = image_url;
        this.createdDate = createdDate;
    }

    private LectureDto convertToDto(LectureEntity lectureEntity) {
        return LectureDto.builder()
                .create_id(lectureEntity.getCreate_id())
                .creator(lectureEntity.getCreator())
                .max_participants(lectureEntity.getMaxParticipants())
                .current_participants(lectureEntity.getCurrentParticipants())
                .category(lectureEntity.getCategory())
                .bank_name(lectureEntity.getBank_name())
                .account_name(lectureEntity.getAccount_name())
                .account_number(lectureEntity.getAccount_number())
                .price(lectureEntity.getPrice())
                .title(lectureEntity.getTitle())
                .content(lectureEntity.getContent())
                .cycle(lectureEntity.getCycle())
                .count(lectureEntity.getCount())
                .start_date(lectureEntity.getStart_date())
                .end_date(lectureEntity.getEnd_date())
                .region(lectureEntity.getRegion())
                .image_url(lectureEntity.getImage_url())
                .createdDate(lectureEntity.getCreatedDate())
                .build();
    }
}
