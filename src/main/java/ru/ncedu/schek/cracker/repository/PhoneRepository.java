package ru.ncedu.schek.cracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ncedu.schek.cracker.entities.Phone;

/**
 * Created by Admin on 24.02.2019.
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
    @Query(value="SELECT * FROM Phone WHERE Phone.id like %?1%",nativeQuery = true)
    Phone getById(Long id);
}
