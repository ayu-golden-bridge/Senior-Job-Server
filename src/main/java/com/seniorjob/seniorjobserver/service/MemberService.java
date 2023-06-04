package com.seniorjob.seniorjobserver.service;

import com.seniorjob.seniorjobserver.domain.entity.MemberEntity;
import com.seniorjob.seniorjobserver.dto.MemberDto;
import com.seniorjob.seniorjobserver.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 수강자 회원생성
    public Long createMember(MemberDto memberDto){
        MemberEntity memberEntity = memberDto.toEntity();
        MemberEntity saveMember = memberRepository.save(memberEntity);
        return saveMember.getMember_id();
    }
}
