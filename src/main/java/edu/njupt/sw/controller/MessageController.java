package edu.njupt.sw.controller;

import edu.njupt.sw.model.HostHolder;
import edu.njupt.sw.model.Message;
import edu.njupt.sw.model.User;
import edu.njupt.sw.model.ViewObject;
import edu.njupt.sw.service.MessageService;
import edu.njupt.sw.service.UserService;
import edu.njupt.sw.util.CampusZhiHuUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller //入口
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired  //自动装配
            MessageService messageService;

    @Autowired  //自动装配
            UserService userService;

    @Autowired  //自动装配
            HostHolder hostHolder;

    /**
     * 获取用户的站内信
     * 使用mysql的group by语句，注意mysql8.* 的语句兼容问题
     * 解决办法见项目根目录的常见问题
     *
     * @param model
     * @return
     */
    @RequestMapping(path = {"/msg/list"}, method = {RequestMethod.GET})
    public String getConversationList(Model model) {
        try {
            int localUserId = hostHolder.getUser().getId();
            System.out.println(localUserId);
            List<ViewObject> conversations = new ArrayList<>();
            List<Message> conversationList = messageService.getConversationList(localUserId, 0, 10);
            for (Message messageList : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("conversation", messageList);
                int targetId = messageList.getFromId() == localUserId ? messageList.getToId() : messageList.getFromId();
                User user = userService.getUser(targetId);
                vo.set("user", user);
                vo.set("unread", messageService.getConversationUnreadCount(localUserId, messageList.getConversationId()));
                conversations.add(vo);
            }
            model.addAttribute("conversations", conversations);
        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
        }
        return "letter";
    }

    /**
     * 查看站内信的详细信息
     *
     * @param model
     * @param conversationId
     * @return
     */
    @RequestMapping(path = {"/msg/detail"}, method = {RequestMethod.GET})
    public String getconversationDetail(Model model, @Param("conversationId") String conversationId) {
        try {
            List<Message> messageList = messageService.getConversationDetail(conversationId, 0, 10);
            List<ViewObject> messages = new ArrayList<>();
            for (Message message : messageList) {
                ViewObject vo = new ViewObject();
                vo.set("message", message);
                User user = userService.getUser(message.getFromId());
                if (user == null) {
                    continue;
                }
                vo.set("headUrl", user.getHeadUrl());
                vo.set("userId", user.getId());
                messages.add(vo);
            }
            model.addAttribute("messages", messages);
        } catch (Exception e) {
            logger.error("获取详情消息失败" + e.getMessage());
        }
        return "letterDetail";
    }

    /**
     * 发送一个站内信
     *
     * @param toName
     * @param content
     * @return
     */
    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content") String content) {
        try {
            if (hostHolder.getUser() == null) {
                return CampusZhiHuUtil.getJSONString(999, "未登录");
            }
            User user = userService.selectByName(toName);
            if (user == null) {
                return CampusZhiHuUtil.getJSONString(1, "用户不存在");
            }

            Message msg = new Message();
            msg.setContent(content);
            msg.setFromId(hostHolder.getUser().getId());
            msg.setToId(user.getId());
            msg.setCreatedDate(new Date());
            messageService.addMessage(msg);
            return CampusZhiHuUtil.getJSONString(0);
        } catch (Exception e) {
            logger.error("增加站内信失败" + e.getMessage());
            return CampusZhiHuUtil.getJSONString(1, "插入站内信失败");
        }
    }

    /**
     * 添加评论
     *
     * @param fromId
     * @param toId
     * @param content
     * @return
     */
    @RequestMapping(path = {"/msg/jsonAddMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("fromId") int fromId,
                             @RequestParam("toId") int toId,
                             @RequestParam("content") String content) {
        try {
            Message msg = new Message();
            msg.setContent(content);
            msg.setFromId(fromId);
            msg.setToId(toId);
            msg.setCreatedDate(new Date());
            //msg.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId) : String.format("%d_%d", toId, fromId));
            messageService.addMessage(msg);
            return CampusZhiHuUtil.getJSONString(msg.getId());
        } catch (Exception e) {
            logger.error("增加评论失败" + e.getMessage());
            return CampusZhiHuUtil.getJSONString(1, "插入评论失败");
        }
    }
}
