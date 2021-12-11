package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // localhost:8080/hello 로 들어오는 요청을 처리함.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!!!"); // 템플릿에서 $(data)로 변수 사용가능.
        return "hello"; // resources/templates/hello.html 를 찾아서 렌더하겠다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name="name") String name, Model model) {
        // url ? 파라미터에 오는 "name"이라는 키값에 대한 정보를 name 변수에 담는다.
        // 그리고 아래 attributeName 으로 템플릿에서 사용하면 name에 해당하는 값을 출력해준다.
        // (name="name", required = false) 와 같이 required 를 false로 바꿔줄 수도 있다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // 이 주소로 요청을 하면
    @ResponseBody  // 웹 요청 body에 데이터를 직접 넣어줌.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("nname") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 넘겨줌.
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

