package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SiteInformation")
public class SiteInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1665702284257147088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer siteId;

	private String siteLocation;

	private String siteDescription;

	private Double sitePrice;

	private Date siteRushBegin;

	private Date siteRushEnd;

	private Double rushDiscount;

	private Date siteFlatBegin;

	private Date siteFlatEnd;

	private Double flatDiscount;

	private Date siteNightBegin;

	private Date siteNightEnd;

	private Double nightDicount;

	private Double specialDayDiscount;

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteLocation() {
		return siteLocation;
	}

	public void setSiteLocation(String siteLocation) {
		this.siteLocation = siteLocation;
	}

	public String getSiteDescription() {
		return siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public Double getSitePrice() {
		return sitePrice;
	}

	public void setSitePrice(Double sitePrice) {
		this.sitePrice = sitePrice;
	}

	public Date getSiteRushBegin() {
		return siteRushBegin;
	}

	public void setSiteRushBegin(Date siteRushBegin) {
		this.siteRushBegin = siteRushBegin;
	}

	public Date getSiteRushEnd() {
		return siteRushEnd;
	}

	public void setSiteRushEnd(Date siteRushEnd) {
		this.siteRushEnd = siteRushEnd;
	}

	public Double getRushDiscount() {
		return rushDiscount;
	}

	public void setRushDiscount(Double rushDiscount) {
		this.rushDiscount = rushDiscount;
	}

	public Date getSiteFlatBegin() {
		return siteFlatBegin;
	}

	public void setSiteFlatBegin(Date siteFlatBegin) {
		this.siteFlatBegin = siteFlatBegin;
	}

	public Date getSiteFlatEnd() {
		return siteFlatEnd;
	}

	public void setSiteFlatEnd(Date siteFlatEnd) {
		this.siteFlatEnd = siteFlatEnd;
	}

	public Double getFlatDiscount() {
		return flatDiscount;
	}

	public void setFlatDiscount(Double flatDiscount) {
		this.flatDiscount = flatDiscount;
	}

	public Date getSiteNightBegin() {
		return siteNightBegin;
	}

	public void setSiteNightBegin(Date siteNightBegin) {
		this.siteNightBegin = siteNightBegin;
	}

	public Date getSiteNightEnd() {
		return siteNightEnd;
	}

	public void setSiteNightEnd(Date siteNightEnd) {
		this.siteNightEnd = siteNightEnd;
	}

	public Double getNightDicount() {
		return nightDicount;
	}

	public void setNightDicount(Double nightDicount) {
		this.nightDicount = nightDicount;
	}

	public Double getSpecialDayDiscount() {
		return specialDayDiscount;
	}

	public void setSpecialDayDiscount(Double specialDayDiscount) {
		this.specialDayDiscount = specialDayDiscount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flatDiscount == null) ? 0 : flatDiscount.hashCode());
		result = prime * result
				+ ((nightDicount == null) ? 0 : nightDicount.hashCode());
		result = prime * result
				+ ((rushDiscount == null) ? 0 : rushDiscount.hashCode());
		result = prime * result
				+ ((siteDescription == null) ? 0 : siteDescription.hashCode());
		result = prime * result
				+ ((siteFlatBegin == null) ? 0 : siteFlatBegin.hashCode());
		result = prime * result
				+ ((siteFlatEnd == null) ? 0 : siteFlatEnd.hashCode());
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
		result = prime * result
				+ ((siteLocation == null) ? 0 : siteLocation.hashCode());
		result = prime * result
				+ ((siteNightBegin == null) ? 0 : siteNightBegin.hashCode());
		result = prime * result
				+ ((siteNightEnd == null) ? 0 : siteNightEnd.hashCode());
		result = prime * result
				+ ((sitePrice == null) ? 0 : sitePrice.hashCode());
		result = prime * result
				+ ((siteRushBegin == null) ? 0 : siteRushBegin.hashCode());
		result = prime * result
				+ ((siteRushEnd == null) ? 0 : siteRushEnd.hashCode());
		result = prime
				* result
				+ ((specialDayDiscount == null) ? 0 : specialDayDiscount
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SiteInformation other = (SiteInformation) obj;
		if (flatDiscount == null) {
			if (other.flatDiscount != null)
				return false;
		} else if (!flatDiscount.equals(other.flatDiscount))
			return false;
		if (nightDicount == null) {
			if (other.nightDicount != null)
				return false;
		} else if (!nightDicount.equals(other.nightDicount))
			return false;
		if (rushDiscount == null) {
			if (other.rushDiscount != null)
				return false;
		} else if (!rushDiscount.equals(other.rushDiscount))
			return false;
		if (siteDescription == null) {
			if (other.siteDescription != null)
				return false;
		} else if (!siteDescription.equals(other.siteDescription))
			return false;
		if (siteFlatBegin == null) {
			if (other.siteFlatBegin != null)
				return false;
		} else if (!siteFlatBegin.equals(other.siteFlatBegin))
			return false;
		if (siteFlatEnd == null) {
			if (other.siteFlatEnd != null)
				return false;
		} else if (!siteFlatEnd.equals(other.siteFlatEnd))
			return false;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
			return false;
		if (siteLocation == null) {
			if (other.siteLocation != null)
				return false;
		} else if (!siteLocation.equals(other.siteLocation))
			return false;
		if (siteNightBegin == null) {
			if (other.siteNightBegin != null)
				return false;
		} else if (!siteNightBegin.equals(other.siteNightBegin))
			return false;
		if (siteNightEnd == null) {
			if (other.siteNightEnd != null)
				return false;
		} else if (!siteNightEnd.equals(other.siteNightEnd))
			return false;
		if (sitePrice == null) {
			if (other.sitePrice != null)
				return false;
		} else if (!sitePrice.equals(other.sitePrice))
			return false;
		if (siteRushBegin == null) {
			if (other.siteRushBegin != null)
				return false;
		} else if (!siteRushBegin.equals(other.siteRushBegin))
			return false;
		if (siteRushEnd == null) {
			if (other.siteRushEnd != null)
				return false;
		} else if (!siteRushEnd.equals(other.siteRushEnd))
			return false;
		if (specialDayDiscount == null) {
			if (other.specialDayDiscount != null)
				return false;
		} else if (!specialDayDiscount.equals(other.specialDayDiscount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SiteInformation [siteId=" + siteId + ", siteLocation="
				+ siteLocation + ", siteDescription=" + siteDescription
				+ ", sitePrice=" + sitePrice + ", siteRushBegin="
				+ siteRushBegin + ", siteRushEnd=" + siteRushEnd
				+ ", rushDiscount=" + rushDiscount + ", siteFlatBegin="
				+ siteFlatBegin + ", siteFlatEnd=" + siteFlatEnd
				+ ", flatDiscount=" + flatDiscount + ", siteNightBegin="
				+ siteNightBegin + ", siteNightEnd=" + siteNightEnd
				+ ", nightDicount=" + nightDicount + ", specialDayDiscount="
				+ specialDayDiscount + "]";
	}

}
