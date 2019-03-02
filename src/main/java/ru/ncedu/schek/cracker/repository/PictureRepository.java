package ru.ncedu.schek.cracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ncedu.schek.cracker.entities.Picture;

/**
 * Created by Admin on 24.02.2019.
 */
@RepositoryRestResource
public interface PictureRepository  extends JpaRepository<Picture,Long> {
}
