package org.zerock.jex01.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.common.config.RootConfig;
import org.zerock.jex01.security.config.SecurityConfig;

import java.sql.SQLOutput;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})// 두 개다 로딩해줘야 에러가 안난다
public class PasswordTests {

   @Autowired
   PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String str = "member1";
        String enStr = passwordEncoder.encode(str);
        log.warn(enStr);

    }

    @Test
    public void testDecode() {
        String str = "$2a$10$S9IWhMaX06oWxWk4BlKL5ObyCarNkSSNpwC7x57aafbatyNl3W8Nm";

        boolean match = passwordEncoder.matches("member1" , str);

        log.warn(match);
    }

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

        String qeury = "insert into tbl_member (mid,mpw,mname) values ('v1','v2','v3');";

        for(int i = 0; i < 10; i++) {

            String mid = "admin" + i; //user0
            String mpw = passwordEncoder.encode("pw" + i); // pw0 -> Bcrypted
            String mname = "관리자" + i; //유저0

            String result = qeury;

            result = result.replace("v1" , mid);
            result = result.replace("v2" , mpw);
            result = result.replace("v3", mname);

            System.out.println(result);
        }

    }



}
