package com.garbagecollector.entities;

import com.garbagecollector.entities.Country;
import com.garbagecollector.entities.Department;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T11:34:06")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, String> streetAddress;
    public static volatile SingularAttribute<Location, String> city;
    public static volatile SingularAttribute<Location, Short> locationId;
    public static volatile SingularAttribute<Location, String> postalCode;
    public static volatile SingularAttribute<Location, String> stateProvince;
    public static volatile CollectionAttribute<Location, Department> departmentsCollection;
    public static volatile SingularAttribute<Location, Country> countryId;

}