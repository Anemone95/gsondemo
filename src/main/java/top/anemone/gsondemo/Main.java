package top.anemone.gsondemo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

        Gson gson3 =new Gson();
        HashMap<String, User> map= new HashMap<String, User>();
        map.put("anemone", user);
        String jsonMap=gson.toJson(map);

        Gson gson4 =new Gson();
        Type type =
                new TypeToken<HashMap<String, User>>(){}.getType();
        Map map2=gson4.fromJson(jsonMap,type);
        System.out.println(map2);


        Gson gson5 =new Gson();
        UserContainer userContainer=new UserContainer();
        HashMap<User, String> hashMap=new HashMap<>();
        hashMap.put(user, "String");
        userContainer.setUserUserHashMap(hashMap);
        json=gson5.toJson(userContainer);
        System.out.println(json);

        Gson gson6 =new Gson();
        UserContainer userContainer1=gson6.fromJson(json, UserContainer.class);
        System.out.println(userContainer1);

    }
}
