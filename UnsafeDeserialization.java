import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeserializationVulnerable {
      public static void main(String[] args) {
        try {
            // 模拟从不可信来源获取数据
            byte[] maliciousInput = getMaliciousInput();

            // 将恶意输入数据反序列化
            ByteArrayInputStream bais = new ByteArrayInputStream(maliciousInput);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject(); // 漏洞点：直接反序列化不可信输入
            ois.close();

            System.out.println("反序列化成功：" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 模拟获取恶意输入数据的方法
    private static byte[] getMaliciousInput() {
        // 这里可以返回恶意构造的字节数组
        // 例如，从网络请求、用户输入等获取
        return new byte[]{};
    }
}