import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.*;

/*
        Написать рефлексивный статический метод public static String toString(Object o), который выдаёт информацию
        по всем полям объекта, используя рефлексию, в формате: название поля: значения поля.
        Если поле имеет ссылочный тип - обработать это отдельно: не выводить hashCode объекта, а приводить
        к строке по тем же правилам.
        Отловить момент, когда рекурсивный вызов toString может вызвать проблемы - вовремя остановить рекурсию.
* */
public class Classinfo {

    private static Boolean shift = false;

    public static String toString(Object o) throws NoSuchFieldException, IllegalAccessException {
        if (o == null) return null;
        StringBuilder result = new StringBuilder();
        Class<?> oClass = o.getClass();
        if (oClass == Object.class) {
            return null;
        }
        if(isPrimitive(oClass)){
            return o.toString();
        }
        if (oClass.isArray()) {
            Object[] arrObj = ((Object[]) o);
            for (Object obj:arrObj) {
                if(!isPrimitive(obj.getClass())){
                    result.append("\n");
                    shift = true;
                }
                if(shift) result.append("      ");
                result.append(toString(obj));
                if(!shift) result.append(" / ");
            }
            if (arrObj.length > 0 && !shift) result.setLength(result.length() - 3);
            return result.toString();
        }
        if (o instanceof Map) {
            Map<?, ?> arrObjMap = ((Map<?, ?>) o);
            Set<? extends Map.Entry<?, ?>> entries = arrObjMap.entrySet();
            for (Map.Entry<?, ?> entry : entries) {
                if(!isPrimitive(entry.getClass())){
                    result.append("\n");
                    shift = true;
                }
                if(shift) result.append("      ");
                result.append(toString(entry.getKey())).append(" = ").append(toString(entry.getValue()));
                if(!shift) result.append("; ");
            }
            if (entries.size() > 0) result.setLength(result.length() - 2);
            return result.toString();
        }
        if (o instanceof Collection) {
            Collection<?> arrObjCol = ((Collection<?>) o);
            for (Object obj:arrObjCol) {
                if(!isPrimitive(obj.getClass())){
                    result.append("\n");
                    shift = true;
                }
                if(shift) result.append("      ");
                result.append(toString(obj));
                if(!shift) result.append(" / ");
            }
            if (arrObjCol.size() > 0) result.setLength(result.length() - 3);
            return result.toString();
        }
        ArrayList<Field> fields = new ArrayList<>();
        result.append("@ Класс: " + oClass.getSimpleName() + "\n");
        while (oClass != null) {
            fields.addAll(Arrays.asList(oClass.getDeclaredFields()));
            oClass = oClass.getSuperclass();
        }
        for (Field field:fields) {
            field.setAccessible(true);
            Object val = field.get(o);
            if(shift) result.append("      ");
            result.append("   " + field.getName()).append(": ");
            result.append(toString(val));
            result.append("\n");
        }
        shift = false;
        if (fields.size() > 0) result.setLength(result.length() - 1);
        return result.toString();
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
