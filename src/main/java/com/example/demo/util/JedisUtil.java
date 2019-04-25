package com.example.demo.util;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@Component
public class JedisUtil {

    Jedis jedis=new Jedis("127.0.0.1",6379);

    /**
     * 判断是否登录时使用
     * 当登录时，判断返回值是否为true，为false表示未登录过，为true表示已登录
     * @param username
     * @return
     */
    public boolean isLogin(String username){
        boolean isLogin = jedis.setnx(username, "success")>0;
        //设置登录后的key的超时时常，每次请求后端都会重新计时
        expire(username,5);
        return !isLogin;
    }

    public void expire(String username, int seconds){
        jedis.expire(username,seconds);
    }

}
