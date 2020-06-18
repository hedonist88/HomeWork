import config.loader.ConfigLoader;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

public class Client {

    private static final Properties CONFIG = ConfigLoader.getConfig("resources/client_config.properties").getProps();
    private String device;
    private String number;
    private String nick;
    private String uid;
    private Socket clientSocket;

    public static void main(String[] args) {
        new Client().start();
    }

    /**
     * Set client data, start Client Sender,Receiver threads
     */
    private void start(){
        clientSocket = getSocket();
        /* Set test client data */
        this.setNick(Randomizer.getNickName());
        this.setDevice(Randomizer.getDevice());
        this.setNumber(Randomizer.getNumber());

        System.out.println(this);

        if(clientSocket != null) {
            new Thread(new Sender(clientSocket, this)).start();
            new Thread(new Receiver(clientSocket)).start();
        } else {
            System.out.println("Server is not responding");
        }
    }

    /**
     * incoming messages, print to console
     */
    private static class Receiver extends Worker{
        private ObjectInputStream in;
        private Socket socket;

        public Receiver(Socket socket) {
            this.socket = socket;
        }

        @Override
        protected void init() {
            try {
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void stop() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void loop() {
            Message incoming = null;
            try {
                incoming = (Message) in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("-> " + incoming.getServerTimeStamp() + "\n" + incoming.getNick()
                    + ": " + incoming.getMessage());
        }
    }

    /**
     *
     * outgoing messages from console, send Message object (uid, nickname, message)
     */
    private static class Sender extends Worker {
        private ObjectOutputStream out;
        private Socket socket;
        private Client client;
        private Scanner scanner = new Scanner(System.in);;

        public Sender(Socket socket, Client client) {
            this.socket = socket;
            this.client = client;
        }

        @Override
        protected void init() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(socket.isConnected() && !socket.isClosed() && socket.isBound()){
                // ping for wellcome
                sendMessage(out, client, "//ping");
            } else {
                System.out.println("Server is not responding");
            }
        }

        @Override
        protected void stop() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void loop() {
            String message = scanner.nextLine();
            if(!"".equals(message)){
                if(socket.isConnected() && !socket.isClosed() && socket.isBound()){
                    sendMessage(out, client, message);
                } else {
                    System.out.println("Server is not responding");
                }
            }
        }

    }

    /**
     * Get client socket with client CONFIG params
     * @return client socket
     */
    private static Socket getSocket() {
        Socket socket = new Socket();
        System.out.println("Client connect to " + CONFIG.getProperty("host") + ":" +
                Integer.parseInt(CONFIG.getProperty("port")));
        try {
            socket.connect(new InetSocketAddress(
                    CONFIG.getProperty("host"),
                    Integer.parseInt(CONFIG.getProperty("port"))
            ));
        } catch (IOException e) {
            System.out.println("Connection failed");
            socket = null;
            e.printStackTrace();
        }
        return socket;
    }

    /**
     * Get client UID
     * MD5 hash from phone number and simple device id
     * @return string uid
     */
    private static String getUID(Client client){
         StringBuilder string = new StringBuilder();
         string.append(client.getNumber()).append(client.getDevice());
         return DigestUtils.md5Hex(string.toString()).toUpperCase();
    }

    /**
     * Send current message to server
     */
    private static void sendMessage(ObjectOutputStream out, Client client, String message){
        Message outgoing = new Message();
        outgoing.setUid(getUID(client));
        outgoing.setNick(client.getNick());
        outgoing.setTimeStamp(LocalDateTime.now());
        outgoing.setMessage(message);
        try {
            out.writeObject(outgoing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Client{" +
                "device='" + device + '\'' +
                ", number='" + number + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
