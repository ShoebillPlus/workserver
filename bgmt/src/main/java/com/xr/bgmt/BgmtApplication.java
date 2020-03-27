package com.xr.bgmt;

//import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableSwagger2
@MapperScan("com.xr.bgmt.DAO")
public class BgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgmtApplication.class, args);
	}

}
