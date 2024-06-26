package cn.example.tcp;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 教程 ： http://www.freemarker.net/
 */
public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        String path = "D:\\devCode\\demo-study\\java-mvn\\src\\main\\resources";
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDirectoryForTemplateLoading(new File(path));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("demo.ftl");
        Map<String, String> dataModel = new HashMap<>();
        dataModel.put("hello", "this is my first FreeMarker test.");
        Writer out = new FileWriter(new File(path + "/hello.c"));
        template.process(dataModel, out);
        out.close();
    }
}