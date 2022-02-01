package me.chulgil.jpashop.service;

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
public class MemberService {


    private MemberRepository memberRepository;

    /**
     * 세터인젝션 리파지토리 추가
     * 장점 : 테스트 코드 작성시 Mock주입 가능
     * 단점 : 런타임 시점에 변경 가능성이 있다.
     * @param memberRepository
     */
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
