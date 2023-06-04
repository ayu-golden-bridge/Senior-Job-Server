package com.seniorjob.seniorjobserver.controller;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.domain.entity.UserEntity;
import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.dto.UserDto;
import com.seniorjob.seniorjobserver.service.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
	private final LectureService lectureService;

	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}

	// GET /api/lectures
	@GetMapping
	public ResponseEntity<List<LectureDto>> getAllLectures() {
		List<LectureDto> lectureList = lectureService.getAllLectures();
		return ResponseEntity.ok(lectureList);
	}

	// POST /api/lectures
	@PostMapping
	public ResponseEntity<LectureDto> createLecture(@RequestBody LectureDto lectureDto) {
		LectureDto createdLecture = lectureService.createLecture(lectureDto);
		return ResponseEntity.ok(createdLecture);
	}

	// PUT /api/lectures/{id}
	@PutMapping("/{id}")
	public ResponseEntity<LectureDto> updateLecture(@PathVariable("id") Long id, @RequestBody LectureDto lectureDto) {
		LectureDto updatedLecture = lectureService.updateLecture(id, lectureDto);
		return ResponseEntity.ok(updatedLecture);
	}

	// Delete /api/lectures/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLecture(@PathVariable("id") Long id) {
		lectureService.deleteLecture(id);
		return ResponseEntity.noContent().build();
	}

	// GET /api/lectures/{id}
	@GetMapping("/{id}")
	public ResponseEntity<LectureDto> getDetailLectureById(@PathVariable("id") Long id) {
		LectureDto lecture = lectureService.getDetailLectureById(id);
		return ResponseEntity.ok(lecture);
	}

	// GET /api/lectures/search?title={title}
	@GetMapping("/search")
	public ResponseEntity<List<LectureDto>> searchLecturesByTitle(@RequestParam("title") String title) {
		List<LectureDto> lectures = lectureService.searchLecturesByTitle(title);
		return ResponseEntity.ok(lectures);
	}

	// 정렬 api
	// 최신글정렬 = true, 오래된글정렬 = false
	// GET /api/lectures/sorted/created-date?descending=true (DESC 내림차순 : 최신순)
	// GET /api/lectures/sorted/created-date?descending=true (ASC 오름차순 : 오래된적은순)
	@GetMapping("/sorted/created-date")
	public ResponseEntity<List<LectureDto>> getAllLecturesSortedByCreatedDate(
			@RequestParam(value = "descending", defaultValue = "true") boolean descending) {
		List<LectureDto> lectureList = lectureService.getAllLecturesSortedByCreatedDate(descending);
		return ResponseEntity.ok(lectureList);
	}

	// 인기순 : max_participant가많은순 -> 강좌 참여하기를 만들때 실제참여자가 많은순으로 변경할것임
	// GET /api/lectures/sorted/popularity?descending=true (DESC 내림차순 : 참여제한이 많은순)
	// GET /api/lectures/sorted/popularity?descending=false (ASC 오름차순 : 참여제한이 적은순)
	@GetMapping("/sorted/popularity")
	public ResponseEntity<List<LectureDto>> getAllLecturesSortByPopularity(
			@RequestParam(value = "descending", defaultValue = "true") boolean descending) {
		List<LectureDto> lectureList = lectureService.getAllLecturesSortByPopularity(descending);
		return ResponseEntity.ok(lectureList);
	}

	// 가격순 : price기준
	// GET /api/lectures/sorted/price?ascending=true (DESC 내림차순 : 가격이 높은순)
	// GET /api/lectures/sorted/price?ascending=true (ASC 오름차순 : 가격이 낮은순)
	@GetMapping("/sorted/price")
	public ResponseEntity<List<LectureDto>> getAllLecturesSortByPrice(
			@RequestParam(value = "ascending", defaultValue = "true") boolean ascending) {
		List<LectureDto> lectureList = lectureService.getAllLecturesSortByPrice(ascending);
		return ResponseEntity.ok(lectureList);
	}

	// 페이징
	// GET /api/lectures/paging?page={no}&size={no}

	@GetMapping("/paging")
	public ResponseEntity<Page<LectureDto>> getLecturesWithPagination(
			@RequestParam(defaultValue = "0", name = "page") int page,
			@RequestParam(defaultValue = "12", name = "size") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<LectureEntity> lecturePage = lectureService.getLectures(pageable);

		List<LectureDto> lectureDtoList = lecturePage.getContent().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());

		Page<LectureDto> pagedLectureDto = new PageImpl<>(lectureDtoList, pageable, lecturePage.getTotalElements());

		return ResponseEntity.ok(pagedLectureDto);
	}


	private LectureDto convertToDto(LectureEntity lectureEntity) {
		if (lectureEntity == null)
			return null;

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
}