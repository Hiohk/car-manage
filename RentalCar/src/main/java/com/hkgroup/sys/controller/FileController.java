package com.hkgroup.sys.controller;

import com.hkgroup.sys.constant.SysConstant;
import com.hkgroup.sys.utils.AppFileUtils;
import com.hkgroup.sys.utils.DataGridView;
import com.hkgroup.sys.utils.RandomUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileController {
    /**
     * 添加
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public DataGridView uploadFile(MultipartFile mf) throws
            IllegalStateException, IOException {
        // ⽂件上传的⽗⽬录
        String parentPath = AppFileUtils.PATH;
        // 得到当前⽇期作为⽂件夹名称
        String dirName = RandomUtils.getCurrentDateForString();
        // 构造⽂件夹对象
        File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建⽂件夹
        }
        // 得到⽂件原名
        String oldName = mf.getOriginalFilename();
        // 根据⽂件原名得到新名
        String newName
                = RandomUtils.createFileNameUseTime(oldName, SysConstant.FILE_UPLOAD_TEMP);
        File dest = new File(dirFile, newName);
        mf.transferTo(dest);
        Map<String, Object> map = new HashMap<>();
        map.put("src", dirName + "/" + newName);
        return new DataGridView(map);
    }

    /**
     * 不下载只显示
     */
    @RequestMapping("downloadShowFile")
    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) {
        return AppFileUtils.downloadFile(response, path, "");
    }

    /**
     * 下载图⽚
     *
     * @param path
     * @param response
     * @return
     */
    @RequestMapping("downloadFile")
    public ResponseEntity<Object> downloadFile(String path,
                                               HttpServletResponse response) {
        String oldName = "";
        return AppFileUtils.downloadFile(response, path, oldName);
    }
}
