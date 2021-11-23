package com.rating.foodtruckapp.serviceimpl;

import com.rating.foodtruckapp.exception.InformationNotFoundException;
import com.rating.foodtruckapp.model.Comment;
import com.rating.foodtruckapp.model.TruckProfile;
import com.rating.foodtruckapp.model.User;
import com.rating.foodtruckapp.repository.CommentRepository;
import com.rating.foodtruckapp.repository.TruckRepository;
import com.rating.foodtruckapp.repository.UserRepository;
import com.rating.foodtruckapp.service.CommentService;
import com.rating.foodtruckapp.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TruckRepository truckRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TruckService truckService;

    @Override
    public List<Comment> getComments(TruckProfile truckObject) {

        return commentRepository.findCommentsByTruckProfile(truckObject);


    }

    @Override
    public Comment saveComment(Comment comment, Long truckProfileId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        User user = userRepository.findByEmail(username);
        if(user== null){
            throw new InformationNotFoundException
                    ("No user with userName " + username + " found");
        }
        TruckProfile truckProfile = truckRepository.findTruckProfilesByTruckid(truckProfileId);
        if(truckProfile == null){
            throw new InformationNotFoundException("No TruckProfile with that id found ");
        }
        comment.setTruckProfile(truckProfile);
        comment.setUserComment(comment.getUserComment());
        comment.setUser(user);
        return commentRepository.save(comment);
    }


}
