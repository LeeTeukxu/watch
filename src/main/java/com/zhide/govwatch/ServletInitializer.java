package com.zhide.govwatch;

import com.aspose.words.License;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.InputStream;

public class ServletInitializer extends SpringBootServletInitializer {

    protected static boolean getLicense() {
        boolean result = false;
        try {

            System.out.println("开始破解........");
            InputStream is = GovwatchApplication.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = aposeLic.getIsLicensed();
            System.out.println("破解完成........,结果:" + Boolean.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        getLicense();
        return application.sources(GovwatchApplication.class);
    }
}
