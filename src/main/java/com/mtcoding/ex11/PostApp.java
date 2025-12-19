package com.mtcoding.ex11;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}

// GSON 라이브러리 작동 방법
// 1. Post 객체를 new 한다 -> "디폴트 생성자"를 new 한다.
// 2. Post 객체에 setter를 호출한다.
public class PostApp {
    public static void main(String[] args) {
        String json = """
                {
                "userId": 1,
                "id": 1,
                "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "body": "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto"
                }
                """;

        Gson gson = new Gson();
        Post p = gson.fromJson(json, Post.class); // Post.class 는 클래스의 위치와 클래스명 알려줌
        System.out.println();
    }
}
