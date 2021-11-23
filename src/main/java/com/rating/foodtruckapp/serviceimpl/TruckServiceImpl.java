package com.rating.foodtruckapp.serviceimpl;

import com.rating.foodtruckapp.exception.InformationNotFoundException;
import com.rating.foodtruckapp.model.TruckProfile;
import com.rating.foodtruckapp.repository.TruckRepository;
import com.rating.foodtruckapp.service.TruckService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    TruckRepository truckRepository;


    /**
     * this class is the service layer that provides the search and logic for saving a new foodtruck instance to the database
     * @param truckProfileId
     * @return
     */

    @Override
    public Optional<TruckProfile> findById(long truckProfileId) {
        return truckRepository.findById(truckProfileId);
    }

    @Override
    public TruckProfile saveNewTruck(TruckProfile newTruck) {
        newTruck.setTruckName(newTruck.getTruckName());
        System.out.println(" calling service save truck ===========");
        newTruck.setDayOfWeek(newTruck.getDayOfWeek(), newTruck.getLocation());
        newTruck.setMenu(newTruck.getMenu());
        newTruck.setPhone(newTruck.getPhone());
        return truckRepository.save(newTruck);

    }

    /**
     * this class is the service layer that provides the search and logic for updating a truck profile
     * @param truckProfileId
     * @param truckProfile
     * @return
     */
    @Override
    public TruckProfile updateTruckProfile(long truckProfileId, TruckProfile truckProfile) {
        System.out.println("inside repo");
        Optional<TruckProfile> oldTruckProfile = truckRepository.findById(truckProfileId).stream().filter(p -> p.getTruckid().equals(truckProfileId)).findFirst();
        if (!oldTruckProfile.isPresent()) {
            throw new InformationNotFoundException("truck profile with id " + truckProfileId +
                    " not belongs to this user or recipe does not exist");
        }
        oldTruckProfile.get().setLocation(truckProfile.getLocation());
        oldTruckProfile.get().setTruckName(truckProfile.getTruckName());
        oldTruckProfile.get().setDayOfWeek(truckProfile.getDayOfWeek());
        oldTruckProfile.get().setMenu(truckProfile.getMenu());
        oldTruckProfile.get().setPhone(truckProfile.getPhone());
        return truckRepository.save(oldTruckProfile.get());
    }

    /**
     * this class is the service layer that provides the logic for deleting a truck profile
     * @param truckProfileId
     * @param truckProfile
     */
    @Override
    public void deleteTruck(long truckProfileId, TruckProfile truckProfile) {
        System.out.println("calling delete");
        Optional<TruckProfile> oldTruckProfile = truckRepository.findById(truckProfileId).stream().filter(p -> p.getTruckid().equals(truckProfileId)).findFirst();
        if (!oldTruckProfile.isPresent()) {
            throw new InformationNotFoundException("truck profile with id " + truckProfileId +
                    " not belongs to this user or recipe does not exist");
        }
        truckRepository.delete(oldTruckProfile.get());

    }

    /**
     * this creates the method to find all trucks
     * @return
     */
    public List<TruckProfile> findAll() {
        return truckRepository.findAll();
    }


}
