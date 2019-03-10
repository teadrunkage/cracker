package ru.ncedu.schek.cracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ncedu.schek.cracker.entities.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
