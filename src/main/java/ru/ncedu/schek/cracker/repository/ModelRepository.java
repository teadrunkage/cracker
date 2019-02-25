package ru.ncedu.schek.cracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.schek.cracker.entities.Model;

/**
 * Created by Admin on 24.02.2019.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
}
