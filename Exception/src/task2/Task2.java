package task2;

import java.util.ArrayList;

/*
    Создать список исключений и заполнить его 9 различными runtime исключениями. Например,
      try {
          int[] arr = new int[5];
          arr[7] = 12;
      } catch (Exception e) {
          exceptionsList.add(e);
      }
*/

public class Task2 {

    private static ArrayList<Exception> exceptionsList = new ArrayList<>();

    public static void main(String[] args) {

        // 1.ArrayStoreException
        try {
            Object arr[] = new Boolean[3];
            arr[1] = 20;
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 2.ArrayIndexOutOfBoundsException
        try {
            int[] arr = new int[3];
            arr[7] = 12;
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 3.ClassCastException
        try {
            Object i = 123;
            System.out.println((String) i);
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 4.IllegalArgumentException
        try {
            String param = "a2";
            System.out.println(ExEnum.valueOf(param));
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 5.NumberFormatException
        try {
            Integer.parseInt("NumberFormatException");
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 6.NullPointerException
        try {
            String nullString = null;
            nullString.endsWith("NullPointerException");
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 7.NegativeArraySizeException
        try {
            int[] arr = new int[-1];
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 8.NoSuchFieldException
        try {
            Object.class.getClass().getField("stringField").equals("ex");
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        // 9.ArithmeticException
        try {
            int j = 10 - 10;
            int i = 20;
            System.out.println( i/j );
        } catch (Exception e) {
            exceptionsList.add(e);
        }

        for (Exception e:exceptionsList){
            System.out.println(e.toString());
        }

    }
}

enum ExEnum {
    a3, a4, a5
}


/* out */
/*
java.lang.ArrayStoreException: java.lang.Integer
java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 3
java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
java.lang.IllegalArgumentException: No enum constant task2.ExEnum.a2
java.lang.NumberFormatException: For input string: "NumberFormatException"
java.lang.NullPointerException
java.lang.NegativeArraySizeException: -1
java.lang.NoSuchFieldException: stringField
java.lang.ArithmeticException: / by zero
 */
