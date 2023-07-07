
package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public String getURL(String url) {
        String[] methode = url.split("/");
        String method = methode[methode.length - 1];
        return method;

    }

    public ArrayList<Class<?>> FindAllClass(String path, String path2) throws Exception {
        ArrayList<Class<?>> list = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            return list;
        }

        File[] allfile = file.listFiles();
        for (int i = 0; i < allfile.length; i++) {
            if (allfile[i].isDirectory()) {
                ArrayList<Class<?>> list1 = new ArrayList<>();
                list1 = this.FindAllClass(allfile[i].getAbsolutePath(), path2);
                list.addAll(list1);

            } else if (allfile[i].getName().endsWith(".class")) {
                String allpath = allfile[i].getPath();
                allpath = allpath.replace("\\", "/");
                int debutpath2 = allpath.indexOf(path2);
                int debutpathcouper = debutpath2 + path2.length();

                String className = allpath.substring(debutpathcouper);
                className = className.replace(".class", "");
                className = className.replace("/", ".");

                Class<?> classe = Class.forName(className);
                list.add(classe);

            }

        }
        return list;
    }

}
