package com.bookmark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 所有测试方法之前
     */
    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void loginTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("userName", "987986567@qq.com").param("passWord",
                        "987986567@qq.com")
        ).andReturn();
        HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
        for(HandlerInterceptor hi :interceptors){
            System.out.println(hi.getClass().getName());
        }

        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString(); // 7
        System.out.println("返回内容："+responseString);
        Assert.assertEquals("return status not equals 200", 200, status); // 8
    }


}
