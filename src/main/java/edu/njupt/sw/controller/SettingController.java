package edu.njupt.sw.controller;

import edu.njupt.sw.service.CampusZhiHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller //入口
public class SettingController {
    @Autowired  //自动装配
            CampusZhiHuService toutiaoService;

    /**
     * 设置界面
     * 暂时还没有内容
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(path = {"/setting"})
    @ResponseBody
    public String setting(HttpSession httpSession) {
        return "Setting OK." + CampusZhiHuService.getMessage(1);
    }
}
