package cn.infomany;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @description: 学习redis主程序
 * @author: zhanjinbing
 * @data: 2020-01-15 15:01
 */

@SpringBootApplication
@Slf4j
@EnableCaching
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        log.info("<< ================= 主程序启动 =================== >>");
    }
}
