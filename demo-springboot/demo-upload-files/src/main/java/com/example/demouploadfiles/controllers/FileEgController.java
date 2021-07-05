package com.example.demouploadfiles.controllers;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.demouploadfiles.commons.FileResponse;
import com.example.demouploadfiles.storage.StorageService;
import com.example.demouploadfiles.vo.QueryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件上传
 * 实际使用的例子 测试
 *
 * @author zhangxinqiang
 * @create 2021/7/5 13:39
 */
@Controller
public class FileEgController {

    @Resource
    private StorageService storageService;

    /**
     * 上传文件
     * <p>
     * 除了文件，还需要传递一些附加信息，比如上传人，时间等等，
     * 前端可以放在一个formData中，后端读取
     */
    @PostMapping("/upload-file")
    @ResponseBody
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file, QueryVo vo) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

    /**
     * 文件上传
     * <p>
     * excel 解析
     */
    @PostMapping("/upload")
    @ResponseBody
    public FileResponse upload(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<List<Object>> sheet0 = reader.read();
        print(sheet0);
        reader.setSheet(1);
        List<List<Object>> sheet1 = reader.read();
        print(sheet1);
        reader.setSheet(2);
        List<List<Object>> sheet2 = reader.read();
        print(sheet2);
        reader.setSheet(3);
        List<List<Object>> sheet3 = reader.read();
        print(sheet3);


        return new FileResponse("name", "uri", file.getContentType(), file.getSize());
    }

    public void print(List<List<Object>> sheet) {
        for (int i = 2; i < sheet.size(); i++) {
            List<Object> rows = sheet.get(i);
            for (Object cell : rows) {
                System.out.print(cell);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<String> list() {
        List<String> collect = storageService.loadAll().map(
                path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(path.getFileName().toString())
                        .toUriString())
                .collect(Collectors.toList());
        return collect;
    }
}
