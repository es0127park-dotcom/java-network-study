package com.mtcoding.ex11;

import com.google.gson.Gson;

public class PostNetworkApp {
    public static void main(String[] args) {
        try {
            Repository repo = new Repository();
            String json = repo.download("https://jsonplaceholder.typicode.com/posts/1");

            Gson gson = new Gson();
            Post p = gson.fromJson(json, Post.class);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
