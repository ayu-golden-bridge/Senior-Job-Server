package com.seniorjob.seniorjobserver.service;

import com.seniorjob.seniorjobserver.domain.entity.LectureApplyEntity;
import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.domain.entity.UserEntity;
import com.seniorjob.seniorjobserver.repository.LectureApplyRepository;
import com.seniorjob.seniorjobserver.repository.LectureRepository;
import com.seniorjob.seniorjobserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LectureApplyService {

    private final LectureRepository lectureRepository;
    private final LectureApplyRepository lectureApplyRepository;
    private final UserRepository userRepository;

    @Autowired
    public LectureApplyService(UserRepository userRepository, LectureRepository lectureRepository, LectureApplyRepository lectureApplyRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
        this.lectureApplyRepository = lectureApplyRepository;
    }

    public void applyForLecture(Long userId, Long lectureId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. id: " + userId));

        LectureEntity lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("강좌를 찾을 수 없습니다. id: " + lectureId));

        // 이미 강좌에 참여한 경우 예외 처리
        if (lectureApplyRepository.existsByUserAndLecture(user, lecture)) {
            throw new RuntimeException(lectureId + " 이미 참여하신 강좌입니다.");
        }

        // 강좌 참여 생성
        LectureApplyEntity lectureApply = LectureApplyEntity.builder()
                .lecture(lecture)
                .user(user)
                .createdDate(LocalDateTime.now())
                .build();
        lecture.increaseCurrentParticipants();
        lectureApplyRepository.save(lectureApply);
    }

    public String cancelLectureApply(Long userId, Long lectureId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        LectureEntity lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("강좌를 찾을 수 없습니다. id: " + lectureId));

        LectureApplyEntity lectureApply = (LectureApplyEntity) lectureApplyRepository.findByUserAndLecture(user, lecture)
                .orElseThrow(() -> new RuntimeException("신청된 강좌를 찾을 수 없습니다. userId: " + userId + ", lectureId: " + lectureId));

        lecture.decreaseCurrentParticipants();
        lectureApplyRepository.delete(lectureApply);

        return "강좌 신청이 취소되었습니다.";
    }

}