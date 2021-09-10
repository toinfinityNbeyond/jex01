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
}
