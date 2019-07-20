package edu.njupt.sw;

import edu.njupt.sw.dao.QuestionDAO;
import edu.njupt.sw.dao.UserDAO;
import edu.njupt.sw.model.Question;
import edu.njupt.sw.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
//@Sql("/init-schema.sql")

public class InitDatabaseTests {

  //  @Autowired
    UserDAO userDAO;

   // @Autowired
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


//            user.setPassword("xx5252");
//            userDAO.updatePassword(user);
        }

//        Assert.assertEquals("xx", userDAO.selectByID(1).getPassword());
//        userDAO.deleteByID(1);
//        Assert.assertNull(userDAO.selectByID(1));
    }

    @Test
    public void QuestionTest(){

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

        questionDAO.addQue();
    }
}
