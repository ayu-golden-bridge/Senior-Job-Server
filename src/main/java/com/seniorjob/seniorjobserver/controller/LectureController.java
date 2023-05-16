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
	public String list(Model model) {
		List<LectureDto> lectureList = lectureService.getLecturelist();

		model.addAttribute("lectureList", lectureList);
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
		// return "lecture/detail.html";
		return lectureDTO.toString();
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