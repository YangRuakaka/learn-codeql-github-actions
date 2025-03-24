import java.io.*;

public class DeserializationExample {
    public static void main(String[] args) {
        try {
            byte[] userInput = new byte[1024];
            System.in.read(userInput);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object obj = objectInputStream.readObject(); // 风险点：可能触发 RCE
            System.out.println("Deserialized object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}