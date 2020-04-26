package message;

import java.util.*;

public class MessageTask {

    public static void main(String[] args) {
        List<Message> messageList = new ArrayList<>();
        messageList = MessageGenerator.generate(20);

        System.out.println("\n=== Исходный список сообщений ===");
        for (Message m:messageList){
            System.out.println(m.toString());
        }

        countEachPriority(messageList);

        countEachCode(messageList);

        uniqueMessageCount(messageList);

        System.out.println("\n=== Только неповторяющиеся сообщения в исходном порядке ===");
        for (Message m:uniqueMessagesInOriginalOrder(messageList)){
            System.out.println(m.getCode() + ": " + m.getPriority());
        }

        removeEach(messageList, MessagePriority.MEDIUM);

        removeOther(messageList, MessagePriority.URGENT);

    }

    public static void countEachPriority(List<Message> messageList) {
        // TODO:  Подсчитать количество сообщений для каждого приоритела
        //  Ответ в консоль
        EnumMap<MessagePriority, Integer> counter = new EnumMap<>(MessagePriority.class);
        for(MessagePriority val: MessagePriority.values()){
            counter.put(val, 0);
        }
        for(Message m:messageList){
            counter.put(m.getPriority(), counter.get(m.getPriority()) + 1);
        }
        System.out.println("\n=== Количество сообщений для каждого приоритета ===");
        for (EnumMap.Entry<MessagePriority, Integer> entry:counter.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    public static void countEachCode(List<Message> messageList) {
        // TODO: Подсчитать количество сообщений для каждого кода сообщения
        //  Ответ в консоль
        HashMap<Integer, Integer> counter = new HashMap<>();
        for(Message m:messageList){
            try {
                counter.put(m.getCode(), counter.get(m.getCode()) + 1);
            } catch (NullPointerException e){
                counter.put(m.getCode(), 0);
                counter.put(m.getCode(), counter.get(m.getCode()) + 1);
            }
        }
        System.out.println("\n=== Количество сообщений для кода сообщения ===");
        for (Map.Entry<Integer, Integer> entry:counter.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void uniqueMessageCount(List<Message> messageList) {
        // TODO: Подсчитать количество уникальных сообщений
        //  Ответ в консоль
        Set<Message> result = new HashSet<>();
        for(Message m:messageList){
            if(!result.contains(m)) {
                result.add(m);
            }
        }
        System.out.println("\n=== Количество уникальных сообщений ===");
        System.out.println("Всего " + result.size() + " уникальных сообщений:");
        for (Message m:result){
            System.out.println(m.getCode() + ": " + m.getPriority());
        }
    }

    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList){
        // TODO: вернуть только неповторяющиеся сообщения и в том порядке,
        //  в котором они встретились в первоначальном списке
        //  Например, было: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}]
        //  на выходе: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}]
        Set<Message> checker = new HashSet<>();
        LinkedList<Message> result = new LinkedList<>();
        for(Message m:messageList){
            if(!checker.contains(m)) {
                checker.add(m);
                result.add(m);
            }
        }
        return result;
    }

    public static void removeEach(List<Message> messageList, MessagePriority priority){
        // TODO: удалить из коллекции каждое сообщение с заданным приоритетом
        //  вывод в консоль до удаления и после удаления
        Iterator<Message> itr = messageList.listIterator();
        while (itr.hasNext()){
            if (itr.next().getPriority().equals(priority)){
                itr.remove();
            }
        }
        System.out.println("\n=== Удаляем из коллекции каждое сообщение с приоритетом MEDIUM ===");
        for (Message m:messageList){
            System.out.println(m.toString());
        }
    }

    public static void removeOther(List<Message> messageList, MessagePriority priority){
        // TODO: удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет
        //  вывод в консоль до удаления и после удаления
        Iterator<Message> itr = messageList.listIterator();
        while (itr.hasNext()){
            if (!itr.next().getPriority().equals(priority)){
                itr.remove();
            }
        }
        System.out.println("\n=== Удаляем из коллекции все сообщения, кроме приоритета URGENT ===");
        for (Message m:messageList){
            System.out.println(m.toString());
        }
    }


}
