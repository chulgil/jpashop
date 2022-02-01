package me.chulgil.jpashop.service;

import lombok.AllArgsConstructor;
import me.chulgil.jpashop.domain.Member;
import me.chulgil.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cglee
 */
@Service
@Transactional(readOnly = true)
// 모든 필드를 가지고 생성자 인젝션을 자동으로 주입
@AllArgsConstructor
public class MemberService {

    // final 선언시 생성자 값 체크를 컴파일 시점에 가능 하다.
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
