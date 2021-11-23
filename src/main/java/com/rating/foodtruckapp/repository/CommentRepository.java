package com.rating.foodtruckapp.repository;

import com.rating.foodtruckapp.model.Comment;
import com.rating.foodtruckapp.model.TruckProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
List<Comment> findCommentsByTruckProfile(TruckProfile truckProfile);

}
