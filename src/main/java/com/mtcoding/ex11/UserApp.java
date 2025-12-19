package com.mtcoding.ex11;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Company company;

    @Setter
    @Getter
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }
}

public class UserApp {
    public static void main(String[] args) {
        try {
            // 1. 다운로드 Repository download()
            Repository repo = new Repository();
            String json = repo.download("https://jsonplaceholder.typicode.com/users/1");

            // 2. 다운로드 확인
            // System.out.println();

            // 3. User 클래스 완성하기

            // 4. 오브젝트로 변환 (json -> User) - gson 필요
            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);

            // 5. 변환 확인
            // System.out.println();

            // 6. 콘솔에 유저정보 출력
            System.out.println("ID : " + user.getId());
            System.out.println("Name : " + user.getName());
            System.out.println("UserName : " + user.getUsername());
            System.out.println("E-mail : " + user.getEmail());
            System.out.println("Phone : " + user.getPhone());
            System.out.println("WebSite : " + user.getWebsite());
            System.out.println("CompanyName : " + user.getCompany().getName());
            System.out.println("CompanyCatchPhrase : " + user.getCompany().getCatchPhrase());
            System.out.println("CompanyBs : " + user.getCompany().getBs());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
