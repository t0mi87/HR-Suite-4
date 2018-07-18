
package com.garbagecollector.dtos;

import com.garbagecollector.entities.User;
import java.util.Collection;


public class RoleDTO {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<User> usersCollection) {
        this.usersCollection = usersCollection;
    }
    
   private Integer id;

  private String name;
  
  private Collection<User> usersCollection;
}
