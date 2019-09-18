package top.anemone.gsondemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;

public class UserContainer {
    private HashMap<User, String> userUserHashMap;

    public HashMap<User, String> getUserUserHashMap() {
        return userUserHashMap;
    }

    public void setUserUserHashMap(HashMap<User,String> userUserHashMap) {
        this.userUserHashMap = userUserHashMap;
    }

    public static void main(String[] args) {
        // 如果Map中的Key为自定义对象，则需要enableComplexMapKeySerialization
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        UserContainer userContainer=new UserContainer();
        HashMap<User, String> hashMap=new HashMap<>();
        User user= new User();
        user.setName("anemone");
        user.setAge(18);
        user.setTags(Arrays.asList(new String[] { "123", "456", "789" }));
        hashMap.put(user, "String");
        userContainer.setUserUserHashMap(hashMap);
        String json=gson.toJson(userContainer);
        System.out.println(json);

        UserContainer userContainer1=gson.fromJson(json, UserContainer.class);
        System.out.println(userContainer1);
    }
}
