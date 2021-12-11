package hello.hellospring.controller;

public class MemberForm {
    private String name;
    /* html 의 "name"이라는 값을 찾고, 그 값에 해당하는 데이터를 가져와서 위 필드에 담는다.
    * 이것은 Spring 이 setName 메서드를 사용하여 필드를 정의하는 것이다.
    * "name"에 해당하는 값이 여러개면 모두 가져와 ,로 구분하여 붙인다. (여전히 String) */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
