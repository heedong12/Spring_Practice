package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IAttribute;

@Controller
public class HelloController {
    @GetMapping("hello") // /hello 가 들어오면 호출
    public String hello(Model model){
        model.addAttribute("data","hello!!");   //키가 data 값이 hello!!
        return "hello";     //resources의 templates에 있는 hello.thml로 이동하라는 의미
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){  //외부에서 파라미터를 받을 것임
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   //http의 바디 부분에 이 데이터를 집적 넣겠다
    public String helloString(@RequestParam("name")String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();  //객체 생성
        hello.setName(name);
        return hello;   //객체 넘김, json 방식으로 반환
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
