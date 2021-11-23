package com.rating.foodtruckapp.service;

import com.rating.foodtruckapp.constraint.FieldMatch;
import com.rating.foodtruckapp.model.Comment;
import com.rating.foodtruckapp.model.TruckProfile;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getComments(TruckProfile truckObject);
    Comment saveComment (Comment newComment, Long TruckId);

}
