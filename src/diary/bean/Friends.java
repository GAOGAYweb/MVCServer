package diary.bean;

import javax.persistence.*;

/**
 * Created by Sunine on 2017/6/9.
 */
@Entity
@Table(name="friends")
public class Friends {
    private int id;
    private String groupName;
    private Integer ownerId;
    private String friends;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "group_name", nullable = true, length = 100)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "owner_id", nullable = true)
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "friends", nullable = true, length = 200)
    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friends friends1 = (Friends) o;

        if (id != friends1.id) return false;
        if (groupName != null ? !groupName.equals(friends1.groupName) : friends1.groupName != null) return false;
        if (ownerId != null ? !ownerId.equals(friends1.ownerId) : friends1.ownerId != null) return false;
        if (friends != null ? !friends.equals(friends1.friends) : friends1.friends != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        return result;
    }
}
