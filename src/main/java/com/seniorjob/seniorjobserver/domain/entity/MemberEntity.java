package com.seniorjob.seniorjobserver.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "member") // 가입된 구인자 회원의 정보
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_date", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToMany
    @JoinTable(
            name = "member_lecture",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private List<LectureEntity> lectures;

    @Builder
    public MemberEntity(Long member_id, String id, String name, LocalDateTime createdDate) {
        this.member_id = member_id;
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
    }
}
