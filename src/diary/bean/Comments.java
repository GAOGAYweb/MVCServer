package diary.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by MSI on 2017/12/27.
 */
@Entity
@Table(name="comments")
public class Comments {
    private int id;
    private String nickname;
    private String content;
    private String avatar;
    private Date time;
    private Integer momentId;
    private Integer movieId;
    private String title;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 200)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 20000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 200)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "moment_id", nullable = true)
    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    @Basic
    @Column(name = "movie_id", nullable = true)
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (id != comments.id) return false;
        if (nickname != null ? !nickname.equals(comments.nickname) : comments.nickname != null) return false;
        if (content != null ? !content.equals(comments.content) : comments.content != null) return false;
        if (avatar != null ? !avatar.equals(comments.avatar) : comments.avatar != null) return false;
        if (time != null ? !time.equals(comments.time) : comments.time != null) return false;
        if (momentId != null ? !momentId.equals(comments.momentId) : comments.momentId != null) return false;
        if (movieId != null ? !movieId.equals(comments.movieId) : comments.movieId != null) return false;
        if (title != null ? !title.equals(comments.title) : comments.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (momentId != null ? momentId.hashCode() : 0);
        result = 31 * result + (movieId != null ? movieId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
