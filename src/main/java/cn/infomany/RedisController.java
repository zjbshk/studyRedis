package cn.infomany;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam String name, @RequestParam String password) {
        String key = String.format("username:login:fail:count:%s", name);
        String count = null;
        if (redisTemplate.hasKey(key)) {
            count = redisTemplate.opsForValue().get(key).toString();
            if (Integer.parseInt(count) >= 5) {
                Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
                return "有限制了，一分钟登录失败超过了5次,请(" + expire + ")秒后重试";
            }
        }

        if (!"zjb".equals(name) || !"123".equals(password)) {
            count = String.valueOf((count == null ? 0 : Integer.parseInt(count)) + 1);
            redisTemplate.opsForValue().set(key, count, 2, TimeUnit.MINUTES);
            return "登录失败，一分钟登录失败次数为" + count;
        }


        redisTemplate.delete(key);
        return "登录成功啦";
    }
}