package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.harmony.serverboot.entity.Form;

public interface FormRepo extends JpaRepository<Form, Long> {
}
