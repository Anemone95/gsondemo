package top.anemone.gsondemo.PoC;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.HashMap;

public class VulnContainer {
    private HashMap<VulnObject, String> vulnObjectHashMap;

    public HashMap<VulnObject, String> getvulnObjectHashMap() {
        return vulnObjectHashMap;
    }

    public void setvulnObjectHashMap(HashMap<VulnObject, String> vulnObjectHashMap) {
        this.vulnObjectHashMap = vulnObjectHashMap;
    }
}
