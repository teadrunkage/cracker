package ru.ncedu.schek.cracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ncedu.schek.cracker.entities.Model;

/**
 * Created by Admin on 24.02.2019.
 */
@RepositoryRestResource
public interface ModelRepository extends JpaRepository<Model,Long> {
    @Query(value="SELECT * FROM Model WHERE Model.name like %?1%",nativeQuery = true)
    Model findByModelName(String modelName);
}
