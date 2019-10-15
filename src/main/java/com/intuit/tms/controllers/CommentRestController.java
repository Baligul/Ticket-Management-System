package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.CommentDTO;
import com.intuit.tms.entities.Comment;
import com.intuit.tms.services.CommentService;
import com.intuit.tms.services.ErrorBuilderService;

import io.swagger.annotations.Api;

@RestController
@Api(description = "Comment Management APIs", produces = "application/json", tags = { "A8" })
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

	@ModelAttribute("comment")
	public CommentDTO commentDTO() {
		return new CommentDTO();
	}

	@PostMapping("/api/v1/comment/tickets/{ticketId}")
	public ResponseEntity<Object> saveComment(@ModelAttribute("comment") @Valid CommentDTO commentDTO,
			BindingResult result, @PathVariable(name = "ticketId") Long ticketId) {

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			commentService.saveComment(commentDTO, ticketId);
			return new ResponseEntity<Object>("You have successfully added a comment", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Comment cannot be saved, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/v1/comments/tickets/{ticketId}")
	public ResponseEntity<Object> getCommentsByTicketId(@PathVariable(name = "ticketId") Long ticketId) {

		try {
			List<Comment> comments = commentService.getCommentsByTicketId(ticketId);
			return new ResponseEntity<Object>(comments, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Comments cannot be fetched for tikets, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/v1/comments/tickets/{ticketId}/users/{userId}")
	public ResponseEntity<Object> getCommentsByUserOnTicket(@PathVariable(name = "ticketId") Long ticketId,
			@PathVariable(name = "userId") Long userId) {

		try {
			List<Comment> comments = commentService.getCommentsByUserOnTicket(userId, ticketId);
			return new ResponseEntity<Object>(comments, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(
					"Comments by user for ticket cannot be fetched, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/api/v1/comment/{commentId}")
	public ResponseEntity<Object> updateComment(@PathVariable(name = "commentId") Long commentId,
			@ModelAttribute("comment") @Valid CommentDTO commentDTO, BindingResult result) {

		Comment existing = commentService.getCommentById(commentId);
		if (existing == null) {
			return new ResponseEntity<Object>("The comment with comment id as " + commentId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			commentService.updateComment(commentDTO, commentId);
			return new ResponseEntity<Object>("You have successfully updated a comment with comment id " + commentId,
					HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Comments cannot be updated, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/api/v1/comment/{commentId}")
	public ResponseEntity<Object> deleteComment(@PathVariable(name = "commentId") Long commentId) {

		Comment existing = commentService.getCommentById(commentId);
		if (existing == null) {
			return new ResponseEntity<Object>("The comment with comment id as " + commentId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			commentService.deleteComment(commentId);
			return new ResponseEntity<Object>("Comment successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Comments cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
