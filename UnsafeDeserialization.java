import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeserializationVulnerable {
    public static void main(String[] args) {
        try {
            // 创建一个恶意对象
            Map<String, String> map = new HashMap<>();
            map.put("key", "value");

            // 将恶意对象序列化到文件
            FileOutputStream fos = new FileOutputStream("vulnerable.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();

            // 从文件中反序列化对象
            FileInputStream fis = new FileInputStream("vulnerable.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject(); // 漏洞点：直接反序列化外部输入对象
            ois.close();
            fis.close();

            System.out.println("反序列化成功：" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}