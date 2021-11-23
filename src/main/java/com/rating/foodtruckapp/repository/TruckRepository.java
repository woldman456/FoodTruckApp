package com.rating.foodtruckapp.repository;

import com.rating.foodtruckapp.model.Comment;
import com.rating.foodtruckapp.model.TruckProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TruckRepository extends JpaRepository<TruckProfile, Long> {
    Optional<TruckProfile> findByTruckid (Long truckId);
    TruckProfile findTruckProfilesByTruckid(Long truckId);
    List<TruckProfile> findCommentByTruckid (Long truckid);

}
