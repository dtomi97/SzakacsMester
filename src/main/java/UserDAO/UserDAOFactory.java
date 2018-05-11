package UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAOFactory {

    private static UserDAOFactory instance;

    private static EntityManager em;
    private static EntityManagerFactory f;


    static {
        instance = new UserDAOFactory();
        f = Persistence.createEntityManagerFactory("USERUNIT");
        em = f.createEntityManager();
    }


    private UserDAOFactory(){ }

    public static UserDAOFactory getInstance(){
        return instance;
    }

    public UserDAO createUserDAO(){
        return new UserDAOImpl(em);
    }

    public void close(){
        em.close();
        f.close();
    }
}
