package edu.njupt.sw.async.handle;

import edu.njupt.sw.async.EventHandler;
import edu.njupt.sw.async.EventModel;
import edu.njupt.sw.async.EventType;
import edu.njupt.sw.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component  //实例化
public class AddQuestionHandler implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(AddQuestionHandler.class);
    @Autowired  //自动装配
    SearchService searchService;

    @Override   //自动装配
    public void doHandle(EventModel model) {
        try {
            searchService.indexQuestion(model.getEntityId(),
                    model.getExt("title"), model.getExt("content"));
        } catch (Exception e) {
            logger.error("增加题目索引失败");
        }
    }

    @Override   //自动装配
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.ADD_QUESTION);
    }
}
