package xhu.zgj.chathome.domain.vo;

import xhu.zgj.chathome.domain.mo.User;
import xhu.zgj.chathome.enums.MessageTypeEnum;
import lombok.*;

import java.util.List;

/**
 * 聊天室动态消息
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DynamicMsgVo extends MessageVO {

    /**
     * 在线人数
     */
    private int onlineCount;

    /**
     * 在线用户列表
     */
    private List<User> onlineUserList;

    @Override
    public MessageTypeEnum getType() {
        return MessageTypeEnum.SYSTEM;
    }
}
