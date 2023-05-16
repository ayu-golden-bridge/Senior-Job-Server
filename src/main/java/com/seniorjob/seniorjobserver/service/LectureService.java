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
                    .id(lectureEntity.getId())
                    .title(lectureEntity.getTitle())
                    .content(lectureEntity.getContent())
                    .writer(lectureEntity.getWriter())
                    .createdDate(lectureEntity.getCreatedDate())
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
                .id(lectureEntity.getId())
                .title(lectureEntity.getTitle())
                .content(lectureEntity.getContent())
                .writer(lectureEntity.getWriter())
                .createdDate(lectureEntity.getCreatedDate())
                .build();

        return lectureDTO;
    }
    @Transactional
    public Long savePost(LectureDto lectureDto) {
        return lectureRepository.save(lectureDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        lectureRepository.deleteById(id);
    }
}