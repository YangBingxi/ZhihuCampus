package edu.njupt.sw.controller;

import edu.njupt.sw.service.CampusZhiHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SettingController {
    @Autowired
    CampusZhiHuService toutiaoService;

    @RequestMapping(path = {"/setting"})
    @ResponseBody
    public String setting(HttpSession httpSession) {
        return "Setting OK." + CampusZhiHuService.getMessage(1);
    }
}
