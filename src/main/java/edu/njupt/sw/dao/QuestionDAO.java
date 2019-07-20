package edu.njupt.sw.dao;

import edu.njupt.sw.model.Question;
import edu.njupt.sw.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDAO {
    //注意空格
    String TABLE_NAME = " wenda.question ";
    String INSERT_FIELDS = "title, content, user_id, comment_count";



    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{content},#{userID},#{commentCount})"})
    int addQuestion(Question question);

    @Insert({"insert into question (title, content, user_id, comment_count) values (\"ggg\",\"hhhh\",\"2\",\"5\")"})
    int addQue();

    List<Question> selectLastQuestions(@Param("userID")int userID,
                                       @Param("offset")int offset,
                                       @Param("limit")int limit);

}
