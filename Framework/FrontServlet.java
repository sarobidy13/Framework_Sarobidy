package etu1816.framework.servlet;

import etu1816.framework.*;
import util.*;
import etu1816.framework.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.lang.reflect.InvocationTargetException;

public class FrontServlet extends HttpServlet {
    Util util;
    HashMap<String, Object> singleton;
    HashMap<String, Mapping> MappingUrls;
    String sessionVariable;

    @Override
    public void init() {
        try {

            this.util = new Util();
            this.MappingUrls = new HashMap<>();
            this.singleton = new HashMap<>();
            this.sessionVariable = getInitParameter("session");

            String tomPath = "/WEB-INF/classes/";
            String path = getServletContext().getRealPath(tomPath);
            List<Class<?>> allClass = util.FindAllClass(path, tomPath);
            util.loadMapping(path, tomPath, MappingUrls, singleton);
            Mapping mapping;
            Method[] allMethods;
            for (Class<?> c : allClass) {
                allMethods = c.getMethods();

                for (Method m : allMethods) {
                    if (m.isAnnotationPresent(MethodAnnotation.class)) {
                        mapping = new Mapping();
                        mapping.setClassName(c.getName());
                        mapping.setMethod(m.getName());
                        MappingUrls.put(m.getAnnotation(MethodAnnotation.class).url(), mapping);

                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        url = util.getURL(url);
        try {
            Mapping map = MappingUrls.get(url);

            if (map == null) {
                throw new Exception("Not Found");
            }

            Class<?> clazz = Class.forName(map.getClassName());
            Object o = clazz.getDeclaredConstructor().newInstance();

            Field[] allField = o.getClass().getDeclaredFields();
            String field_name;
            String value;
            for (Field f : allField) {

                field_name = f.getName();
                value = request.getParameter(field_name);

                if (value != null) {
                    o.getClass().getMethod("set" + util.casse(field_name), String.class).invoke(o, value);
                }
            }

            ModelView mv = (ModelView) o.getClass().getMethod(map.getMethod()).invoke(o);

            HashMap<String, Object> donne = mv.getData();
            for (String key : donne.keySet()) {
                System.out.println(key);
                request.setAttribute(key, donne.get(key));
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(mv.getView());
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
