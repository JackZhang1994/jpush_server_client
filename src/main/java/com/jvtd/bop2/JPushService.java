package com.jvtd.bop2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



//@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class,DataSourceAutoConfiguration.class},scanBasePackages ={"com.jvtd.*"})
@SpringBootApplication
//@EnableScheduling//定时任务
//@EnableConfigurationProperties
//@EnableAsync  //线程池注解
//@EnableEurekaClient //启⽤服务发现
//@EnableAuthClient //启动网关
//@MapperScan("com.jvtd.dao")
public class JPushService {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(JPushService.class, args);
	}

}