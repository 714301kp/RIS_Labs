package com.zabello.service;


import com.zabello.dao.UserHibernateDao;
import com.zabello.dao.UserXmlDao;
import com.zabello.generated.User;
import com.zabello.generated.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("service")
public class UserServiceImpl{

    @Autowired
    private UserHibernateDao hibernateDao;


    @Autowired
    private UserXmlDao xmlDao;

    public UserServiceImpl() {
    }


    public void createUsersInDb(String fileName) {

        Users users = xmlDao.doUnmarsh(fileName);

        hibernateDao.createUsers(users.getUser());
    }


    public void createUserInDbById(String fileName, long id) {
        Users users = xmlDao.doUnmarsh(fileName);
        for (User user : users.getUser()) {
            if (user.getId() == id)
                hibernateDao.createUser(user);
        }
    }

    public void updateUserInDb(String fileName, long id) {
        Users users = xmlDao.doUnmarsh(fileName);
        for (User user : users.getUser()) {
            if (user.getId() == id)
                hibernateDao.updateUser(user);
        }
    }

    public void deleteUserFromDb(String fileName) {
        List<User> usersFromFile = xmlDao.doUnmarsh(fileName).getUser();
        List<User> usersFromDb = hibernateDao.readUsers();

        for(User user: usersFromDb){
            hibernateDao.deleteUser(user.getId());
        }

        for (User user: usersFromFile){
            hibernateDao.createUser(user);
        }
    }

    public void addUsersToFile(String fileName) {

        List<User> users = hibernateDao.readUsers();

        xmlDao.doMarsh(fileName, users);
    }
}
