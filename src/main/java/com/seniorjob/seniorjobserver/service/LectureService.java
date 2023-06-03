package com.seniorjob.seniorjobserver.service;


import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.repository.LectureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public LectureDto updateLecture(Long create_id, LectureDto lectureDto) {
        LectureEntity existingLecture = lectureRepository.findById(create_id)
                .orElseThrow(() -> new RuntimeException("강좌아이디 찾지못함 create_id: " + create_id));

        existingLecture.setCreator(lectureDto.getCreator());
        existingLecture.setMaxParticipants(lectureDto.getMax_participants());
        existingLecture.setCategory(lectureDto.getCategory());
        existingLecture.setBank_name(lectureDto.getBank_name());
        existingLecture.setAccount_name(lectureDto.getAccount_name());
        existingLecture.setAccount_number(lectureDto.getAccount_number());
        existingLecture.setPrice(lectureDto.getPrice());
        existingLecture.setTitle(lectureDto.getTitle());
        existingLecture.setContent(lectureDto.getContent());
        existingLecture.setCycle(lectureDto.getCycle());
        existingLecture.setCount(lectureDto.getCount());
        existingLecture.setStart_date(lectureDto.getStart_date());
        existingLecture.setEnd_date(lectureDto.getEnd_date());
        existingLecture.setRegion(lectureDto.getRegion());
        existingLecture.setImage_url(lectureDto.getImage_url());

        LectureEntity updatedLecture = lectureRepository.save(existingLecture);
        return convertToDto(updatedLecture);
    }

    public void deleteLecture(Long create_id) {
        lectureRepository.deleteById(create_id);
    }

    public LectureDto getDetailLectureById(Long create_id) {
        LectureEntity lectureEntity = lectureRepository.findById(create_id)
                .orElseThrow(() -> new RuntimeException("강좌아이디 찾지못함 create_id: " + create_id));
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
    // 최신순으로 강좌 정렬 최신 = true 오래된 = false
    public List<LectureDto> getAllLecturesSortedByCreatedDate(boolean descending) {
        List<LectureEntity> lectureEntities;
        if (descending) {
            lectureEntities = lectureRepository.findAllByOrderByCreatedDateDesc();
        } else {
            lectureEntities = lectureRepository.findAllByOrderByCreatedDateAsc();
        }
        return lectureEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 인기순 : max_participant가많은순 -> 강좌 참여하기를 만들때 실제참여자가 많은순으로 변경할것임
    public List<LectureDto> getAllLecturesSortByPopularity(boolean descending){
        List<LectureEntity> lectureEntities;
        if(descending){
            lectureEntities = lectureRepository.findAllByOrderByMaxParticipantsDesc();
        }else{
            lectureEntities = lectureRepository.findAllByOrderByMaxParticipantsAsc();
        }
        return lectureEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 가격순 : prices(낮은순 높은순)
    public List<LectureDto> getAllLecturesSortByPrice(boolean descending){
        List<LectureEntity> lectureEntities;
        if(descending){
            lectureEntities = lectureRepository.findAllByOrderByPriceDesc();
        }else{
            lectureEntities = lectureRepository.findAllByOrderByPriceAsc();
        }
        return lectureEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LectureDto convertToDto(LectureEntity lectureEntity) {
        return LectureDto.builder()
                .create_id(lectureEntity.getCreate_id())
                .creator(lectureEntity.getCreator())
                .max_participants(lectureEntity.getMaxParticipants())
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

    @Transactional
    public LectureDto getPost(Long id) {
        Optional<LectureEntity> lectureEntityWrapper = lectureRepository.findById(id);
        LectureEntity lectureEntity = lectureEntityWrapper.get();

        LectureDto lectureDTO = LectureDto.builder()
                .create_id(lectureEntity.getCreate_id())
                .creator(lectureEntity.getCreator())
                .max_participants(lectureEntity.getMaxParticipants())
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

        return lectureDTO;
    }
    @Transactional
    public Long savePost(LectureDto lectureDto) {
        return lectureRepository.save(lectureDto.toEntity()).getCreate_id();
    }
}
