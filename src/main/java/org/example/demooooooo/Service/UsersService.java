package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.users1;
import org.example.demooooooo.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserRepository repo;

    public UsersService(UserRepository repo) {
        this.repo = repo;
    }

    public users1 login(String username, String password) {
        users1 user = repo.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public users1 save(users1 user) {
        return repo.save(user);
    }
}