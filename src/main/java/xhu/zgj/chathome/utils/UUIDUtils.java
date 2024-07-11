package xhu.zgj.chathome.utils;

import java.util.UUID;

/**
 * uuid工具类
 */
public class UUIDUtils {

    /**
     * 生成uuid
     *
     * @return
     */
    public static String create() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
