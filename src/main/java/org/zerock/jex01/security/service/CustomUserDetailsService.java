package org.zerock.jex01.security.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.jex01.security.domain.Member;
import org.zerock.jex01.security.dto.MemberDTO;
import org.zerock.jex01.security.mapper.MemberMapper;


@Log4j2
@RequiredArgsConstructor //@NonNull 이나 final이 붙은 변수에 대한 생성자를 만들어준다
@Service //@service 어노테이션은 해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //username 은 사용자 id

        log.warn("CustomUserDetailsService--------------------------------------");
        log.warn("CustomUserDetailsService--------------------------------------");
        log.warn(username);
        log.warn(memberMapper);
        log.warn("CustomUserDetailsService--------------------------------------");
        log.warn("CustomUserDetailsService--------------------------------------");
        log.warn("CustomUserDetailsService--------------------------------------");


        Member member = memberMapper.findByMid(username);

        log.warn(member);

        if (member == null){
            throw new UsernameNotFoundException("NOT EXIST");
        }

        String[] authorities = member.getRoleList().stream().map(memberRole -> memberRole.getRole()).toArray(String[]::new);

        User result = new MemberDTO(member);
//        User result = (User) User.builder()
//                .username(username)
//                .password(member.getMpw())
//                .accountExpired(false) // 만료된 계정
//                .accountLocked(false) // 열려있는 계정
//                .authorities(authorities) // 계정 권한. 핸들링을 할 때는 이렇게 줘야한다.
//                .build();


        return result;
    }
}
