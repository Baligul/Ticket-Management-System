package com.intuit.tms.services;

import com.intuit.tms.entities.Comment;

import java.util.List;

import com.intuit.tms.dto.CommentDTO;;

public interface CommentService {

	Comment getCommentById(Long id);

	List<Comment> getCommentsByTicketId(Long ticketId);

	List<Comment> getCommentsByUserOnTicket(Long createdBy, Long ticketId);

	void deleteComment(Long id);

	Comment updateComment(CommentDTO commentDTO, Long commentId);

	Comment saveComment(CommentDTO commentDTO, Long ticketId);
}