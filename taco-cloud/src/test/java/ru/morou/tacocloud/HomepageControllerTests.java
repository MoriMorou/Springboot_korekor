package ru.morou.tacocloud;


import static org.hamcrest.Matchers.containsString; import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @WebMvcTest - это специальная аннотация теста, предоставляемая Spring Boot, которая организует запуск теста в
 * контексте приложения Spring MVC. Он организует регистрацию HomeController в Spring MVC
 */

@RunWith (SpringRunner.class)
//@WebMvcTest(HomeController.class)
public class HomepageControllerTests {

// Injects MockMvc - чтобы выполнить HTTP-запрос GET для / (корневой путь).
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))                                               // Performs GET
                .andExpect(status().isOk())                                                // Expects HTTP 200
                .andExpect(view().name("Home"))                           // Expects home view
                .andExpect(content().string(containsString("Welcome to...")));    // Expects Welcome to...
    }
}
