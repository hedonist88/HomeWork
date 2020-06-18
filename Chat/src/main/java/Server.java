import config.loader.ConfigLoader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {

    private static final Properties CONFIG = ConfigLoader.getConfig("resources/config.properties").getProps();
    private Map<String,User> users = new HashMap();
    private final LinkedBlockingQueue<Message> forwardingList = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Server().start();
    }

    /**
     * start Server Reading,Sending threads
     */
    private void start(){
        new Thread(new Sender()).start();
        new Thread(new Receiver()).start();
    }

    /**
     * Server Reader, start server, read new message from clients
     * Create new threads @ClientHandler
     */
    private class Receiver extends Worker {
        private ServerSocket serverSocket;

        @Override
        protected void init() {
            try {
                serverSocket = new ServerSocket(
                        Integer.parseInt(CONFIG.getProperty("port")));
                System.out.println("Server is started" + " " + CONFIG.getProperty("host") + ":"
                    +CONFIG.getProperty("port"));
            } catch (IOException e) {
                Thread.currentThread().interrupt();
                System.out.println("Server not started");
                e.printStackTrace();
            }
        }

        @Override
        protected void stop() {
            System.out.println("Server is stopped");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }

        @Override
        protected void loop() {
            try {
                Socket clientSocket = serverSocket.accept();
                if(clientSocket != null) {
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Server Sender, forwarding message to all users
     */
    private class Sender extends Worker {
        @Override
        protected void init() {}

        @Override
        protected void stop() {
            System.out.println("Sender is stopped");
            Thread.currentThread().interrupt();
        }

        @Override
        protected void loop() {
            try {
                Message message = forwardingList.take();
                users.entrySet().forEach(user -> {
                    if (!user.getKey().equals(message.getUid())){
                        try {
                            user.getValue().getOut().writeObject(message);
                            System.out.println("Message send to " + user.getValue().getUid());
                        } catch (IOException e) {
                            System.out.println("Message not send to " + user.getValue().getUid());
                            users.remove(message.getUid());
                            try {
                                user.getValue().getOut().close();
                                user.getValue().getSocket().close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * ClientHandler, Thread for clients, read message and put in forwardingList
     */
    private class ClientHandler extends Worker {
        Socket clientSocket;
        String uid;
        ObjectInputStream in;
        
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        protected void init() {
            try {
                Message message;
                in = new ObjectInputStream(clientSocket.getInputStream());
                message = ReadMessage(in);
                message.setServerTimeStamp(LocalDateTime.now());
                if(!users.containsKey(message.getUid())) {
                    User user = new User(message.getUid(),
                            clientSocket,
                            new ObjectOutputStream(clientSocket.getOutputStream()));
                    users.put(message.getUid(), user);
                }
                uid = message.getUid();
                System.out.println("-> " + LocalDateTime.now() + "\nWellcome " + message.getNick());
            } catch (IOException e) {
                System.out.println("Message not read");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void stop() throws IOException {
            clientSocket.close();
            in.close();
            users.remove(uid);
        }

        @Override
        protected void loop() {
            Message message = null;
            try {
                message = ReadMessage(in);
            } catch (IOException e) {
                System.out.println("Message not read");
                message = null;
                //e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(message.getMessage() != null){
                message.setServerTimeStamp(LocalDateTime.now());
                try {
                    forwardingList.put(message);
                    System.out.println("-> " + message.getServerTimeStamp() + "\n" + message.getNick()
                            + ": " + message.getMessage());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * read message from input stream
     * @return Message
     */
    private Message ReadMessage(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return (Message) in.readObject();
    }

}
