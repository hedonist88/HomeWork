import java.lang.annotation.Annotation;
import java.util.HashMap;

@ClassConfig(prefix = "pref", version= 23)
public class AnnotationClass {
    @Required
    private String stringData;
    //@Required
    private int intData;

    private HashMap<Integer, String> hmData;

    public AnnotationClass() {
    }

    public AnnotationClass(String stringData, int intData, HashMap hmData) {
        setStringData(stringData);
        setIntData(intData);
        setHmData(hmData);
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public HashMap getHmData() {
        return hmData;
    }

    public void setHmData(HashMap hmData) {
        this.hmData = hmData;
    }

    @Override
    public String toString() {
        return "AnnotationClass{" +
                "stringData='" + stringData + '\'' +
                ", intData=" + intData +
                ", hmData=" + hmData +
                '}';
    }
}

