package xhu.zgj.chathome.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 撤消消息
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RevokeMsgVo extends MessageVO {

    /**
     * 撤回的消息id
     */
    private String revokeMessageId;
}
