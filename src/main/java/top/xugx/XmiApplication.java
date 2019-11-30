package top.xugx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.xugx.util.CipherUtils;

@SpringBootApplication(scanBasePackages = {"top.xugx","top.xugx.dao"})
public class XmiApplication {

    public static void main(String[] args) {
        try {
            String encoded = CipherUtils.md5("123456");
            System.out.println(encoded);
        }catch (Exception e){
            e.printStackTrace();
        }
        SpringApplication.run(XmiApplication.class, args);
    }

}
