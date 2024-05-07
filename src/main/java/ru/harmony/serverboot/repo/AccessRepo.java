package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.harmony.serverboot.entity.Access;

public interface AccessRepo extends JpaRepository<Access, Long> { }