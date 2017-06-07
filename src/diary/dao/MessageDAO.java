package diary.dao;

import diary.bean.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sunine on 2017/6/7.
 */
public class MessageDAO {
    private SessionFactory sessionFactory;
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){this.sessionFactory=sessionFactory;}
    public SessionFactory getSessionFactory(){return this.sessionFactory;}
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public void add(Message message){this.getSession().save(message);}
    public List<Message> query(String from,String to){
        String hql="from Message m where (m.fromName='"+from+"' and m.toName='"+to+"') or (m.fromName='"+to+"' and m.toName='"+from+"') order by time";
        Query query=getSession().createQuery(hql);
        List<Message> list=query.list();
        return list;
    }
}
