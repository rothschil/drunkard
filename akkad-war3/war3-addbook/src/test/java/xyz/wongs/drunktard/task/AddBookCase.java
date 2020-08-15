package xyz.wongs.drunkard.task;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.wongs.drunkard.base.BaseTest;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUser;

public class AddBookCase extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRegister() throws Exception{
        RegisterUser registerUser = RegisterUser.builder().nickName("王老邪").uniCode("WCNGS").mobile("18900010002").email("WCNGS@QQ.COM").build();

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/addBook/register").contentType(MediaType.APPLICATION_JSON)
                 .content(JSONObject.toJSONString(registerUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

//        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetById3() throws Exception{
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/locations/findByLocationId")
                .param("id","143840").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetById4() throws Exception{
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/locations/findByLocationId")
                .param("id","143840").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
