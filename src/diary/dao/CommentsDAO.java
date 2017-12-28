package diary.dao;

import diary.bean.Comments;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sunine on 2017/6/4.
 */
public class CommentsDAO {
    private SessionFactory sessionFactory;
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){this.sessionFactory=sessionFactory;}
    public SessionFactory getSessionFactory(){return this.sessionFactory;}
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void add(Comments c){this.getSession().save(c);}

    public List<Comments> queryComments(String id){
        String hql="from Comments c where c.momentId="+id+" order by c.time";
        Query query=getSession().createQuery(hql);
        List<Comments> list=query.list();
        return list;
    }
    public List<Comments> queryMovieComments(String id){
        String hql="from Comments c where c.movieId="+id+" and c.title!=null order by c.time";
        Query query=getSession().createQuery(hql);
        List<Comments> list=query.list();
        return list;
    }
    public List<Comments> queryShortComments(String id){
        String hql="from Comments c where c.movieId="+id+" and c.title=null order by c.time";
        Query query=getSession().createQuery(hql);
        List<Comments> list=query.list();
        return list;
    }
    public long getCommentsSize(String id){
        String hql="select count(*) from Comments c where c.momentId="+id;
        Query query=getSession().createQuery(hql);
        long size= (long) query.uniqueResult();
        return size;
    }

}
