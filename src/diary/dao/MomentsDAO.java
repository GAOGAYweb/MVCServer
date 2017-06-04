package diary.dao;

import diary.bean.Moments;
import diary.bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sunine on 2017/6/1.
 */
public class MomentsDAO {
    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addMoments(Moments m) {
        this.getSession().save(m);
    }

    public List<Moments> findMomengsByOwner(String id){
        String hql="from Moments m where m.ownerId="+id+" order by m.time desc ";
        Query query=getSession().createQuery(hql);
        List<Moments> list=query.list();
        return list;
    }
    public void update(Moments m){getSession().saveOrUpdate(m);}
    public Moments findMomentsById(String id){
        String hql = "from Moments m where m.id=?";
        Query query = getSession().createQuery(hql);// 本地SQL检索方式
        query.setInteger(0, Integer.parseInt(id));
        return (Moments)query.uniqueResult();
    }
    public List<Moments> listNewestMoments(int count) {
        String hql = "from Moments m order by time desc";
        Query query = getSession().createQuery(hql);
        query.setFirstResult(0+count*10);
        query.setMaxResults(10+count*10);
        List<Moments> list=query.list();
        return list;
    }
    public List<Moments> listFriendsMoments(int count,int userid){
        String hql="from User u where u.id="+userid;
        Query query=getSession().createQuery(hql);
        User u= (User) query.uniqueResult();
        String[] friends=u.getFriends().split(",");
        if(friends.length==0)return null;
        String hql2="from Moments m where";
        String temp="";
        for(String s : friends){
            temp+=(" or owner_id="+s);
        }
        hql2+=temp.substring(3,temp.length());
        hql2+=" order by time desc";
        System.out.println(hql2);
        Query query2=getSession().createQuery(hql2);
        query2.setFirstResult(0+count*10);
        query2.setMaxResults(10+count*10);
        List<Moments> list=query2.list();
        return list;
    }
    public Moments queryMomentsById(int id){
        String hql="from Moments m where m.id="+id;
        Query query=getSession().createQuery(hql);
        Moments m= (Moments) query.uniqueResult();
        return m;
    }
}
