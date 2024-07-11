package xhu.zgj.chathome.controller;

import xhu.zgj.chathome.domain.vo.ResponseVO;
import xhu.zgj.chathome.service.ChatRecordService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 聊天记录
 */
@RestController
@RequestMapping("/api/record")
public class ChatRecordController {

    @Resource
    private ChatRecordService chatRecordService;

    /**
     * 聊天记录列表
     *
     * @param directoryName 目录名
     * @return ResponseVO
     */
    @GetMapping
    public ResponseVO listChatRecord(@RequestParam(required = false, defaultValue = "") String directoryName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", chatRecordService.listRecord(directoryName));

        return new ResponseVO(jsonObject);
    }
}
