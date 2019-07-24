package edu.njupt.sw;

import edu.njupt.sw.dao.MysqlLink;
import edu.njupt.sw.dao.MysqlOperation;
import edu.njupt.sw.dao.QuestionDAO;
import edu.njupt.sw.dao.UserDAO;
import edu.njupt.sw.model.Question;
import edu.njupt.sw.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CampusZhiHuApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {

    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void contexLoad() {
        Random random = new Random();

        for (int i = 0; i < 11; ++i) {
            User user = new User();

            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");

            userDAO.addUser(user);//不可以重复插入相同用户

            user.setPassword("xx5252");
            userDAO.updatePassword(user);


            Question question = new Question();
            question.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            question.setCreatedDate(date);
            question.setUserId(i + 1);
            question.setTitle(String.format("TITLE{%d}", i));
            question.setContent(String.format("我们都是好孩子 %d", i));
            questionDAO.addQuestion(question);
        }

        //Assert.assertEquals("xx", userDAO.selectByID(1).getPassword());
        userDAO.deleteById(1);
        //Assert.assertNull(userDAO.selectByID(1));
    }

    @Test
    public void QuestionTest() throws Exception {

//        for(int i=0;i<11;++i){
//            Question question = new Question();
//            question.setCommentCount(i);
////            Date date = new Date();
////            date.setTime(date.getTime()+1000*3600*i);
////            question.setCreatedDate(date);
//            question.setUserID(i+1);
//            question.setTitle("title");
//            question.setContent("我们都是有问题的人");
//
//            questionDAO.addQuestion(question);
//        }

        //questionDAO.addQue();

        Connection conn;
        Statement stmt;
        ResultSet rs;

        MysqlLink.loadDriver();
        conn=MysqlLink.getConnection();
        String sql = "insert into question (title, content, user_id, comment_count) values (\"ggg\",\"hhhh\",\"2\",\"5\");";
        stmt=conn.createStatement();
        int i = stmt.executeUpdate(sql);
    }

    @Test
    public void test() throws Exception {
        MysqlOperation mysqlOperation =new MysqlOperation();
        Random random = new Random();
//        for(int i =0;i<11;++i){
//            User user = new User();
//            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
//            user.setName(String.format("USER%d", i));
//            user.setPassword("");
//            user.setSalt("");
//            mysqlOperation.insertUser(user.getName(),user.getPassword(),user.getSalt(),user.getHeadUrl());
//        }
//
        for(int i=0;i<11;++i){
            Question question = new Question();
            question.setTitle(String.format("TITLE%d",i));
            question.setContent("我们都是好孩子");
            question.setUserId(i+1);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            timestamp.setTime(timestamp.getTime()+1000*3600*5*i);
            question.setCreatedDate(timestamp);
            question.setCommentCount(i);

            System.out.println(timestamp);
            mysqlOperation.insertQuestion(question.getTitle(),question.getContent(),question.getUserId(),question.getCreatedDate(),question.getCommentCount());
        }

    }
}
