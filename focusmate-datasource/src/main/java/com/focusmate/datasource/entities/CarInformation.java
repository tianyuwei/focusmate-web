package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CarInformation")
public class CarInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5694836180766335471L;

	@Id
	private Integer userId;

	private String carBrand;

	private String carModel;

	private String carLicense;

	private Date firstLicenseDay;

	private Date lastMaintenanceDay;

	private Date lastWashDay;

	private String carStyle;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarLicense() {
		return carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public Date getFirstLicenseDay() {
		return firstLicenseDay;
	}

	public void setFirstLicenseDay(Date firstLicenseDay) {
		this.firstLicenseDay = firstLicenseDay;
	}

	public Date getLastMaintenanceDay() {
		return lastMaintenanceDay;
	}

	public void setLastMaintenanceDay(Date lastMaintenanceDay) {
		this.lastMaintenanceDay = lastMaintenanceDay;
	}

	public Date getLastWashDay() {
		return lastWashDay;
	}

	public void setLastWashDay(Date lastWashDay) {
		this.lastWashDay = lastWashDay;
	}

	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carBrand == null) ? 0 : carBrand.hashCode());
		result = prime * result
				+ ((carLicense == null) ? 0 : carLicense.hashCode());
		result = prime * result
				+ ((carModel == null) ? 0 : carModel.hashCode());
		result = prime * result
				+ ((carStyle == null) ? 0 : carStyle.hashCode());
		result = prime * result
				+ ((firstLicenseDay == null) ? 0 : firstLicenseDay.hashCode());
		result = prime
				* result
				+ ((lastMaintenanceDay == null) ? 0 : lastMaintenanceDay
						.hashCode());
		result = prime * result
				+ ((lastWashDay == null) ? 0 : lastWashDay.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		CarInformation other = (CarInformation) obj;
		if (carBrand == null) {
			if (other.carBrand != null)
				return false;
		} else if (!carBrand.equals(other.carBrand))
			return false;
		if (carLicense == null) {
			if (other.carLicense != null)
				return false;
		} else if (!carLicense.equals(other.carLicense))
			return false;
		if (carModel == null) {
			if (other.carModel != null)
				return false;
		} else if (!carModel.equals(other.carModel))
			return false;
		if (carStyle == null) {
			if (other.carStyle != null)
				return false;
		} else if (!carStyle.equals(other.carStyle))
			return false;
		if (firstLicenseDay == null) {
			if (other.firstLicenseDay != null)
				return false;
		} else if (!firstLicenseDay.equals(other.firstLicenseDay))
			return false;
		if (lastMaintenanceDay == null) {
			if (other.lastMaintenanceDay != null)
				return false;
		} else if (!lastMaintenanceDay.equals(other.lastMaintenanceDay))
			return false;
		if (lastWashDay == null) {
			if (other.lastWashDay != null)
				return false;
		} else if (!lastWashDay.equals(other.lastWashDay))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarInformation [userId=" + userId + ", carBrand=" + carBrand
				+ ", carModel=" + carModel + ", carLicense=" + carLicense
				+ ", firstLicenseDay=" + firstLicenseDay
				+ ", lastMaintenanceDay=" + lastMaintenanceDay
				+ ", lastWashDay=" + lastWashDay + ", carStyle=" + carStyle
				+ "]";
	}

}
