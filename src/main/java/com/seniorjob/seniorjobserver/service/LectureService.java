package com.seniorjob.seniorjobserver.service;

import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class LectureService {
    private LectureRepository lectureRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수

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
    public List<LectureDto> getBoardlist(Integer pageNum) {
        Page<LectureEntity> page = LectureRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<LectureEntity> lectureEntities = page.getContent();
        List<LectureDto> lectureDtoList = new ArrayList<>();

        for (LectureEntity lectureEntity : lectureEntities) {
            lectureDtoList.add(this.convertEntityToDto(lectureEntity));
        }

        return lectureDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return lectureRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

    // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

    // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

    // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

    // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

    // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
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

    @Transactional
    public List<LectureDto> searchPosts(String title) {
        List<LectureEntity> LectureEntities = lectureRepository.findByTitleContaining(title);
        List<LectureDto> LectureDtoList = new ArrayList<>();

        if(LectureEntities.isEmpty()) return  LectureDtoList;

        for(LectureEntity LectureEntity : LectureEntities) {
            LectureDtoList.add(this.convertEntityToDto(LectureEntity));
        }

        return LectureDtoList;
    }

    private LectureDto convertEntityToDto(LectureEntity lectureEntity) {
        return LectureDto.builder()
                .id(lectureEntity.getId())
                .title(lectureEntity.getTitle())
                .content(lectureEntity.getContent())
                .writer(lectureEntity.getWriter())
                .createdDate(lectureEntity.getCreatedDate())
                .build();
    }
}