package main.store;

import java.util.HashMap;
import java.util.Map;

public class Local {
    private Map<String, String> map;

    public Local() {
        map = new HashMap<>();
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void keep(String key, String value) {
        // Se guarda en local
        map.put(key, value);
        System.out.println("[INFO] Local:keep: (" + key + ":" + value + ")");
    }

    public String find(String key) {
        // Se busca de local
        System.out.println("[INFO] Local:find: (" + key + ":" + map.get(key) + ")");
        return map.get(key);
    }

    public int size() {
        return map.size();
    }

}
