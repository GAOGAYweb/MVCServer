package diary.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sunine on 2017/6/7.
 */
@Entity
@Table(name="message")
public class Message {
    private int id;
    private String fromName;
    private String toName;
    private String content;
    private Date time;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fromName", nullable = true, length = 100)
    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    @Basic
    @Column(name = "toName", nullable = true, length = 100)
    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
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
    @Column(name = "time", nullable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (fromName != null ? !fromName.equals(message.fromName) : message.fromName != null) return false;
        if (toName != null ? !toName.equals(message.toName) : message.toName != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (time != null ? !time.equals(message.time) : message.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fromName != null ? fromName.hashCode() : 0);
        result = 31 * result + (toName != null ? toName.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
