package com.property.propertyservice;

import java.util.List;

public interface PropertyService {
    //This method pulls out all the Property list from database
    List<Property> getAllProperties();
    //This method gets all occupied properties sorted in descending order
    List<Property> getAllOccupiedProperties();
}
