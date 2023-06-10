package com.seniorjob.seniorjobserver.controller;

import com.seniorjob.seniorjobserver.service.LectureApplyService;
import com.seniorjob.seniorjobserver.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/")
public class LectureApplyController {

    private final LectureApplyService lectureApplyService;

    @Autowired
    public LectureApplyController(LectureApplyService lectureApplyService) {
        this.lectureApplyService = lectureApplyService;
    }

    // 강좌참여API
    // POST /api/lectureapply/
    @PostMapping("/lectureapply")
    public ResponseEntity<String> applyForLecture(@RequestParam Long userId, @RequestParam Long lectureId) {
        try {
            lectureApplyService.applyForLecture(userId, lectureId);
            return ResponseEntity.ok("강좌 신청에 성공하였습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 강좌 신청 취소 API
    @DeleteMapping("/lectureapply")
    public String cancelLectureApply(@RequestParam Long userId, @RequestParam Long lectureId) {
        return lectureApplyService.cancelLectureApply(userId, lectureId);
    }
}
