package com.zabello;

import com.zabello.dao.UserHibernateDao;
import com.zabello.dao.UserXmlDao;
import com.zabello.generated.User;
import com.zabello.service.UserServiceImpl;
import com.zabello.utils.AppConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class AppTest 
{
    @Autowired
    private UserXmlDao xmlDao;
    @Autowired
    private UserHibernateDao hibernateDao;

    @Autowired
    @Qualifier("service")
    private UserServiceImpl service;

    private static final String FILE_NAME = "E:/вучоба/РИС/labs/lab8/user.xml";

    @Test
    public void addUsersToDb(){
        int dbSizeBefore = hibernateDao.readUsers().size();

        service.createUsersInDb(FILE_NAME);

        int dbSizeAfter = hibernateDao.readUsers().size();

        System.out.println(dbSizeBefore +" "+ dbSizeAfter);
        assertNotEquals(dbSizeAfter, dbSizeBefore);
    }

    @Test
    public void addUserInDbById(){
        service.createUserInDbById(FILE_NAME, 1);

        assertEquals(1, hibernateDao.readUsers().size());
    }

    @Test
    public void updateUserInDbById() {
        service.createUsersInDb(FILE_NAME);

        User constantUser = hibernateDao.readUserById(1);

        List<User> users = xmlDao.doUnmarsh(FILE_NAME).getUser();

        users.get(0).setFirstName("nnnlndkndr");

        xmlDao.doMarsh(FILE_NAME, users);

        service.updateUserInDb(FILE_NAME, 1);

        User updatedUser = hibernateDao.readUserById(1);

        assertNotEquals(constantUser, updatedUser);
    }

    @Test
    public void deleteUserFromDb(){
        service.createUsersInDb(FILE_NAME);

        int sizeBefore = hibernateDao.readUsers().size();

        List<User> users = xmlDao.doUnmarsh(FILE_NAME).getUser();

        users.remove(0);

        xmlDao.doMarsh(FILE_NAME, users);

        service.deleteUserFromDb(FILE_NAME);

        int sizeAfter = hibernateDao.readUsers().size();

        System.out.println(sizeBefore + " " +sizeAfter);
        assertNotEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void fromDbTOFile(){
        service.createUsersInDb(FILE_NAME);

        List<User>empty = Collections.emptyList();

        xmlDao.doMarsh(FILE_NAME, empty);

        int sizeBefore = xmlDao.doUnmarsh(FILE_NAME).getUser().size();

        service.addUsersToFile(FILE_NAME);

        int sizeAfter = xmlDao.doUnmarsh(FILE_NAME).getUser().size();

        assertNotEquals(sizeBefore, sizeAfter);
    }
}
