package com.property.propertyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> getAllOccupiedProperties() {
        return getAllProperties().stream().filter(Property::isOccupied).sorted(Comparator.comparing(Property::getDate).reversed()).toList();
    }
}