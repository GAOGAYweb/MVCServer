package diary.dao;

import diary.bean.Friends;
import diary.bean.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Sunine on 2017/6/9.
 */
public class FriendsDAO {
    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addFriends(Friends f){this.getSession().save(f);}

    public void addFriend(String owner,String friend,String groupName){
        Friends f=queryGroup(owner,groupName);
        if(f.getFriends().length()==0){
            f.setFriends(friend);
        }else{
            f.setFriends(f.getFriends()+","+friend);
        }
        getSession().saveOrUpdate(f);
    }

    public void deleteFriends(Friends f){this.getSession().delete(f);}

    public void deleteFriend(String owner,String friend){
        String hql="from Friends f where f.ownerId="+owner+" and f.friends like '%"+friend+"%'";
        Query query=getSession().createQuery(hql);
        Friends f= (Friends) query.uniqueResult();
        String[] temp=f.getFriends().split(",");
        if(temp.length==1){
            f.setFriends("");
        }else{
            ArrayList<String> list=new ArrayList<String>();
            for(String s:temp)list.add(s);
            list.remove(friend);
            String result="";
            for(String s:list)result+=(","+s);
            f.setFriends(result.substring(1,result.length()));
        }
        getSession().saveOrUpdate(f);
    }

    public void moveFriend(String owner,String friend,String groupName){
        deleteFriend(owner,friend);
        addFriend(owner,friend,groupName);
    }

    public boolean checkFriend(String owner,String friend){
        String hql="from Friends f where f.ownerId="+owner+" and f.friends like '%"+friend+"%'";
        Query query=getSession().createQuery(hql);
        Friends f= (Friends) query.uniqueResult();
        if(f==null){
            return false;
        }
        return true;
    }

    public Friends queryGroup(String owner,String name){
        String hql="from Friends f where f.ownerId=? and f.groupName=?";
        Query query=getSession().createQuery(hql);
        query.setString(0,owner);
        query.setString(1,name);
        return (Friends) query.uniqueResult();
    }

    public Map<String,List<User>> queryAllFriends(String owner){
        Map<String,List<User>> result=new HashMap<String,List<User>>();
        String hql="from Friends f where f.ownerId="+owner;
        Query query=getSession().createQuery(hql);
        List<Friends> groups=query.list();
        for(Friends f:groups){
            if(f.getFriends().length()==0)continue;
            String[] friends=f.getFriends().split(",");
            String hql2="from User u where";
            String temp="";
            for(String s : friends){
                temp+=(" or id="+s);
            }
            hql2+=temp.substring(3,temp.length());
            System.out.println(hql2);
            Query query2=getSession().createQuery(hql2);
            List<User> list=query2.list();
            result.put(f.getGroupName(),list);
        }

        return result;
    }

    public List<Friends> queryGroups(String owner){
        String hql="from Friends f where f.ownerId="+owner;
        Query query=getSession().createQuery(hql);
        return query.list();
    }

    public int queryFriendsNum(String id) {
        int count=0;
        String hql = "from Friends f where f.ownerId=" + id;
        Query query = getSession().createQuery(hql);
        List<Friends> flist=query.list();
        for(Friends f:flist){
            if(f.getFriends().length()==0)continue;
            count+=f.getFriends().split(",").length;
        }
        return count;
    }
}
