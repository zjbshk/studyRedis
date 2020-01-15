package cn.infomany;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: springbootdemo
 * @Date: 2019/1/25 15:03
 * @Author: Mr.Zheng
 * @Description:
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    /**
     * redis中存储的过期时间60s
     */
    private static int EXPIRE_TIME = 60;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/set")
    public boolean redisSet(String key, String value) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("ZHANGSAN");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());

        return redisUtil.set(key, value);
    }

    @RequestMapping("/get")
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("/expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, EXPIRE_TIME);
    }
}