package UserDAO;

import model.Users;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    private EntityManager em;

    UserDAOImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public void createUser(String username, String password, Long szint, Long userExp) {
        em.getTransaction().begin();

        Users user = new Users(username, password, szint, userExp);

        em.persist(user);

        em.getTransaction().commit();
    }

    @Override
    public void updateUserExp(Users user, Long userExp) {
        em.getTransaction().begin();
        user.setUserTP(userExp);
        em.getTransaction().commit();
    }

    @Override
    public void updateUserlevel(Users user, Long szint) {
        em.getTransaction().begin();
        user.setSzint(szint);
        em.getTransaction().commit();
    }

    @Override
    public List<Users> find(String username) {

        TypedQuery<Users> q = em.createQuery("SELECT u FROM Users u WHERE u.username='" + username + "'", Users.class);

        return q.getResultList();

    }
}
