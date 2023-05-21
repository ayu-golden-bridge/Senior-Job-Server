package com.seniorjob.seniorjobserver.service;


import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public List<LectureDto> getAllLectures() {
        List<LectureEntity> lectureEntities = lectureRepository.findAll();
        return lectureEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LectureDto createLecture(LectureDto lectureDto) {
        LectureEntity lectureEntity = lectureDto.toEntity();
        LectureEntity savedLecture = lectureRepository.save(lectureEntity);
        return convertToDto(savedLecture);
    }

    public LectureDto updateLecture(Long lecture_id, LectureDto lectureDto) {
        LectureEntity existingLecture = lectureRepository.findById(lecture_id)
                .orElseThrow(() -> new RuntimeException("강좌아이디 찾지못함 lecture_id: " + lecture_id));

        // Update the existing LectureEntity with the new values from LectureDto
        existingLecture.setAuthor(lectureDto.getAuthor());
        existingLecture.setMax_participants(lectureDto.getMax_participants());
        existingLecture.setCategory(lectureDto.getCategory());
        existingLecture.setBank_name(lectureDto.getBank_name());
        existingLecture.setAccount_name(lectureDto.getAccount_name());
        existingLecture.setAccount_number(lectureDto.getAccount_number());
        existingLecture.setPrice(lectureDto.getPrice());
        existingLecture.setTitle(lectureDto.getTitle());
        existingLecture.setContent(lectureDto.getContent());
        existingLecture.setStart_date(lectureDto.getStart_date());
        existingLecture.setEnd_date(lectureDto.getEnd_date());
        existingLecture.setRegion(lectureDto.getRegion());
        existingLecture.setImage_url(lectureDto.getImage_url());

        LectureEntity updatedLecture = lectureRepository.save(existingLecture);
        return convertToDto(updatedLecture);
    }

    public void deleteLecture(Long lectureId) {
        lectureRepository.deleteById(lectureId);
    }

    public LectureDto getDetailLectureById(Long lecture_id) {
        LectureEntity lectureEntity = lectureRepository.findById(lecture_id)
                .orElseThrow(() -> new RuntimeException("강좌아이디 찾지못함 lecture_id: " + lecture_id));
        return convertToDto(lectureEntity);
    }

    // 강좌검색
    public List<LectureDto> searchLecturesByTitle(String title) {
        List<LectureEntity> lectureEntities = lectureRepository.findByTitleContaining(title);
        return lectureEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

   // 강좌정렬

    // 가격(최저, 최고) 강좌 정렬
    public List<LectureDto> getLecturesByPriceRange(boolean isLowToHigh) {
        Sort.Direction direction = isLowToHigh ? Sort.Direction.ASC : Sort.Direction.DESC;
        List<LectureEntity> lectureEntities = lectureRepository.findAll(Sort.by(direction, "price"));
        return lectureEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LectureDto convertToDto(LectureEntity lectureEntity) {
        return LectureDto.builder()
                .lecture_id(lectureEntity.getLecture_id())
                .author(lectureEntity.getAuthor())
                .max_participants(lectureEntity.getMax_participants())
                .category(lectureEntity.getCategory())
                .bank_name(lectureEntity.getBank_name())
                .account_name(lectureEntity.getAccount_name())
                .account_number(lectureEntity.getAccount_number())
                .price(lectureEntity.getPrice())
                .title(lectureEntity.getTitle())
                .content(lectureEntity.getContent())
                .start_date(lectureEntity.getStart_date())
                .end_date(lectureEntity.getEnd_date())
                .region(lectureEntity.getRegion())
                .image_url(lectureEntity.getImage_url())
                .created_date(lectureEntity.getCreated_date())
                .build();
    }


//    public List<LectureDto> getLecturelist() {
//        List<LectureEntity> lectureEntities = lectureRepository.findAll();
//        List<LectureDto> lectureDtoList = new ArrayList<>();
//
//        for ( LectureEntity lectureEntity : lectureEntities) {
//            LectureDto lectureDTO = LectureDto.builder()
//                    .lecture_id(lectureEntity.getLecture_id())
//                    .author(lectureEntity.getAuthor())
//                    .max_participants(lectureEntity.getMax_participants())
//                    .category(lectureEntity.getCategory())
//                    .bank_name(lectureEntity.getBank_name())
//                    .account_name(lectureEntity.getAccount_name())
//                    .account_number(lectureEntity.getAccount_number())
//                    .price(lectureEntity.getPrice())
//                    .title(lectureEntity.getTitle())
//                    .content(lectureEntity.getContent())
//                    .start_date(lectureEntity.getStart_date())
//                    .end_date(lectureEntity.getEnd_date())
//                    .region(lectureEntity.getRegion())
//                    .image_url(lectureEntity.getImage_url())
//                    .created_date(lectureEntity.getCreated_date())
//                    .build();
//
//            lectureDtoList.add(lectureDTO);
//        }
//
//        return lectureDtoList;
//    }


    @Transactional
    public LectureDto getPost(Long id) {
        Optional<LectureEntity> lectureEntityWrapper = lectureRepository.findById(id);
        LectureEntity lectureEntity = lectureEntityWrapper.get();

        LectureDto lectureDTO = LectureDto.builder()
                .lecture_id(lectureEntity.getLecture_id())
                .author(lectureEntity.getAuthor())
                .max_participants(lectureEntity.getMax_participants())
                .category(lectureEntity.getCategory())
                .bank_name(lectureEntity.getBank_name())
                .account_name(lectureEntity.getAccount_name())
                .account_number(lectureEntity.getAccount_number())
                .price(lectureEntity.getPrice())
                .title(lectureEntity.getTitle())
                .content(lectureEntity.getContent())
                .start_date(lectureEntity.getStart_date())
                .end_date(lectureEntity.getEnd_date())
                .region(lectureEntity.getRegion())
                .image_url(lectureEntity.getImage_url())
                .created_date(lectureEntity.getCreated_date())
                .build();

        return lectureDTO;
    }
    @Transactional
    public Long savePost(LectureDto lectureDto) {
        return lectureRepository.save(lectureDto.toEntity()).getLecture_id();
    }

    @Transactional
    public void deletePost(Long id) {
        lectureRepository.deleteById(id);
    }
}