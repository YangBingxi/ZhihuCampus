package edu.njupt.sw.service;

import edu.njupt.sw.dao.MysqlOperation;
import edu.njupt.sw.dao.QuestionDAO;
import edu.njupt.sw.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    //@Autowired
    QuestionDAO questionDAO;
/*
    public List<Question> getLatestQuestions(int userID, int offset, int limit) throws Exception {
        // return questionDAO.selectLatestQuestions(userID, offset, limit);
        //return MysqlOperation.selectLatestQuestions(userID, offset, limit);
    }

 */
}
