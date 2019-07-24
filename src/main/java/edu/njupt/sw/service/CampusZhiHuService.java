package edu.njupt.sw.service;

import org.springframework.stereotype.Service;

@Service
public class CampusZhiHuService {
    public static String getMessage(int userId) {
        return "Hello Message:" + userId;
    }
}
