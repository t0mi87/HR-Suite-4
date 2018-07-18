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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "LOCATIONS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Locations.findAll", query = "SELECT l FROM Location l")
  , @NamedQuery(name = "Locations.findByLocationId", query = "SELECT l FROM Location l WHERE l.locationId = :locationId")
  , @NamedQuery(name = "Locations.findByStreetAddress", query = "SELECT l FROM Location l WHERE l.streetAddress = :streetAddress")
  , @NamedQuery(name = "Locations.findByPostalCode", query = "SELECT l FROM Location l WHERE l.postalCode = :postalCode")
  , @NamedQuery(name = "Locations.findByCity", query = "SELECT l FROM Location l WHERE l.city = :city")
  , @NamedQuery(name = "Locations.findByStateProvince", query = "SELECT l FROM Location l WHERE l.stateProvince = :stateProvince")})
public class Location implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "LOCATION_ID")
  private Short locationId;
  @Column(name = "STREET_ADDRESS")
  private String streetAddress;
  @Column(name = "POSTAL_CODE")
  private String postalCode;
  @Basic(optional = false)
  @Column(name = "CITY")
  private String city;
  @Column(name = "STATE_PROVINCE")
  private String stateProvince;
  @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID")
  @ManyToOne
  private Country countryId;
  @OneToMany(mappedBy = "locationId")
  private Collection<Department> departmentsCollection;

  public Location() {
  }

  public Location(Short locationId) {
    this.locationId = locationId;
  }

  public Location(Short locationId, String city) {
    this.locationId = locationId;
    this.city = city;
  }

  public Short getLocationId() {
    return locationId;
  }

  public void setLocationId(Short locationId) {
    this.locationId = locationId;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStateProvince() {
    return stateProvince;
  }

  public void setStateProvince(String stateProvince) {
    this.stateProvince = stateProvince;
  }

  public Country getCountryId() {
    return countryId;
  }

  public void setCountryId(Country countryId) {
    this.countryId = countryId;
  }

  @XmlTransient
  public Collection<Department> getDepartmentsCollection() {
    return departmentsCollection;
  }

  public void setDepartmentsCollection(Collection<Department> departmentsCollection) {
    this.departmentsCollection = departmentsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (locationId != null ? locationId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Location)) {
      return false;
    }
    Location other = (Location) object;
    if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Locations[ locationId=" + locationId + " ]";
  }

}
