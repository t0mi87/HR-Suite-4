/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Users.findAll", query = "SELECT u FROM User u")
  , @NamedQuery(name = "Users.findById", query = "SELECT u FROM User u WHERE u.id = :id")
  , @NamedQuery(name = "Users.findByPass", query = "SELECT u FROM User u WHERE u.pass = :pass")
  , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "PASS")
  private String pass;
  @Column(name = "USERNAME")
  private String username;
  @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
  @ManyToOne
  private Role roleId;

  public User() {
  }

  public User(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Role getRoleId() {
    return roleId;
  }

  public void setRoleId(Role roleId) {
    this.roleId = roleId;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Users[ id=" + id + " ]";
  }

}
