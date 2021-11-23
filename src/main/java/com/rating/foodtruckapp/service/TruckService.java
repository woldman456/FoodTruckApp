package com.rating.foodtruckapp.service;

import com.rating.foodtruckapp.model.TruckProfile;
import java.util.List;
import java.util.Optional;

public interface TruckService {


    List<TruckProfile> findAll();

    TruckProfile saveNewTruck(TruckProfile truckProfile);

    void deleteTruck(long truckProfile, TruckProfile truckProfileObject);

    Optional<TruckProfile> findById(long truckProfileId);
    TruckProfile updateTruckProfile(long truckProfile, TruckProfile truckProfileObject);


}
