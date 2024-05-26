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

    @Query("SELECT u FROM User u JOIN Worker w ON u.worker.id=w.id JOIN Access a ON w.access.id=a.id WHERE u.login=:login AND u.password=:password AND (a.name IN ('Рекрутер','Администратор'))")
    User byAccees(String login, String password);

    @Query("SELECT u FROM User u JOIN Worker w ON u.worker.id=w.id JOIN Access a ON w.access.id=a.id WHERE u.login=:login AND u.password=:password AND (a.name = 'Администратор')")
    User byAdmin(String login, String password);
}
