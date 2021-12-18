package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 생성자도 안쓰고 그냥 필드인젝션으로 넣어줌. 컨테이너가 알아서 함
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입(){
        // given -- 새 member의 name을 설정.
        Member member = new Member();
        member.setName("spring");

        // when -- 새 회원을 가입시키면 ID 가 자동 생성
        Long saveId = memberService.join(member);

        // then -- 회원 가입 시킨 멤버 == 여기서 생성한 멤버 ?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // given -- 같은 이름의 회원을 만든다.
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

            /* join(member2) 를 하면 IllegalStateException 이 터지는지 확인한다. */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

            /* 오류 메시지 검증 */
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}