package com.xr.bgmt.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private Configuration configuration = null;

    public ExcelUtil(){
        freemarker.template.Version version = new freemarker.template.Version("2.3.23");
        configuration = new Configuration(version);
        this.configuration.setDefaultEncoding("utf-8");
    }
    public void print(Map dataMap,String filePath) throws IOException {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        // 要填入模本的数据文件
        configuration.setTemplateLoader(new ClassTemplateLoader(this.getClass().getClassLoader(),"/template"));
        //configuration.setDirectoryForTemplateLoading(new File("/template"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate(dataMap.get("ftl").toString());
            t.setEncoding("utf-8");
        }catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File(filePath);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        }catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            t.process(dataMap, out);
            out.close();
        }catch (TemplateException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)throws IOException {
    }
}
