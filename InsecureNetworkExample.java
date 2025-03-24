import javax.net.ssl.*;
import java.io.*;
import java.net.URL;

public class InsecureNetworkExample {
    public static void main(String[] args) {
        try {
            // 不安全的 HTTP 通信
            URL url = new URL("http://example.com"); // 风险点：未使用 HTTPS
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

            // 证书验证绕过
            trustAllCertificates(); // 风险点：禁用证书验证
            HttpsURLConnection httpsConnection = (HttpsURLConnection) new URL("https://example.com").openConnection();
            httpsConnection.setRequestMethod("GET");
            BufferedReader httpsReader = new BufferedReader(new InputStreamReader(httpsConnection.getInputStream()));
            while ((line = httpsReader.readLine()) != null) {
                System.out.println(line);
            }
            httpsReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 禁用证书验证
    private static void trustAllCertificates() throws Exception {
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
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }
}