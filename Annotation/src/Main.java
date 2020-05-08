
/*

Использовать аннотации @ConfigClass и @Required из занятия про рефлексию и аннотации

Создать модельный класс с конструктором, полями, геттерами, сеттерами, toString(). Аннотировать его аннотациями @ConfigClass и @Required.

Написать статический метод по обработке классов (любых) по следующим правилам:

    Если класс аннотирован аннотацией CongigClass создать объект данного класса
        использовать рефлексию; должно работать с любым классом.
    Если в классе есть поле, отмеченное аннотацией @Required:
        установить значение данного поля (значение любое).
        Значение поля установить через сеттер (исходя из того, что название сеттера состоит из "set" и имени поля)
    У созданного объекта вызвать toString() используя рефлексию.

 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Object object = getAnoClass(AnnotationClass.class);
        System.out.println(object);
    }

    private static Object getAnoClass(Class<?> oClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(oClass == null) return null;
        Object result = null;
        if(!oClass.isAnnotationPresent(ClassConfig.class)) return null;
        result = getInstance(oClass);
        return result;
    }

    private static Object getInstance(Class<?> oClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(oClass == null) return null;
        Object result = null;
        if(isPrimitive(oClass)) return null;
        Constructor<?> constructor = oClass.getConstructor();
        result = constructor.newInstance();
        setFields(result);
        return result;
    }

    private static void setFields(Object obj){
        if(obj == null) return;
        Class<?> oClass = obj.getClass();
        ArrayList<Field> fields = new ArrayList<>();
        ArrayList<Field> requiredFields = new ArrayList<>();
        while (oClass != null) {
            fields.addAll(Arrays.asList(oClass.getDeclaredFields()));
            oClass = oClass.getSuperclass();
        }
        for (Field field:fields) {
            if (field.isAnnotationPresent(Required.class)) {
                requiredFields.add(field);
            }
        }
        oClass = obj.getClass();
        for (Field field:requiredFields) {
            Method setterField = null;
            try {
                setterField = oClass.getMethod(getMethodName(field.getName()), field.getType());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                setterField.setAccessible(true);
                setterField.invoke(obj, getFieldVal(field.getType()));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getMethodName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static Object getFieldVal(Class<?> oClass) {
        if(oClass == null) return null;
        Object result = null;
        if (oClass == Integer.class || oClass == Long.class || oClass == Float.class || oClass == Double.class ||
            oClass == Boolean.class || oClass == Byte.class || oClass == Short.class) {
            return 0;
        }
        if (oClass == String.class || oClass == Character.class) {
            return "";
        }
        if (oClass == Boolean.class) return false;
        return result;
    }

    private static Boolean isPrimitive(Class<?> oClass){
        Boolean result = false;
        if (oClass == Integer.class || oClass == Long.class || oClass == Float.class || oClass == Double.class ||
                oClass == Boolean.class || oClass == Byte.class || oClass == Short.class ||
                oClass == String.class || oClass == Character.class) {
            result = true;
        }
        return result;
    }

}
