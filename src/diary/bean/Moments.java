package diary.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sunine on 2017/6/5.
 */
@Entity
@Table(name="moments")
public class Moments {
    private long id;
    private long ownerId;
    private double longitude;
    private double latitude;
    private String content;
    private int likeCount;
    private String likes;
    private Date time;
    private String imageSrc;
    private String tag;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner_id", nullable = false)
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "longitude", nullable = false, precision = 0)
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = false, precision = 0)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "like_count", nullable = false)
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "likes", nullable = true, length = 1000)
    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
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
    @Column(name = "imageSrc", nullable = true, length = 100)
    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = 100)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moments moments = (Moments) o;

        if (id != moments.id) return false;
        if (ownerId != moments.ownerId) return false;
        if (Double.compare(moments.longitude, longitude) != 0) return false;
        if (Double.compare(moments.latitude, latitude) != 0) return false;
        if (likeCount != moments.likeCount) return false;
        if (content != null ? !content.equals(moments.content) : moments.content != null) return false;
        if (likes != null ? !likes.equals(moments.likes) : moments.likes != null) return false;
        if (time != null ? !time.equals(moments.time) : moments.time != null) return false;
        if (imageSrc != null ? !imageSrc.equals(moments.imageSrc) : moments.imageSrc != null) return false;
        if (tag != null ? !tag.equals(moments.tag) : moments.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + likeCount;
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (imageSrc != null ? imageSrc.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
