package com.cuberxp.shoppingrecommend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Cuberxp
 */
@EnableOpenApi
@MapperScan("com.cuberxp.shoppingrecommend.dao")
@SpringBootApplication
public class ShoppingRecommendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingRecommendApplication.class, args);
    }

}
