package com.rating.foodtruckapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


@Entity
@Table(name = "commnet")
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column
    private String userComment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "truckID")
    private TruckProfile truckProfile;

    public Comment() {
    }

    public Comment(Long commentId, String userComment) {
        this.commentId = commentId;
        this.userComment = userComment;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TruckProfile getTruckProfile() {
        return truckProfile;
    }

    public void setTruckProfile(TruckProfile truckProfile) {
        this.truckProfile = truckProfile;
    }
}
