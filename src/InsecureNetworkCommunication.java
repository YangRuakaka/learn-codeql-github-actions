import javax.net.ssl.*;
import java.io.*;
import java.net.*;

public class InsecureNetworkCommunication {
    public static void main(String[] args) {
        try {
            // 不安全的 HTTP 通信
            URL url = new URL("http://example.com"); // 风险点：使用 HTTP 而非 HTTPS
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 读取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString());

            // 不安全的证书验证
            trustAllCertificates(); // 风险点：绕过证书验证
            URL httpsUrl = new URL("https://example.com");
            HttpsURLConnection httpsConnection = (HttpsURLConnection) httpsUrl.openConnection();
            httpsConnection.setRequestMethod("GET");
            System.out.println("HTTPS Response Code: " + httpsConnection.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 绕过证书验证
    private static void trustAllCertificates() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // 忽略主机名验证
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}