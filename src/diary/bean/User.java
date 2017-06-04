package diary.bean;

import javax.persistence.*;

/**
 * Created by Sunine on 2017/6/4.
 */
@Entity
@Table(name="user")
public class User {
    private long id;
    private String account;
    private String password;
    private String friends;
    private String nickName;
    private Integer gender;
    private String description;
    private String imageSrc;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account", nullable = false, length = 100)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "friends", nullable = true, length = 100)
    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    @Basic
    @Column(name = "nickName", nullable = true, length = 100)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "imageSrc", nullable = true, length = 100)
    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (friends != null ? !friends.equals(user.friends) : user.friends != null) return false;
        if (nickName != null ? !nickName.equals(user.nickName) : user.nickName != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (imageSrc != null ? !imageSrc.equals(user.imageSrc) : user.imageSrc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageSrc != null ? imageSrc.hashCode() : 0);
        return result;
    }
}
