package com.garbagecollector.entities;

import com.garbagecollector.entities.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T11:34:06")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> pass;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> username;

}