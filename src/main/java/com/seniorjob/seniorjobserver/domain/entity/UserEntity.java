package com.seniorjob.seniorjobserver.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user") // 가입된 구인자, 강사 회원정보
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(name = "encryption_code", nullable = false)
    private String encryptionCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private char gender;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "type", nullable = false)
    private char type;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "region")
    private String region;

    @Column(name = "img_key")
    private String imgKey;

    @Column(name = "category")
    private String category;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Builder
    public UserEntity(String encryptionCode, String name, LocalDate dateOfBirth, char gender, String phoneNumber,
                      char type, String job, String region, String imgKey, String category,
                      LocalDateTime updateDate, LocalDateTime createDate) {
        this.encryptionCode = encryptionCode;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.job = job;
        this.region = region;
        this.imgKey = imgKey;
        this.category = category;
        this.updateDate = updateDate;
        this.createDate = createDate;
    }
}
