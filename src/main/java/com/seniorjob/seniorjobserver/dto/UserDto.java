package com.seniorjob.seniorjobserver.dto;

import com.seniorjob.seniorjobserver.domain.entity.UserEntity;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private String encryptionCode;
    private String name;
    private LocalDate dateOfBirth;
    private char gender;
    private String phoneNumber;
    private char type;
    private String job;
    private String region;
    private String imgKey;
    private String category;
    private LocalDateTime updateDate;
    private LocalDateTime createDate;

    public UserEntity toEntity(){
        UserEntity userEntity = UserEntity.builder()
                .encryptionCode(encryptionCode)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .type(type)
                .job(job)
                .region(region)
                .imgKey(imgKey)
                .category(category)
                .updateDate(updateDate)
                .createDate(createDate)
                .build();
        return userEntity;
    }
}