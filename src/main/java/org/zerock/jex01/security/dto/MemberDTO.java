package org.zerock.jex01.security.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.jex01.security.domain.Member;

import java.util.Collection;
import java.util.stream.Collectors;


@Getter
@Setter

public class MemberDTO extends User { //1.User라는 클래스를 상속을 해서 만든다   -> 이렇게 만들거야~~
                                      //2.UserDetails를 가지고 인터페이스 구현

    private String mid;
    private String mpw;
    private String mname;
    private boolean enabled;

    public MemberDTO(Member member){
        super(member.getMid(),
                member.getMpw(),
                member.getRoleList().stream()                                               //권한 정보
                        .map(memberRole -> new SimpleGrantedAuthority(memberRole.getRole())).collect(Collectors.toList())); //memberRole을 GrantedAuthority으로 바꿔줘야함

        // 멤버를 넣어주면 멤버DTO가 나가게끔 만든다. 생성자 때문에 타입을 바꿔준다(타입 컨버팅)
        this.mid = member.getMid();
        this.mpw = member.getMpw();
        this.mname = member.getMname();
        this.enabled = member.isEnabled();

    }

    public MemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
