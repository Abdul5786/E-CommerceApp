package com.AbdulKhaliq.EcommerceApplication.config;
import com.AbdulKhaliq.EcommerceApplication.security.JwtAuthenticationEntryPoint;
import com.AbdulKhaliq.EcommerceApplication.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
   {
//           ,"/api/v1/createAdmin","/api/v1/getAllUsers"
       http.csrf(csrf -> csrf.disable())
               .cors(cors->cors.disable())
               .authorizeHttpRequests(auth->auth
                       .requestMatchers("/api/register/createUser","/auth/Login").permitAll().anyRequest().authenticated())
               .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

       return  http.build();
   }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider()
   {
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setUserDetailsService(userDetailsService);
      provider.setPasswordEncoder(passwordEncoder);
       return provider;
   }
 }






//   @Bean   // it is used to enable cors policy at global level
//   public CorsConfiguration corsConfiguration()
//   {
////       CorsConfiguration corsConfiguration = new CorsConfiguration();
//       corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//       corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//       corsConfiguration.setAllowCredentials(true);
//       return corsConfiguration;
//




