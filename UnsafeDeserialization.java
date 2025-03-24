import java.io.*;
import com.alibaba.fastjson.JSON;

public class UnsafeDeserialization {
    public static void main(String[] args) {
        try {
            // 从用户输入中读取数据
            byte[] userInput = new byte[1024];
            System.in.read(userInput);

            // 不安全的反序列化操作 1: 使用 ObjectInputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object obj1 = objectInputStream.readObject(); // 风险点：可能触发 RCE
            System.out.println("Deserialized object 1: " + obj1);

            // 不安全的反序列化操作 2: 使用 fastjson
            String jsonInput = new String(userInput).trim(); // 将字节数组转换为字符串
            Object obj2 = JSON.parseObject(jsonInput); // 风险点：可能触发 RCE 或数据篡改
            System.out.println("Deserialized object 2: " + obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}