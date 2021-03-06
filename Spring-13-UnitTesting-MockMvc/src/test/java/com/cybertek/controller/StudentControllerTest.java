package com.cybertek.controller;

import com.cybertek.entity.Student;
import com.cybertek.service.StudentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void getStudent_service() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/student").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\": 0,"+ "\"firstName\": \"mike\"," + "\"lastName\": \"smith\"," + "\"age\": 20" + "}"))
                .andReturn();
    }

    @Test
    void jsonAssert() throws JSONException {
        String actual = "{\"id\": 0,"+ "\"firstName\": \"mike\"," + "\"lastName\": \"smith\"," + "\"age\": 20" + "}";
        String expected = "{\"id\": 0,"+ "\"firstName\": \"mike\"," + "\"lastName\": \"smith\"," + "}";

        JSONAssert.assertEquals(expected,actual, false);

    }

    @Test
    void getStudent_data() throws Exception {
        Mockito.when(studentService.getStudent_data())
                .thenReturn(Arrays.asList(
                        new Student("Ozzy","can",20),
                        new Student("Tom","Hanks",50)
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/data").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":0,\"firstName\":\"Ozzy\",\"lastName\":\"can\",\"age\":20},{\"id\":0,\"firstName\":\"Tom\",\"lastName\":\"Hanks\",\"age\":50}]"))
                .andReturn();

    }

}