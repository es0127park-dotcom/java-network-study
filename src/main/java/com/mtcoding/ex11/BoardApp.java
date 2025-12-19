package com.mtcoding.ex11;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
class Common {
    private Integer status;
    private String msg;
    private List<Body> body;

    @Getter @Setter
    public static class Body {
        private Integer id;
        private String title;
        private String content;
        private String author;
    }
}

public class BoardApp {
    public static void main(String[] args) {
        try {
            Repository repo = new Repository();
            String json = repo.download("http://192.168.0.99:8080/api/boards");
            // System.out.println();

            Gson gson = new Gson();
            Common common = gson.fromJson(json, Common.class);
            // System.out.println();

            List<Common.Body> list = common.getBody();
            System.out.println("상태 : " + common.getStatus());
            System.out.println("메세지 : " + common.getMsg());
            System.out.println();
            for (int i = 0; i < list.size(); i++) {
                System.out.println("ID : " + list.get(i).getId());
                System.out.println("제목 : " + list.get(i).getTitle());
                System.out.println("내용 : " + list.get(i).getContent());
                System.out.println("작가 : " + list.get(i).getAuthor());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
