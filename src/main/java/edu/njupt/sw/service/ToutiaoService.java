package edu.njupt.sw.service;

import org.springframework.stereotype.Service;

@Service
public class ToutiaoService {
    public static String getMessage(int userID) {
        return "Hello Message:" + userID;
    }
}
