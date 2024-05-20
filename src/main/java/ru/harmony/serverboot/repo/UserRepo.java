package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.harmony.serverboot.entity.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    //@Query("SELECT u FROM User u JOIN u.worker w JOIN w.access a JOIN  a.name b WHERE b = 'Рекрутер'")
//    @Query("SELECT u FROM User u JOIN u.worker.access.name b WHERE b = :name")
//    List<User> findByRecruter(String name);
    List<User> findByWorkerAccessName(String name);

    User findByLoginAndPassword(String login, String password);
}
