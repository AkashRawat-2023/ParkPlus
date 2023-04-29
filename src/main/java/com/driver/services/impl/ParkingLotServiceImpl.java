package com.driver.services.impl;

import com.driver.entity.ParkingLot;
import com.driver.entity.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.driver.model.SpotType.*;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);


        return parkingLotRepository1.save(parkingLot);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {

        Spot spot = new Spot();
        if(numberOfWheels == 2) {
            spot.setSpotType(TWO_WHEELER);
        }else if(numberOfWheels == 4){
            spot.setSpotType(FOUR_WHEELER);
        }else{
            spot.setSpotType(OTHERS);
        }
        spot.setOccupied(false);
        spot.setPricePerHour(pricePerHour);

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLot.getSpotList().add(spot);

        spot.setParkingLot(parkingLot);
        parkingLotRepository1.save(parkingLot);
        return spot;


    }

    @Override
    public void deleteSpot(int spotId) {

    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        return null;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

    }
}
