package xhu.zgj.chathome.utils;

import xhu.zgj.chathome.constant.DateConstant;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class DateUtilsTest {

    @Test
    public void getDate() {
        System.out.println(DateUtils.getDate(DateConstant.SEND_TIME_FORMAT));
        System.out.println(DateUtils.getDate(System.currentTimeMillis(), DateConstant.SEND_TIME_FORMAT));
    }

}