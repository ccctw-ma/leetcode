package sort;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Base64;

public class test extends parent{

    public int addc(int a, int b){
        return a<<b;
    }

    public void read() throws IOException {
        File file = new File("C:/Users/qq/Desktop/计算机专业英语");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        while ((s=reader.readLine())!=null){
            System.out.println(s);
        }
    }

    public static String AESEncode1(String encodeRules, String content) {
        try {
            //构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(encodeRules.getBytes());
            keygen.init(128, sr);
            //3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(1, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            Base64.Encoder encoder = Base64.getEncoder();
            String AES_encode = new String(encoder.encodeToString(byte_AES));
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void processCexe(){
        Runtime run = Runtime.getRuntime();
        try {
            Process p = run.exec("./src/sort/test01.exe");
            System.out.println(p.toString());
            System.out.println(p.pid());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(p.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(p.getOutputStream());
            int ch;
            StringBuilder buffer = new StringBuilder("获得的信息是: \n");

            while ((ch = bufferedInputStream.read()) != -1) {
                buffer.append((char) ch);
            }


            int retval = p.waitFor();

            System.out.println(buffer);
            System.out.println(1);

            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void range() {
        long TimeNanos = (long) (9.859706 * 1e13);
        long FullBiasNanos = (long) (-1.315528257385472798 * 1e18);
        double BiasNanos = -0.46668243408203125;
        double gpsTime = TimeNanos - (FullBiasNanos + BiasNanos);
        long nanos_week = (long) (1.315440 * 1e18);
        long ReceivedSvTimeNanos = (long) (1.86854377410807 * 1e14);
        double range = ((gpsTime - nanos_week - ReceivedSvTimeNanos));
        range = range / 1e9;
        range = range * (long) (2.99792458 * 1e8);
        System.out.println(range);
    }

    public static void main(String[] args) throws IOException, ParseException {

        processCexe();

    }


}
