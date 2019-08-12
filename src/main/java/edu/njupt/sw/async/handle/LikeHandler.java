package edu.njupt.sw.async.handle;

import edu.njupt.sw.async.EventHandler;
import edu.njupt.sw.async.EventModel;
import edu.njupt.sw.async.EventType;
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


@Component  //实例化
public class LikeHandler implements EventHandler {
    @Autowired   //自动装配
    MessageService messageService;

    @Autowired   //自动装配
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(CampusZhiHuUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());
        message.setContent("用户" + user.getName()
                + "赞了你的评论,http://127.0.0.1:8080/question/" + model.getExt("questionId"));

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
