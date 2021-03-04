package it.academy;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "it.academy")
@Profile("test")
public class RestTestConfiguration {


}
