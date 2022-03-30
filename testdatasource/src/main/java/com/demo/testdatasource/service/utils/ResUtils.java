package com.demo.testdatasource.service.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 17:41
 */
public class ResUtils {
    public static InputStream getResInputStream(String path) {
        if (path == null) {
            return null;
        }
        InputStream inputStream = null;
        if (path.startsWith("classpath")) {
            path = path.replace("classpath:", "/");
            inputStream = ResUtils.class.getResourceAsStream(path);
        } else {
            try {
                inputStream = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    public static InputStream getInputStream( String location) throws IOException {

        if(location.startsWith( ResourceUtils.CLASSPATH_URL_PREFIX )){
            String path = location.substring( ResourceUtils.CLASSPATH_URL_PREFIX.length() );
            ClassPathResource resource = new ClassPathResource( path );
            return resource.getInputStream();
        }else{
            return new FileInputStream( ResourceUtils.getFile( location ) );
        }
    }
}
