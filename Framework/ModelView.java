package etu1816.framework;

import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;

public class ModelView {
    String view;
    HashMap<String, Object> data;
    HashMap<String, String> session;
    boolean json;
    boolean api;
    Gson gson;
    public ModelView() {
        data = new HashMap<>();
        session = new HashMap<>();
        json = false;
        gson = new Gson();
    }

    public void addItem(String key, Object value) {
        data.put(key, value);
    }

    public void addSession(String key, String value) {
        session.put(key, value);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, String> getSession() {
        return session;
    }

    public void setSession(HashMap<String, String> session) {
        this.session = session;
    }
    public boolean isJson() {
        return json;
    }

    public void setJson(boolean json) {
        this.json = json;
    }
    public String toJson() {
        return gson.toJson(data);
    }
    public boolean isApi() {
        return api;
    }

    public void setApi(boolean api) {
        this.api = api;
    }
}
