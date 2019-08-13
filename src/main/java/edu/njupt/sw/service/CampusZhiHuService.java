package edu.njupt.sw.service;

import org.springframework.stereotype.Service;

@Service
public class CampusZhiHuService {
    /**
     * 获取消息
     * @param userId
     * @return
     */
    public static String getMessage(int userId) {
        return "Hello Message:" + userId;
    }
}
