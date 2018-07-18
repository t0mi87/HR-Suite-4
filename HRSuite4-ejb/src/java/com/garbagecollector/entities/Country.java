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
@Table(name = "COUNTRIES")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Countries.findAll", query = "SELECT c FROM Country c")
  , @NamedQuery(name = "Countries.findByCountryId", query = "SELECT c FROM Country c WHERE c.countryId = :countryId")
  , @NamedQuery(name = "Countries.findByCountryName", query = "SELECT c FROM Country c WHERE c.countryName = :countryName")})
public class Country implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "COUNTRY_ID")
  private String countryId;
  @Column(name = "COUNTRY_NAME")
  private String countryName;
  @OneToMany(mappedBy = "countryId")
  private Collection<Location> locationsCollection;
  @JoinColumn(name = "REGION_ID", referencedColumnName = "REGION_ID")
  @ManyToOne
  private Region regionId;

  public Country() {
  }

  public Country(String countryId) {
    this.countryId = countryId;
  }

  public String getCountryId() {
    return countryId;
  }

  public void setCountryId(String countryId) {
    this.countryId = countryId;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  @XmlTransient
  public Collection<Location> getLocationsCollection() {
    return locationsCollection;
  }

  public void setLocationsCollection(Collection<Location> locationsCollection) {
    this.locationsCollection = locationsCollection;
  }

  public Region getRegionId() {
    return regionId;
  }

  public void setRegionId(Region regionId) {
    this.regionId = regionId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (countryId != null ? countryId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Country)) {
      return false;
    }
    Country other = (Country) object;
    if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Countries[ countryId=" + countryId + " ]";
  }

}
