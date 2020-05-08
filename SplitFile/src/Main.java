import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String mask = "img_part_";
        String folder ="media/parts/";
        int numOfParts = 4;
        File inputFile = new File("media/af14736294ee058af31fd0436bc91d62.jpg");
        File outputFile = new File("media/output.jpg");
        List<String> mergeFiles = new ArrayList<>();

        // разделили
        splitFile(inputFile, folder.toString() + mask, numOfParts);

        // нашли
        searchFiles(".*\\.bin", new File(folder), mergeFiles);

        // собрали
        mergeFile(outputFile, mergeFiles);

    }

    private static void splitFile(File file, String folderMask, int numOfParts) throws IOException {
        if(file == null) return;
        List<Byte> orig = new LinkedList<>();
        List<List<Byte>> parts = new LinkedList<List<Byte>>();

        byte[] bytes = imgToByteArray(file);
        for (int i = 0; i < bytes.length; i++){
            orig.add(bytes[i]);
        }

        int partSize = (bytes.length / numOfParts) + 1;
        for (int i = 0; i < bytes.length; i += partSize) {
            parts.add(orig.subList(i, Math.min(i + partSize, orig.size())));
        }

        for (int i = 0; i < parts.size(); i++){
            File outFile = new File(folderMask + i + ".bin");
            byte[] outBytes = new byte[parts.get(i).size()];
            for (int j = 0; j < parts.get(i).size(); j++){
                outBytes[j] =  parts.get(i).get(j);
            }
            writeBytesToFile(outFile, outBytes, false);
        }
    }

    private static void mergeFile(File file, List<String> files) throws IOException {
        if(file == null || files == null) return;
        int byteSize = 0;
        List<Byte> orig = new LinkedList<>();
        for (String f:files){
            byte[] bytes = readFromFileToByteArray(new File(f));
            byteSize += bytes.length;
            for (int i = 0; i < bytes.length; i++){
                orig.add(bytes[i]);
            }
        }
        byte[] resultBytes = new byte[byteSize];
        int i = 0;
        for (Byte b:orig){
            resultBytes[i] = b;
            i++;
        }
        byteArrayToImage(file, resultBytes);
    }

    private static void writeBytesToFile(File file, byte[] bytes, boolean append) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(file, append)){
            fileOutput.write(bytes);
        }
    }

    public static byte[] readFromFileToByteArray(File file) throws IOException {
        byte[] result;
        try (FileInputStream fileInput = new FileInputStream(file);
             ByteArrayOutputStream arrOut = new ByteArrayOutputStream()) {
            byte[] buf = new byte[512];
            int data;
            while ((data = fileInput.read(buf)) > 0) {
                arrOut.write(buf, 0, data);
            }
            result = arrOut.toByteArray();
        }
        return result;
    }

    public static byte[] imgToByteArray(File file) throws IOException{
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", output);
        return output.toByteArray();
    }

    public static boolean byteArrayToImage(File file, byte[] bytes) throws IOException{
        try(ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            BufferedImage image = ImageIO.read(input);
            return ImageIO.write(image,"jpg",file);
        }
    }

    public static void searchFiles(final String pattern, final File folder, List<String> result) {
        for (final File f:folder.listFiles()) {

            if (f.isDirectory()) {
                searchFiles(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }


}
