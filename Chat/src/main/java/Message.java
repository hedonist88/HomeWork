import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String uid;
    private String nick;
    private String message;
    private LocalDateTime timeStamp;
    private LocalDateTime serverTimeStamp;

    public Message() {}

    public Message(String uid, String nick, String message, LocalDateTime timeStamp) {
        this.uid = uid;
        this.nick = nick;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getServerTimeStamp() {
        return serverTimeStamp;
    }

    public void setServerTimeStamp(LocalDateTime serverTimeStamp) {
        this.serverTimeStamp = serverTimeStamp;
    }

}
