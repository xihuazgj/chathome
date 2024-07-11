package xhu.zgj.chathome.controller;

import xhu.zgj.chathome.domain.vo.ResponseVO;
import xhu.zgj.chathome.service.UploadService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 上传文件
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping("/image")
    public ResponseVO uploadImage(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", uploadService.uploadImage(multipartFile));

        return new ResponseVO(jsonObject);
    }
}
