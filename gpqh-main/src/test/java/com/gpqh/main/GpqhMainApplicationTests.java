package com.gpqh.main;

import com.gpqh.user.utils.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GpqhMainApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new PasswordUtil().encodePassword("123456"));
    }

}
