package xhu.zgj.chathome.service;

import xhu.zgj.chathome.domain.dto.ChatRecordDTO;

import java.util.HashMap;
import java.util.List;

/**
 * 聊天记录
 */
public interface ChatRecordService {

    /**
     * 添加聊天记录
     *
     * @param chatRecordDTO 聊天记录对象
     */
    void addRecord(ChatRecordDTO chatRecordDTO);

    /**
     * 聊天记录列表
     *
     * @param directoryName 目录名
     * @return 聊天记录列表
     */
    List<HashMap<String, Object>> listRecord(String directoryName);
}
