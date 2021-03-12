package it.academy.web.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebShopSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
                .withUser("user")
                .password("{noop}password")
//                .password("{bcrypt}$2a$10$zn4ZyBzymddvGSQQOcGB2.XsBzziDtgz5Z.9PqBEpQ/FuGEb7SfOq")
                .roles("USER")
                .and()
                .withUser("admin")
//                .password("{bcrypt}$2a$10$zn4ZyBzymddvGSQQOcGB2.XsBzziDtgz5Z.9PqBEpQ/FuGEb7SfOq")
                .password("{noop}password")
                .roles("USER", "ADMIN");
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/product").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .formLogin();
    }

}
