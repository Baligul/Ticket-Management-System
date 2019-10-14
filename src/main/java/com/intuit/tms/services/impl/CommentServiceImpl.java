package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Comment;
import com.intuit.tms.entities.Ticket;
import com.intuit.tms.repositories.CommentRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.CommentService;
import com.intuit.tms.dto.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	public Comment getCommentById(Long id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isPresent()) {
			return comment.get();
		}
		return null;
	}

	@Override
	public Comment saveComment(CommentDTO commentDTO, Long ticketId) {
		Comment comment = new Comment();
		comment.setComment(commentDTO.getComment());
		comment.setTicket(new Ticket(ticketId));
		comment.setCreatedBy(customUserDetailsService.getCurrentLoggedInUserId());
		return commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long commentId) {
		// The user should be an of comment to be able to delete it
		Comment comment = commentRepository.findById(commentId).get();

		if (customUserDetailsService.getCurrentLoggedInUserId().equals(comment.getCreatedBy()))
			commentRepository.deleteById(commentId);
	}

	@Override
	public Comment updateComment(CommentDTO commentDTO, Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (customUserDetailsService.getCurrentLoggedInUserId().equals(comment.get().getUpdatedBy())) {
			Comment c = new Comment();
			c.setId(commentId);
			c.setComment(commentDTO.getComment());
			c.setUpdatedBy(customUserDetailsService.getCurrentLoggedInUserId());
			c.setUpdatedOn(LocalDateTime.now());
			return commentRepository.save(c);
		}
		return null;
	}

	@Override
	public List<Comment> getCommentsByTicketId(Long ticketId) {
		return commentRepository.findByTicketId(ticketId);
	}

	@Override
	public List<Comment> getCommentsByUserOnTicket(Long createdBy, Long ticketId) {
		return commentRepository.findByCreatedByAndTicketId(createdBy, ticketId);
	}
}
