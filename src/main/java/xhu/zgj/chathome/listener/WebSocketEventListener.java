package xhu.zgj.chathome.listener;

import xhu.zgj.chathome.cache.UserCache;
import xhu.zgj.chathome.constant.MessageConstant;
import xhu.zgj.chathome.constant.StompConstant;
import xhu.zgj.chathome.constant.UserStatusConstant;
import xhu.zgj.chathome.domain.mo.User;
import xhu.zgj.chathome.domain.vo.DynamicMsgVo;
import xhu.zgj.chathome.domain.vo.MessageVO;
import xhu.zgj.chathome.enums.CodeEnum;
import xhu.zgj.chathome.exception.ErrorCodeException;
import xhu.zgj.chathome.service.MessageService;
import xhu.zgj.chathome.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import javax.annotation.Resource;

/**
 * websocket事件监听
 */
@Slf4j
@Component
public class WebSocketEventListener {

    @Resource
    private MessageService messageService;

    private User user;

    /**
     * 建立连接监听
     *
     * @param sessionConnectedEvent
     */
    @EventListener
    public void handleConnectListener(SessionConnectedEvent sessionConnectedEvent) throws ErrorCodeException {
        log.debug("建立连接 -> {}", sessionConnectedEvent);

        user = (User) sessionConnectedEvent.getUser();
        if (!CheckUtils.checkUser(user)) {
            throw new ErrorCodeException(CodeEnum.INVALID_PARAMETERS);
        }

        UserCache.addUser(user.getUserId(), user);
    }

    /**
     * 断开连接监听
     *
     * @param sessionDisconnectEvent
     */
    @EventListener
    public void handleDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) throws Exception {
        log.debug("断开连接 -> {}", sessionDisconnectEvent);

        String userId = sessionDisconnectEvent.getUser().getName();
        User user = UserCache.getUser(userId);
        if (null == user) {
            log.debug("用户不存在 uid ->", userId);
            return;
        }

        user.setStatus(UserStatusConstant.OFFLINE);
        UserCache.removeUser(userId);

        // 广播离线消息
        sendMessage(buildMessageVo(user, MessageConstant.OFFLINE_MESSAGE));
        log.debug("广播离线消息 -> {}", user);
    }

    /**
     * 订阅监听
     *
     * @param sessionSubscribeEvent
     */
    @EventListener
    public void handleSubscribeListener(SessionSubscribeEvent sessionSubscribeEvent) throws Exception {
        log.debug("新的订阅 -> {}", sessionSubscribeEvent);
        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(sessionSubscribeEvent.getMessage(),
                StompHeaderAccessor.class);

        if (StompConstant.SUB_STATUS.equals(stompHeaderAccessor.getFirstNativeHeader(StompHeaderAccessor
                .STOMP_DESTINATION_HEADER))) {
            if (user != null) {
                try {
                    // 延迟100ms，防止客户端来不及接收上线消息
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    log.error("中断异常，error -> {}", e);
                }

                // 广播上线消息
                sendMessage(buildMessageVo(user, MessageConstant.ONLINE_MESSAGE));
                // 发送机器人欢迎消息
                sendRobotMessage(String.format(MessageConstant.ROBOT_WELCOME_MESSAGE, user.getUsername()));
                log.debug("广播上线消息 -> {}", user);
            }

        }
    }

    /**
     * 构建消息视图
     *
     * @param user
     * @return
     */
    private MessageVO buildMessageVo(User user, String message) {
        DynamicMsgVo dynamicMsgVo = new DynamicMsgVo();
        dynamicMsgVo.setUser(user);
        dynamicMsgVo.setMessage(String.format(message, user.getUsername()));
        dynamicMsgVo.setOnlineCount(UserCache.getOnlineCount());
        dynamicMsgVo.setOnlineUserList(UserCache.listUser());

        return dynamicMsgVo;
    }

    /**
     * 发送订阅消息，广播用户动态
     *
     * @param messageVO
     */
    private void sendMessage(MessageVO messageVO) throws Exception {
        messageService.sendMessage(StompConstant.SUB_STATUS, messageVO);
    }

    /**
     * 发送机器人消息
     *
     * @param message
     * @throws Exception
     */
    private void sendRobotMessage(String message) throws Exception {
        messageService.sendRobotMessage(StompConstant.SUB_CHAT_ROOM, message);
    }
}