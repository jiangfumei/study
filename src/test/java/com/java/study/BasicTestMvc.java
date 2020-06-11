package com.java.study;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class BasicTestMvc /*extends AbstractTransactionalJUnit4SpringContextTests*/ {

    @Resource
    protected EntityManager manager;

    protected MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    protected ObjectMapper mapper = new ObjectMapper();

    @Before // 7 测试开始前的初始化工作
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // 2
    }

    public void demo() throws Exception {
        TestingAuthenticationToken an = new TestingAuthenticationToken("", "");
        mockMvc.perform(//
                get("/403.html").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                        .principal(an)//
        )//
                .andDo(print())//
                .andExpect(status().is4xxClientError())//
        ;
        //.andExpect(jsonPath("$.result").value("SUCCESS"));
        // {"mobilephone":17161654483}
    }
}
