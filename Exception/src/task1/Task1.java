package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.jar.JarException;

/*
    Метод static void throwException(Status status) throws JarException, FileNotFoundException,
    AccessDeniedException принимает на вход enum и выбрасывает следующие исключения в зависимости от значения status:
    если status FILE_NOT_FOUND, выбрасывает FileNotFoundException
    если status ACCESS_DENIED, выбрасывает AccessDeniedException
    если status JAR_ERROR, выбрасывает JarException.

    При вызове метода throwException обработать исключения следующим образом: FileNotFoundException - выводить
    в консоль сообщение исключения(метод getMessage()) + любое дополнительное сообщение, AccessDeniedException -
    выводить в консоль сообщение исключения (метод getMessage()) и снова выбрасывать exception, JarException -
    выводить в консоль стек трейс.
*/

public class Task1 {

    private static final String testFile = System.getProperty("user.dir") + "\\test_file.txt";

    public static void main(String[] args) {
        Status status = null;
        System.out.println("File: " + testFile);

        try {
            if (!checkFile(testFile)) {
                throwException(Status.FILE_NOT_FOUND);
            }
            // Как добавить условие на проверку досутупа?
            //if () {
            throwException(Status.ACCESS_DENIED);
            //?
            //if () {
            throwException(Status.JAR_ERROR);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " + любое дополнительное сообщение");
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
            try {
                throw new AccessDeniedException(null).initCause(e);
            } catch (Throwable t) {
                System.out.println("More exception " + t.getCause());
            }
        } catch (JarException e) {
            e.printStackTrace();
        }

    }

    private static void throwException(Status status) throws FileNotFoundException, AccessDeniedException, JarException {
        if("FILE_NOT_FOUND".equals(status.name())){
            throw new FileNotFoundException("Файл не найден");
        } else if("ACCESS_DENIED".equals(status.name())){
            throw new AccessDeniedException("Доступ запрещен");
        } else if("JAR_ERROR".equals(status.name())){
            throw new JarException("Стек трейс");
        }
    }

    private static Boolean checkFile(String filePath){
        final File file = new File(filePath);
        if(file.exists()){
            return true;
        } else {
            return false;
        }
    }
}

enum Status {
    FILE_NOT_FOUND,
    ACCESS_DENIED,
    JAR_ERROR
}