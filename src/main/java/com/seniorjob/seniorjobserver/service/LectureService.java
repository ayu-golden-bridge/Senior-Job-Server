package com.seniorjob.seniorjobserver.service;

import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@AllArgsConstructor
@Service
public class LectureService {
    private LectureRepository lectureRepository;

    public List<LectureDto> getLecturelist() {
        List<LectureEntity> lectureEntities = lectureRepository.findAll();
        List<LectureDto> lectureDtoList = new ArrayList<>();

        for ( LectureEntity lectureEntity : lectureEntities) {
            LectureDto lectureDTO = LectureDto.builder()
                    .lecture_id(lectureEntity.getLecture_id())
                    .create_uid(lectureEntity.getCreate_uid())
                    .participant_uid(lectureEntity.getParticipant_uid())
                    .participant_limit(lectureEntity.getParticipant_limit())
                    .category(lectureEntity.getCategory())
                    .bank_name(lectureEntity.getBank_name())
                    .bank_number(lectureEntity.getBank_number())
                    .title(lectureEntity.getTitle())
                    .content(lectureEntity.getContent())
                    .start_date(lectureEntity.getStart_date())
                    .end_date(lectureEntity.getEnd_date())
                    .price(lectureEntity.getPrice())
                    .region(lectureEntity.getRegion())
                    .imgKey(lectureEntity.getImg_key())
                    .create_date(lectureEntity.getCreate_date())
                    .update_date(lectureEntity.getUpdate_date())
                    .build();

            lectureDtoList.add(lectureDTO);
        }

        return lectureDtoList;
    }


    @Transactional
    public LectureDto getPost(Long id) {
        Optional<LectureEntity> lectureEntityWrapper = lectureRepository.findById(id);
        LectureEntity lectureEntity = lectureEntityWrapper.get();

        LectureDto lectureDTO = LectureDto.builder()
                .lecture_id(lectureEntity.getLecture_id())
                .create_uid(lectureEntity.getCreate_uid())
                .participant_uid(lectureEntity.getParticipant_uid())
                .participant_limit(lectureEntity.getParticipant_limit())
                .category(lectureEntity.getCategory())
                .bank_name(lectureEntity.getBank_name())
                .bank_number(lectureEntity.getBank_number())
                .title(lectureEntity.getTitle())
                .content(lectureEntity.getContent())
                .start_date(lectureEntity.getStart_date())
                .end_date(lectureEntity.getEnd_date())
                .price(lectureEntity.getPrice())
                .region(lectureEntity.getRegion())
                .imgKey(lectureEntity.getImg_key())
                .update_date(lectureEntity.getUpdate_date())
                .create_date(lectureEntity.getCreate_date())
                .build();

        return lectureDTO;
    }
    @Transactional
    public Integer savePost(LectureDto lectureDto) {
        return lectureRepository.save(lectureDto.toEntity()).getLecture_id();
    }

    @Transactional
    public void deletePost(Long id) {
        lectureRepository.deleteById(id);
    }
}