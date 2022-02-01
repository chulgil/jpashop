package me.chulgil.jpashop.service;

import lombok.RequiredArgsConstructor;
import me.chulgil.jpashop.domain.Member;
import me.chulgil.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cglee
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param member 회원
     * @return long 회원아이디
     */
    @Transactional
    public Long join(Member member) {

        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 단일 조회
     * @param memberId 회원아이디
     * @return 회원
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 전체 조회
     * @return 회원리스트
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }





}
