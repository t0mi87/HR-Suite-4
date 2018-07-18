/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Role r")
  , @NamedQuery(name = "Roles.findById", query = "SELECT r FROM Role r WHERE r.id = :id")
  , @NamedQuery(name = "Roles.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")})
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "NAME")
  private String name;
  @OneToMany(mappedBy = "roleId")
  private Collection<User> usersCollection;

  public Role() {
  }

  public Role(Integer id) {
    this.id = id;
  }

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

  @XmlTransient
  public Collection<User> getUsersCollection() {
    return usersCollection;
  }

  public void setUsersCollection(Collection<User> usersCollection) {
    this.usersCollection = usersCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Role)) {
      return false;
    }
    Role other = (Role) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Roles[ id=" + id + " ]";
  }

}
