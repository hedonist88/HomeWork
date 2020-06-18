import java.io.ObjectOutputStream;
import java.net.Socket;

public class User {
    private final String uid;
    private final Socket socket;
    private final ObjectOutputStream out;

    public User(String uid, Socket socket, ObjectOutputStream out) {
        this.uid = uid;
        this.socket = socket;
        this.out = out;
    }

    public String getUid() {
        return uid;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
