package com.intuit.tms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Optional<Comment> findById(Long id);

	List<Comment> findByTicketId(Long id);

	List<Comment> findByCreatedBy(Long createdBy);

	List<Comment> findByCreatedByAndTicketId(Long createdBy, Long id);
}
