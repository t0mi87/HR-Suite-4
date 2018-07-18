/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "REGIONS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Regions.findAll", query = "SELECT r FROM Region r")
  , @NamedQuery(name = "Regions.findByRegionId", query = "SELECT r FROM Region r WHERE r.regionId = :regionId")
  , @NamedQuery(name = "Regions.findByRegionName", query = "SELECT r FROM Region r WHERE r.regionName = :regionName")})
public class Region implements Serializable {

  private static final long serialVersionUID = 1L;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
  @Basic(optional = false)
  @Column(name = "REGION_ID")
  private BigDecimal regionId;
  @Column(name = "REGION_NAME")
  private String regionName;
  @OneToMany(mappedBy = "regionId")
  private Collection<Country> countriesCollection;

  public Region() {
  }

  public Region(BigDecimal regionId) {
    this.regionId = regionId;
  }

  public BigDecimal getRegionId() {
    return regionId;
  }

  public void setRegionId(BigDecimal regionId) {
    this.regionId = regionId;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  @XmlTransient
  public Collection<Country> getCountriesCollection() {
    return countriesCollection;
  }

  public void setCountriesCollection(Collection<Country> countriesCollection) {
    this.countriesCollection = countriesCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (regionId != null ? regionId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Region)) {
      return false;
    }
    Region other = (Region) object;
    if ((this.regionId == null && other.regionId != null) || (this.regionId != null && !this.regionId.equals(other.regionId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Regions[ regionId=" + regionId + " ]";
  }

}
