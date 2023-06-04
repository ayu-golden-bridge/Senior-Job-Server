package com.seniorjob.seniorjobserver.controller;

import com.seniorjob.seniorjobserver.dto.MemberDto;
import com.seniorjob.seniorjobserver.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 수강자 회원생성 API
    // POST /api/members
    @PostMapping
    public ResponseEntity<Long> createMember(@RequestBody MemberDto memberDto) {
        Long memberId = memberService.createMember(memberDto);
        return ResponseEntity.ok(memberId);
    }
}
