package com.seniorjob.seniorjobserver.dto;

import com.seniorjob.seniorjobserver.domain.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

    private Long member_id;

    private String id;

    private String name;

    private LocalDateTime createdDate;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .member_id(member_id)
                .id(id)
                .name(name)
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Builder
    public MemberDto(Long member_id, String id, String name, LocalDateTime createdDate){
        this.member_id = member_id;
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
    }
}
