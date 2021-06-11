package com.fundamentalconstant;

import com.fundamentalconstant.app.*;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;

@SpringBootApplication
public class FundamentalConstantApplication implements ApplicationRunner {

    protected final Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

    @Autowired
    private ApplicationRoot applicationRoot;

    public static void main(String[] args) {
        new SpringApplicationBuilder(FundamentalConstantApplication.class)
                .headless(false)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            applicationRoot.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
