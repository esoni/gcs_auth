package it.frongillo.demostorage;

import it.frongillo.demostorage.model.entity.UserEntity;
import it.frongillo.demostorage.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoStorageApplication {
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername("PIPPO");
            userEntity.setPassword(bCryptPasswordEncoder().encode("PIPPO"));
            userEntity.setFullName("PIPPO ROSSI");
            repository.save(userEntity);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoStorageApplication.class, args);
    }

}
