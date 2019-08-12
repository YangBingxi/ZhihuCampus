package edu.njupt.sw.controller;

import edu.njupt.sw.async.EventModel;
import edu.njupt.sw.async.EventProducer;
import edu.njupt.sw.async.EventType;
import edu.njupt.sw.model.Comment;
import edu.njupt.sw.model.EntityType;
import edu.njupt.sw.model.HostHolder;
import edu.njupt.sw.service.CommentService;
import edu.njupt.sw.service.LikeService;
import edu.njupt.sw.util.CampusZhiHuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller //入口
public class LikeController {
    @Autowired  //自动装配
    LikeService likeService;

    @Autowired  //自动装配
    HostHolder hostHolder;

    @Autowired  //自动装配
    CommentService commentService;

    @Autowired  //自动装配
    EventProducer eventProducer;

    /**
     * 对评论点赞
     * @param commentId
     * @return
     */
    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return CampusZhiHuUtil.getJSONString(999); //表示未登录
        }

        Comment comment = commentService.getCommentById(commentId);

        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(hostHolder.getUser().getId()).setEntityId(commentId)
                .setEntityType(EntityType.ENTITY_COMMENT).setEntityOwnerId(comment.getUserId())
                .setExt("questionId", String.valueOf(comment.getEntityId())));

        long likeCount = likeService.like(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
        return CampusZhiHuUtil.getJSONString(0, String.valueOf(likeCount));
    }

    /**
     * 对评论点踩
     * @param commentId
     * @return
     */
    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return CampusZhiHuUtil.getJSONString(999);
        }

        long likeCount = likeService.disLike(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
        return CampusZhiHuUtil.getJSONString(0, String.valueOf(likeCount));
    }
}
