package com.drpicox.ddd;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.google.common.truth.Truth.assertThat;

public class HelloSteps {

    @Autowired NiceService niceService;

    @Given("say hello")
    public void say_hello() {
        System.out.println("Hello!");
        System.out.println(niceService);
        System.out.println("Hello!");
    }

    @Given("^hello (\\w+)$")
    public void hello_XXX(String name) {
        System.out.println("Hello " + name);
    }

    private String myName;

    @Given("I am (\\w+)")
    public void i_am_XXX(String name) {
        myName = name;
    }

    @Given("I say hello")
    public void i_say_hello() {
        System.out.println("I am " + myName + " and I say hello");
    }

    @Given("My favourite number is (\\d+)")
    public void my_favourite_number_is_XXX(int number) {
        System.out.println("My favourite number is " + number);
    }

}
