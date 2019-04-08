package com.lut;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.lut.repository")
@ServletComponentScan
public class ArchivesApplication {
	private final static Logger log = LoggerFactory.getLogger(ArchivesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ArchivesApplication.class, args);
		log.info("服务器启动成功 ... ‵(*∩_∩*)′\n");
	}
}
