package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();   //회원을 저장할 임시 DB?라고 생각
    private static long sequence = 0L;  //일련번호
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //멤버 저장시 일련번호 +1
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //null 반환할 경우를 대비해서 Optional 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member ->member.getName().equals(name))  //같은 name을 가지고 있는 객체 찾으면 반환, 없으면 null 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
