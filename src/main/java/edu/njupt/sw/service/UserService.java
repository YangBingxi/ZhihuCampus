package edu.njupt.sw.service;

import edu.njupt.sw.dao.UserDAO;
import edu.njupt.sw.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    //@Autowired
    private UserDAO userDAO;

    public User getUser(int id) {
        return userDAO.selectByID(id);

    }
}
