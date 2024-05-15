package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.harmony.serverboot.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
