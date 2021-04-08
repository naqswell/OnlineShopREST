package edu.spbstu.eight.config;

import edu.spbstu.eight.auth.User;
import edu.spbstu.eight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/user").permitAll()
        .antMatchers(HttpMethod.POST, "/user").not().authenticated()
        .antMatchers("/**", "/global.css", "/js/**", "/img/**", "/build/**", "/favicon.png", "/registration").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .defaultSuccessUrl("/")
        .permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
  }

  @Bean
  public AuthenticationProvider authProvider() {
    var provider = new DaoAuthenticationProvider();

    addAdmin();
    addDefaultUser();

    System.err.println("USERNAME: user PASSWORD: 123");
    System.err.println("USERNAME: admin PASSWORD: 123");

    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userService);

    return provider;
  }

  private User addDefaultUser() {
    final User user = new User();

    user.setUsername("user");
    user.setPassword(passwordEncoder.encode("123"));
    user.setRoles(Set.of("USER"));

    return userService.save(user);
  }

  private User addAdmin() {
    final User admin = new User();

    admin.setUsername("admin");
    admin.setPassword(passwordEncoder.encode("123"));
    admin.setRoles(Set.of("ADMIN"));

    return  userService.save(admin);
  }
}
