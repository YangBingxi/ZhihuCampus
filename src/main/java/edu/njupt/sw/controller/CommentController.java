package edu.njupt.sw.controller;

import edu.njupt.sw.model.Comment;
import edu.njupt.sw.model.EntityType;
import edu.njupt.sw.model.HostHolder;
import edu.njupt.sw.service.CommentService;
import edu.njupt.sw.service.QuestionService;
import edu.njupt.sw.service.SensitiveService;
import edu.njupt.sw.service.UserService;
import edu.njupt.sw.util.CampusZhiHuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;

@Controller //入口
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired   //自动装配
            HostHolder hostHolder;

    @Autowired   //自动装配
            UserService userService;

    @Autowired   //自动装配
            CommentService commentService;

    @Autowired   //自动装配
            QuestionService questionService;

    @Autowired   //自动装配
            SensitiveService sensitiveService;

    /**
     * 给问题添加评论
     *
     * @param questionId
     * @param content
     * @return
     */
    @RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
    public String addComment(@RequestParam("questionId") int questionId,
                             @RequestParam("content") String content) {
        try {
            content = HtmlUtils.htmlEscape(content);
            content = sensitiveService.filter(content);
            // 过滤content
            Comment comment = new Comment();
            if (hostHolder.getUser() != null) { //已登录
                comment.setUserId(hostHolder.getUser().getId());
            } else {    //未登录
                comment.setUserId(CampusZhiHuUtil.ANONYMOUS_USERID);
            }
            comment.setContent(content);
            comment.setEntityId(questionId);
            comment.setEntityType(EntityType.ENTITY_QUESTION);
            comment.setCreatedDate(new Date());
            comment.setStatus(0);

            commentService.addComment(comment);
            // 更新题目里的评论数量
            int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
            questionService.updateCommentCount(comment.getEntityId(), count);
            // 怎么异步化
        } catch (Exception e) {
            logger.error("增加评论失败" + e.getMessage());
        }
        return "redirect:/question/" + questionId;
    }
}
