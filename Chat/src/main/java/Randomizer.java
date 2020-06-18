import java.util.Date;
import java.util.Random;

public class Randomizer {

    /**
     * Randomizer nick from arr
     * @return string
     */
    public static String getNickName(){
        Random rand = new Random(new Date().getTime());
        String[] arr = {"Butterbean", "Big Baby", "The Hawk", "Spud", "Megatron", "The Grim Reaper",
                "Shogun", "The Assassin"};
        return arr[rand.nextInt(arr.length)];
    }

    /**
     * Randomizer simple device id from arr
     * @return string
     */
    public static String getDevice(){
        Random rand = new Random(new Date().getTime());
        String[] arr = {"OnePLus 8 Pro", "Samsung Galaxy Tab", "IPad Pro", "iPhone 11", "Xiaomi mi5", "Nokia 3310"};
        return arr[rand.nextInt(arr.length)];
    }

    /**
     * Randomizer phone number from arr
     * @return string
     */
    public static String getNumber(){
        Random rand = new Random(new Date().getTime());
        String[] arr = {"+79211234567", "+79211234568", "+79211234569", "+79211234510", "+79211234511",
                "+79211244567", "+79211147867", "+79213697567"};
        return arr[rand.nextInt(arr.length)];
    }



}
