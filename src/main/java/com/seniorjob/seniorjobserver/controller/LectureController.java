package com.seniorjob.seniorjobserver.controller;

import com.seniorjob.seniorjobserver.dto.LectureDto;
import com.seniorjob.seniorjobserver.service.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class LectureController {
	private LectureService lectureService;

	/* 게시글 목록 */
	@GetMapping("/")
	public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
		List<LectureDto> lectureList = lectureService.getLecturelist();
		Integer[] pageList = LectureService.getPageList(pageNum);

		model.addAttribute("lectureList", lectureList);
		model.addAttribute("pageList", pageList);
		return "lecture/list.html";
	}
	/* 게시글 검색*/
	@GetMapping("/board/search")
	public String search(@RequestParam(value="title") String title, Model model) {
		List<LectureDto> lectureDtoList = lectureService.searchPosts(title);

		model.addAttribute("lectureList", lectureDtoList);

		return "lecture/list.html";
	}


	@GetMapping("/post")
	public String write() {
		return "lecture/write.html";
	}

	@PostMapping("/post")
	public String write(LectureDto lectureDto) {
		lectureService.savePost(lectureDto);

		return "redirect:/";
	}

	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") Long no, Model model) {
		LectureDto lectureDTO = lectureService.getPost(no);

		model.addAttribute("lectureDto", lectureDTO);

		return "lecture/detail.html";

	}

	@GetMapping("/post/edit/{no}")
	public String edit(@PathVariable("no") Long no, Model model) {
		LectureDto lectureDTO = lectureService.getPost(no);

		model.addAttribute("lectureDto", lectureDTO);
		return "lecture/update.html";
	}


	@PutMapping("/post/edit/{no}")
	public String update(LectureDto lectureDTO) {
		lectureService.savePost(lectureDTO);

		return "redirect:/";
	}

	@DeleteMapping("/post/{no}")
	public String delete(@PathVariable("no") Long no) {
		lectureService.deletePost(no);

		return "redirect:/";
	}




}