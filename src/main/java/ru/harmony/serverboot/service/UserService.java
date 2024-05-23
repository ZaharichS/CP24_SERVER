package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.User;
import ru.harmony.serverboot.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo repo;

    public List<User> getAll() {
        return repo.findAll();
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public List<User> getByAccess(String name) {
        return repo.findByWorkerAccessName(name);
    }

    public User getByUserData(String login, String password) {
        return repo.findByLoginAndPassword(login, password);
    }

    public User getByUserAccess(String login, String password) {
        return repo.byAccees(login, password);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void update(User user){
        repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
