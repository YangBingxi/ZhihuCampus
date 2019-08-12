package edu.njupt.sw.async.handle;

import edu.njupt.sw.async.EventHandler;
import edu.njupt.sw.async.EventModel;
import edu.njupt.sw.async.EventType;
import edu.njupt.sw.model.EntityType;
import edu.njupt.sw.model.Message;
import edu.njupt.sw.model.User;
import edu.njupt.sw.service.MessageService;
import edu.njupt.sw.service.UserService;
import edu.njupt.sw.util.CampusZhiHuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 关注事件
 */
@Component  //实例化
public class FollowHandler implements EventHandler {
    @Autowired  //自动装配
    MessageService messageService;

    @Autowired  //自动装配
    UserService userService;

    @Override   //自动装配
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(CampusZhiHuUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());

        if (model.getEntityType() == EntityType.ENTITY_QUESTION) {
            message.setContent("用户" + user.getName()
                    + "关注了你的问题,http://127.0.0.1:8080/question/" + model.getEntityId());
        } else if (model.getEntityType() == EntityType.ENTITY_USER) {
            message.setContent("用户" + user.getName()
                    + "关注了你,http://127.0.0.1:8080/user/" + model.getActorId());
        }

        messageService.addMessage(message);
    }

    @Override   //自动装配
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.FOLLOW);
    }
}