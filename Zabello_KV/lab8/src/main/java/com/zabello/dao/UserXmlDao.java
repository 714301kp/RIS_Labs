package com.zabello.dao;

import com.zabello.generated.User;
import com.zabello.generated.Users;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Component
public class UserXmlDao {

    public void doMarsh(String fileName, List<User> users) {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller m = context.createMarshaller();
            m.marshal(new Users(users), new File(fileName));
        } catch (Exception e) {
            System.out.println("Ошибка сохранения данных в XML: \"" + e.getMessage() + "\"");
        }
    }

    public Users doUnmarsh(String fileName) {

        Users users1 = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            users1 = ((Users)unmarshaller.unmarshal(new File(fileName)));
        } catch (Exception e) {
            System.out.println("Ошибка получения данных из XML: \"" + e.getMessage() + "\"");
        }
        return users1;
    }
}
