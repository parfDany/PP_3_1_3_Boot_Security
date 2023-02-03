package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration//не особо важно
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userServiceImpl;
    private final SuccessUserHandler successUserHandler;

    @Autowired
    public WebSecurityConfig(UserDetailsService userServiceImpl, SuccessUserHandler successUserHandler) {
        this.userServiceImpl = userServiceImpl;
        this.successUserHandler = successUserHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/read_for_admins/**").hasRole("ADMIN")
                .antMatchers("/read_profile").hasAuthority("READ_PROFILE")
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();
    }

    // аутентификация inMemory
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userServiceImpl);

        return authenticationProvider;
    }

//        @Bean
//        public JdbcUserDetailsManager users(DataSource dataSouce) {
//            UserDetails user =
//                    User.withDefaultPasswordEncoder()
//                            .username("user")
//                            .password("user")
//                            .roles("USER")
//                            .build();
//            UserDetails admin =
//                    User.withDefaultPasswordEncoder()
//                            .username("admin")
//                            .password("admin")
//                            .roles("ADMIN")
//                            .build();
 //           JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//            if (users.userExists(user.getUsername())){
//                users.deleteUser(user.getUsername());
//            }
//            if (users.userExists(admin.getUsername())){
//                users.deleteUser(admin.getUsername());
//            }
//            users.createUser(user);
//            users.createUser(admin);
 //           return users;
 //       }
 //   }
}