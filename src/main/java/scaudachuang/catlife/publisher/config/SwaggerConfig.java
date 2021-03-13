package scaudachuang.catlife.publisher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否启动swagger 如果是false则不能在浏览器中使用
                .enable(true)
                .select()
                //RequestHandlerSelectors. 配置要扫描的方式
                //basePackage():指定要扫描的包
                //any():扫描全部
                //none()：不扫描
                //withClassAnnotation():扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation():扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("scaudachuang.catlife.publisher.controller"))
                //.paths() .过滤URL
                //any() 任何请求都扫描
                //none() 任何请求都不扫描
                //ant()	通过ant控制
                //regex() 通过正则表达式控制
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("开发者邮箱", null, "luyx27877@163.com");
        return new ApiInfo(
                "CatLife API document",
                "为前端提供访问api",
                "v1.0",
                "https://github.com/SCAUComputerClassOneEEE",
                contact,null,null,
                new ArrayList<>()
        );
    }

}
