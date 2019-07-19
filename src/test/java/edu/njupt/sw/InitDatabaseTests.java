package edu.njupt.sw;

import edu.njupt.sw.dao.UserDAO;
import edu.njupt.sw.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
//@Sql("/init-schema.sql")
@Sql
public class InitDatabaseTests {

    @Autowired
    UserDAO userDAO;

    @Test
    public void contexLoad(){
        Random random = new Random();

        for(int i=0;i<11;++i){
            User user = new User();

            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);

        }

    }
}
