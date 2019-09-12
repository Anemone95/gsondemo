package top.anemone.gsondemo;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Gson gson =new Gson();
        User user= new User();
        user.setName("anemone");
        user.setAge(18);
        user.setTags(Arrays.asList(new String[] { "123", "456", "789" }));
        String json=gson.toJson(user);
        System.out.println(json);

        Gson gson2 =new Gson();
        User u=gson2.fromJson(json,User.class);
        System.out.println(u);
    }
}
