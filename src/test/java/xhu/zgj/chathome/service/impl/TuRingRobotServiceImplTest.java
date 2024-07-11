package xhu.zgj.chathome.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class TuRingRobotServiceImplTest {

    @Resource
    private TuRingRobotServiceImpl tuRingRobotService;

    @Test
    public void sendMessage() {
        System.out.println(tuRingRobotService.sendMessage("123456", "你长啥样"));
    }
}