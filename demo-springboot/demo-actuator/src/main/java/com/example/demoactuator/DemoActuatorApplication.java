package com.example.demoactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 除了提供最基本的健康检查外，actuator 还提供了许多其他的端点（Endpoints）信息。通过这些端点信息，我们可以掌握 99% 的应用状态信息。
 */

@SpringBootApplication
public class DemoActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoActuatorApplication.class, args);
	}

}
