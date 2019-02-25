package ru.ncedu.schek.cracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.schek.cracker.entities.Picture;

/**
 * Created by Admin on 24.02.2019.
 */
@Repository
public interface PictureRepository  extends JpaRepository<Picture,Long> {
}
