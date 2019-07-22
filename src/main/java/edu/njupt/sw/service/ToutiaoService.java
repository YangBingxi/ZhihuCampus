package edu.njupt.sw.service;

import org.springframework.stereotype.Service;

@Service
public class ToutiaoService {
    public static String getMessage(int userId) {
        return "Hello Message:" + userId;
    }
}
