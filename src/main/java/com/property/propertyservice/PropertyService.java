package com.property.propertyservice;

import java.util.List;

public interface PropertyService {
    List<Property> getAllProperties();

    List<Property> getAllOccupiedProperties();
}
