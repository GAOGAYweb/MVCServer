package diary.dao;

import diary.bean.Friends;
import diary.bean.Moments;
import diary.bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void updateMoments(Moments m) {
        this.getSession().saveOrUpdate(m);
    }

    public void addMoments(Moments m) {
        this.getSession().save(m);
    }

    public void deleteMoments(Moments m){this.getSession().delete(m);}

    public String[] queryAllLikes(String likes) {
        String[] allLikes = likes.split(",");
        String hql = "from User u where";
        String temp = "";
        for (String s : allLikes) {
            temp += (" or id=" + s);
        }
        hql += temp.substring(3, temp.length());
        Query query = getSession().createQuery(hql);
        System.out.println(hql);
        List<User> list = query.list();
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getNickName();
        }
        return result;
    }

    public List<Moments> findMomengsByOwner(String id) {
        String hql = "from Moments m where m.ownerId=" + id + " order by m.time desc ";
        Query query = getSession().createQuery(hql);
        List<Moments> list = query.list();
        return list;
    }

    public void update(Moments m) {
        getSession().saveOrUpdate(m);
    }

    public Moments findMomentsById(String id) {
        String hql = "from Moments m where m.id=?";
        Query query = getSession().createQuery(hql);// 本地SQL检索方式
        query.setInteger(0, Integer.parseInt(id));
        return (Moments) query.uniqueResult();
    }

    public List<Moments> listNewestMoments(int count) {
        String hql = "from Moments m order by time desc";
        Query query = getSession().createQuery(hql);
        query.setFirstResult(0 + count * 10);
        query.setMaxResults(10 + count * 10);
        List<Moments> list = query.list();
        return list;
    }

    public List<Moments> listFriendsMoments(int count, int userid) {
        String hql = "from Friends f where f.ownerId=" + userid;
        Query query = getSession().createQuery(hql);
        List<Friends> flist=query.list();
        String[] friends = new String[0];
        for(Friends f:flist){
            if(f.getFriends().length()==0)continue;
            friends=getMergeArray(friends,f.getFriends().split(","));
        }
        String hql2 = "from Moments m where";
        String temp = "";
        for (String s : friends) {
            temp += (" or owner_id=" + s);
        }
        hql2 += temp.substring(3, temp.length());
        hql2 += " order by time desc";
        System.out.println(hql2);
        Query query2 = getSession().createQuery(hql2);
        query2.setFirstResult(0 + count * 10);
        query2.setMaxResults(10 + count * 10);
        List<Moments> list = query2.list();
        return list;
    }

    public List<Moments> queryMomentsAdvice(String id) {
        String hql="from Moments m where m.ownerId='"+id+"' order by time desc ";
        Query query=getSession().createQuery(hql);
        query.setMaxResults(1);
        Moments m= (Moments) query.list().get(0);
        System.out.println(m.getId());
        String hql2 = "from Moments m where m.ownerId!='"+id+"' order by ABS("+m.getEmotion()+"-m.emotion) ,likeCount desc,time desc";
        Query query2=getSession().createQuery(hql2);
        query2.setMaxResults(5);
        return query2.list();
    }
    public String[] getMergeArray(String[] al,String[] bl) {
        String[] a = al;
        String[] b = bl;
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
