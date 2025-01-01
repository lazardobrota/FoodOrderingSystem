package com.usermanagment.backend.config;

import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeedDataLoader implements CommandLineRunner {

    private final IUserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepo.count() != 0)
            return;

        User user = new User();
        user.setName("admin");
        user.setLastname("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setPermissionsBitMask(15);
        userRepo.save(user);

        user = new User();
        user.setName("Lazar");
        user.setLastname("Dobrota");
        user.setEmail("ldobrota@raf.rs");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setPermissionsBitMask(1);
        userRepo.save(user);

        user = new User();
        user.setName("Lazar");
        user.setLastname("Dobrota");
        user.setEmail("2ldobrota@raf.rs");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setPermissionsBitMask(0);
        userRepo.save(user);

        user = new User();
        user.setName("John");
        user.setLastname("Black");
        user.setEmail("jb@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setPermissionsBitMask(7);
        userRepo.save(user);

        user = new User();
        user.setName("Ime");
        user.setLastname("Prezime");
        user.setEmail("ip@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setPermissionsBitMask(15);
        userRepo.save(user);
    }
}
