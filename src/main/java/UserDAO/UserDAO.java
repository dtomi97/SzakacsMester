package UserDAO;

import model.Users;

import java.util.List;

public interface UserDAO {

    void createUser(String username, String password, Long szint, Long userExp);
    void updateUserExp(Users user, Long userExp);
    void updateUserlevel(Users user, Long szint);
    List<Users> find(String username);

}
