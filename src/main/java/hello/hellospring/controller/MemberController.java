package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")     //데이터 조회는 GetMapping
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")    //회원가입
    public String create(MemberForm form) {      //데이터 등록은 PostMapping
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";    //회원가입 후에 홈 화면으로 보냄
    }

    @GetMapping("/members")     //회원등록
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
