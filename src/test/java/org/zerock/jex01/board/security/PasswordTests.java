package org.zerock.jex01.board.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.common.config.RootConfig;
import org.zerock.jex01.security.config.SecurityConfig;
import org.zerock.jex01.security.domain.Member;
import org.zerock.jex01.security.mapper.MemberMapper;


@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})// 두 개다 로딩해줘야 에러가 안난다
public class PasswordTests {

   @Autowired
   PasswordEncoder passwordEncoder;

   @Autowired(required = false) //@Autowired 안에 required = false 를 사용하면 오류가 나지 않는다.
    MemberMapper memberMapper;


   @Test
   public void testMember() {
       String mid = "admin0";

       Member member = memberMapper.findByMid(mid);

       log.warn("--------------------");
       log.warn(member);
   }

    @Test
    public void testEncode() {
        String str = "member1";
        String enStr = passwordEncoder.encode(str);
        log.warn(enStr);

    }

    @Test
    public void testDecode() {
                      //로그에 찍힌 값을 복사해옴
        String str = "$2a$10$S9IWhMaX06oWxWk4BlKL5ObyCarNkSSNpwC7x57aafbatyNl3W8Nm";

        boolean match = passwordEncoder.matches("member1" , str); // member1 의 패스워드 값과 str이랑 같은지 확인

        log.warn(match + ".====================================아 졸려");
    } // 테스트를 통해 사용자의 패스워드랑 맞는지 확인?
     // SecurityConfig 메서드에 값을 준 member1의 값과 str의 값이 같이 때문에 테스트 결과는 true
     // 둘 중 하나 다른 값을 주면 false가 나온다

    @Test
    public void insertMember(){

        //insert into tbl_member (mid,mpw,mname) values('mid','mpw','mname');

        String qeury = "insert into tbl_member (mid,mpw,mname) values ('v1','v2','v3');";

        for(int i = 0; i < 10; i++) {

            String mid = "user" + i; //user0
            String mpw = passwordEncoder.encode("pw" + i); // pw0 -> Bcrypted
            String mname = "유저" + i; //유저0

            String result = qeury;

            result = result.replace("v1" , mid);
            result = result.replace("v2" , mpw);
            result = result.replace("v3", mname);

            System.out.println(result);
        }

    }


    @Test
    public void insertAdmin(){

        //insert into tbl_member (mid,mpw,mname) values('mid','mpw','mname');

        String query = "insert into tbl_member (mid,mpw,mname) values ('v1','v2','v3');";

        for(int i = 0; i < 10; i++) {

            String mid = "admin" + i; //user0
            String mpw = passwordEncoder.encode("pw" + i); // pw0 -> Bcrypted
            String mname = "관리자" + i; //유저0

            String result = query;

            result = result.replace("v1" , mid);
            result = result.replace("v2" , mpw);
            result = result.replace("v3", mname);

            System.out.println(result);
        }

    }

    @Test
    public void insertMemberRole(){

        String sql = "insert into tbl_member_role (mid, role) values ('%s' , '%s');";

        for (int i = 0; i < 10;i++){
            String result = String.format(sql, "user" + i, "ROLE_MEMBER");

            System.out.println(result);
        } // end for
    }


    @Test
    public void insertAdminRole(){  //role 테이블 계정을 MEMBER, ADMIN 두개를 줘야함

        String sql = "insert into tbl_member_role (mid, role) values ('%s' , '%s');";

        for (int i = 0; i < 10; i++){

            String result = String.format(sql, "admin" + i, "ROLE_MEMBER");

            System.out.println(result);

            String result2 = String.format(sql, "admin" + i, "ROLE_ADMIN");

            System.out.println(result2);

        } // end for
    }



}
