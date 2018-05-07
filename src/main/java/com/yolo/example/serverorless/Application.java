package com.yolo.example.serverorless;


import org.springframework.context.annotation.ImportResource;

/** Simple class to start up the application.
 *
 * @SpringBootApplication adds:
 *  @Configuration
 *  @EnableAutoConfiguration
 *  @ComponentScan
 */
// @SpringBootApplication(scanBasePackages = {"com.aws.codestar", "tech.rsqn.cdsl"})
@ImportResource("classpath:/spring/app-ctx.xml")
public class Application {
    public static void main(String[] args) {
//         SpringApplication.run(Application.class, args);
    }
}
