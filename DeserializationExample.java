import java.io.*;

public class DeserializationExample {
    public static void main(String[] args) {
        try {
            // 从用户输入中读取数据
            byte[] userInput = new byte[1024];
            System.in.read(userInput);

            // 不安全的反序列化操作
            ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object obj = objectInputStream.readObject(); // 风险点：可能触发 RCE
            System.out.println("Deserialized object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}