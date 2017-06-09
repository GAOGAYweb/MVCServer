package diary.dao;

import diary.bean.Friends;
import diary.bean.User;
import org.hibernate.*;

import javax.annotation.Resource;
import java.util.List;

public class UserDao{

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    public void addUser(User user) {
        this.getSession().save(user);
    }
    public void updateUser(User user){this.getSession().saveOrUpdate(user);}
    public SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public User findUserByAccount(String account) {
        String hql = "from User u where u.account=?";
        Query query = getSession().createQuery(hql);// 本地SQL检索方式
        query.setString(0, account);
        return (User)query.uniqueResult();
    }

    public User login(String account, String password) {
        String hql = "from User u where u.account=? and u.password=?";
        Query query = getSession().createQuery(hql);// 本地SQL检索方式
        query.setString(0, account);
        query.setString(1, password);
        return (User)query.uniqueResult();
    }

    public User findUserById(String userId){
        String hql = "from User u where u.id=?";
        Query query = getSession().createQuery(hql);// 本地SQL检索方式
        query.setInteger(0, Integer.parseInt(userId));
        return (User)query.uniqueResult();
    }

}
