package diary.bean;

import javax.persistence.*;

/**
 * Created by Sunine on 2017/6/4.
 */
@Entity
@Table(name="comments")
public class Comments {
    private int id;
    private String nickname;
    private String content;
    private Integer momentId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 100)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "moment_id", nullable = true)
    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (id != comments.id) return false;
        if (nickname != null ? !nickname.equals(comments.nickname) : comments.nickname != null) return false;
        if (content != null ? !content.equals(comments.content) : comments.content != null) return false;
        if (momentId != null ? !momentId.equals(comments.momentId) : comments.momentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (momentId != null ? momentId.hashCode() : 0);
        return result;
    }
}
