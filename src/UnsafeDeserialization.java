import java.io.*;

public class UnsafeDeserialization {
    public static void main(String[] args) {
        try {
            // 从用户输入中读取序列化数据
            byte[] serializedData = getUserInput();

            // 不安全的反序列化操作
            ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject(); // 风险点：不安全的反序列化
            System.out.println("Deserialized object: " + obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 模拟用户输入（实际场景中可能来自网络或文件）
    private static byte[] getUserInput() {
        // 这里返回一个模拟的序列化对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(new String("This is a test object"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}