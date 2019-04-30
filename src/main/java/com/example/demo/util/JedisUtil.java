package com.example.demo.util;


import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@Component
public class JedisUtil {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    private Jedis jedis = new Jedis(host, port);

    /**
     * 判断是否登录时使用
     * 当登录时，判断返回值是否为true，为false表示未登录过，为true表示已登录
     *
     * @param username
     * @return
     */
    public boolean isLogin(String username, int roleId) {
        boolean isLogin = jedis.setnx(username, "" + roleId) > 0;
        //设置登录后的key的超时时常，每次请求后端都会重新计时，单位秒
        expire(username, 60 * 30);
        return !isLogin;
    }

    public void expire(String username, int seconds) {
        jedis.expire(username, seconds);
    }

}
