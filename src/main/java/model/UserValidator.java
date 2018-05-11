package model;

import java.util.List;
import UserDAO.UserDAO;

public class UserValidator {

    private  UserDAO ud;

    public UserValidator(UserDAO ud) {
        this.ud = ud;
    }

    public UserValidator() {
    }

    public boolean loginValidate(String username, String password){
        List<Users> usersLista = ud.find(username);

        return !usersLista.isEmpty() && password.equals(usersLista.get(0).getPassword());
    }

    public boolean regUsernameValidate(String username){
        return username.length()>=6 && username.length() <=16;
    }

    public boolean regUsernameisUnique(String username){
        List<Users> usersLista = ud.find(username);

        return usersLista.isEmpty() && !username.isEmpty();
    }

    public boolean regPasswordValidate(String password){
        if(password.contains("!") || password.contains("$") || password.contains("#") || password.contains("+") || password.contains("-") || password.contains("%"))
            for(int i=0; i<10; i++){
                if(password.contains(String.valueOf(i)))
                    return !password.equals(password.toLowerCase()) && password.length()>=6 && password.length()<=16;
            }
        return false;
    }
}
