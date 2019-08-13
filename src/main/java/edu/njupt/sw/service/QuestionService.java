package edu.njupt.sw.service;

import edu.njupt.sw.dao.QuestionDAO;
import edu.njupt.sw.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


@Service
public class QuestionService {
    @Autowired  //自动装配
    QuestionDAO questionDAO;

    @Autowired  //自动装配
    SensitiveService sensitiveService;

    /**
     * 获取问题
     * @param id
     * @return
     */
    public Question getById(int id) {
        return questionDAO.getById(id);
    }

    /**
     * 添加问题
     * @param question
     * @return
     */
    public int addQuestion(Question question) {
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle())); //过滤html标签
        question.setContent(HtmlUtils.htmlEscape(question.getContent())); //过滤html标签
        // 敏感词过滤
        question.setTitle(sensitiveService.filter(question.getTitle()));
        question.setContent(sensitiveService.filter(question.getContent()));
        return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
    }

    /**
     * 获取最新的问题列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset, limit);
    }

    /**
     * 更新问题
     * @param id
     * @param count
     * @return
     */
    public int updateCommentCount(int id, int count) {
        return questionDAO.updateCommentCount(id, count);
    }
}
