package configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Cors {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .authorizeHttpRequests(
                        (authorize) ->
                                authorize.requestMatchers("/auth/register").permitAll())
                .authorizeHttpRequests(
                        (authorize) ->
                                authorize.anyRequest().authenticated());


        return http.build();
    }
}
