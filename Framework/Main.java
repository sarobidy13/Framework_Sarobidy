import com.google.gson.Gson;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Object> hs = new HashMap<>();

        hs.put("test", 123);
        hs.put("test1", 1234);

        Gson gson = new Gson();
        String result = gson.toJson(hs);

        System.out.println(result);
    }
}
