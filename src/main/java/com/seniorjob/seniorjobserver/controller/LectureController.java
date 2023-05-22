package com.seniorjob.seniorjobserver.controller;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.service.LectureService;
import lombok.AllArgsConstructor;
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
	// GET /api/lectures/sort/price-range?lowToHigh=true / false
	// 강좌 가격별 api true / false
	@GetMapping("/sort/price-range")
	public ResponseEntity<List<LectureDto>> getLecturesByPriceRange(@RequestParam("lowToHigh") boolean isLowToHigh) {
		List<LectureDto> lecturesByPriceRange = lectureService.getLecturesByPriceRange(isLowToHigh);
		return ResponseEntity.ok(lecturesByPriceRange);
	}


	private LectureDto convertToDto(LectureEntity lectureEntity) {
		if (lectureEntity == null)
			return null;

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


	//---------------------------------------
	// 아래는 이전에 html파일로 확인했을 때 쓰던 코드
	/* 게시글 목록 */
//	@GetMapping("/")
//	public String list(Model model) {
//		List<LectureDto> lectureList = lectureService.getLecturelist();
//
//		model.addAttribute("lectureList", lectureList);
//		return "lecture/list.html";
//	}


//	@GetMapping("/post")
//	public String write() {
//		return "lecture/write.html";
//	}
//
//	@PostMapping("/post")
//	public String write(@ModelAttribute LectureDto lectureDto) {
//		lectureService.savePost(lectureDto);
//
//		return "redirect:/";
//	}


//	@GetMapping("/post/{no}")
//	public String detail(@PathVariable("no") Long no, Model model) {
//		LectureDto lectureDTO = lectureService.getPost(no);
//
//		model.addAttribute("lectureDto", lectureDTO);
//		// return "lecture/detail.html";
//		return "lecture/detail.html";
//	}
//
//	@GetMapping("/post/edit/{no}")
//	public String edit(@PathVariable("no") Long no, Model model) {
//		LectureDto lectureDTO = lectureService.getPost(no);
//
//		model.addAttribute("lectureDto", lectureDTO);
//		return "lecture/update.html";
//	}
//
//
//	@PutMapping("/post/edit/{no}")
//	public String update(LectureDto lectureDTO) {
//		lectureService.savePost(lectureDTO);
//
//		return "redirect:/";
//	}
//
//	@DeleteMapping("/post/{no}")
//	public String delete(@PathVariable("no") Long no) {
//		lectureService.deletePost(no);
//
//		return "redirect:/";
//	}
}