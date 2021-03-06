package edu.mum.cs.cs544.service;

import edu.mum.cs.cs544.dao.LocationRepository;
import edu.mum.cs.cs544.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> getAll(Sort sort) {
        if (sort == null) {
            sort = Sort.by(Direction.ASC, "description");
        }
        return locationRepository.findAll(sort);
    }

    @Override
    public Optional<Location> getById(int id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location create(Location loc) {
        return locationRepository.save(loc);

    }

    @Override
    public Location update(Location loc) {
        return locationRepository.save(loc);
    }

    @Override
    public void remove(int id) {
        locationRepository.deleteById(id);
	}
}
