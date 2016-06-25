package main.store;

import java.util.HashMap;
import java.util.Map;

public class Net {

    private static Net net = new Net();

    private Map<String, String> map;

    private boolean available;

    private int count;

    public Net() {
        available = true;
        count = 0;
        map = new HashMap<>();
    }

    public static Net getNet() {
        return net;
    }

    public boolean isAvailable() {
        count++;
        available = (count % 10 != 0 && count % 10 != 1 && count % 10 != 2);
        return available;
    }

    public void write(String key, String value) {
        if (!available) {
            throw new UnsupportedOperationException("No existe conexión... ");
        }
        map.put(key, value);
        System.out.println("[INFO] Net:write: (" + key + ":" + value + ")");
    }

    public void write(Map<String, String> map) {
        this.map.putAll(map);
        System.out.println("[INFO] Net:write: (" + map + ":" + map.toString());
    }

    public String read(String key) {
        if (!available) {
            throw new UnsupportedOperationException("No existe conexión... ");
        }
        System.out.println("[INFO] Net:read: (" + key + ":" + map.get(key) + ")");
        return map.get(key);
    }

}
