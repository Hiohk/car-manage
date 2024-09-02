package com.hkgroup.sys.utils;

import com.sun.deploy.net.URLEncoder;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class AppFileUtils {
    /**
     * 得到⽂件上传的路径
     */
    public static String PATH = "D:/upload/";

    static {
        InputStream stream =
                AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
            PATH = properties.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ⽂件下载
     *
     * @param response
     * @param path
     * @param oldName
     * @return
     */
    public static ResponseEntity<Object> downloadFile(HttpServletResponse response, String path, String oldName) {
        //4,使⽤绝对路径+相对路径去找到⽂件对象
        File file = new File(AppFileUtils.PATH, path);
        //5,判断⽂件是否存在
        if (file.exists()) {
            try {
                try {
                    //如果名字有中⽂ 要处理编码
                    oldName = URLEncoder.encode(oldName, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //把file转成⼀个bytes
                byte[] bytes = FileUtils.readFileToByteArray(file);
                HttpHeaders header = new HttpHeaders();
                //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)

                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //设置下载的⽂件的名称
                header.setContentDispositionFormData("attachment",
                        oldName);
                //创建ResponseEntity对象
                ResponseEntity<Object> entity =
                        new ResponseEntity<Object>(bytes, header,
                                HttpStatus.CREATED);
                return entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            PrintWriter out;
            try {
                out = response.getWriter();
                out.write("⽂件不存在");
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 根据相对路径删除硬盘上⽂件
     *
     * @param path
     */
    public static void deleteFileUsePath(String path) {
        String realPath = PATH + path;
        //根据⽂件
        File file = new File(realPath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 更改⽂件名
     *
     * @param carimg
     * @param suffix
     */
    public static String updateFileName(String carimg, String suffix) {
        //找到⽂件
        try {
            File file = new File(PATH, carimg);
            if (file.exists()) {
                file.renameTo(new File(PATH, carimg.replace(suffix, "")));
                return carimg.replace(suffix, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据路径删除图⽚
     *
     * @param carimg
     */
    public static void removeFileByPath(String carimg) {
        //找到⽂件
        try {
            File file = new File(PATH, carimg);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
