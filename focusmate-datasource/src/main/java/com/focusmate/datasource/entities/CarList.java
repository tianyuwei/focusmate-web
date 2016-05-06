package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CarList")
public class CarList implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4452057079790176361L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autoKeyValue;

    private String carBrand;

    private String carModel;

    private String carStyle;

    private String pictureAddress;

    private String maintanceTrip;

    private Date maintenancePeriod;

    public Integer getAutoKeyValue() {
        return autoKeyValue;
    }

    public void setAutoKeyValue(Integer autoKeyValue) {
        this.autoKeyValue = autoKeyValue;
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

    public String getCarStyle() {
        return carStyle;
    }

    public void setCarStyle(String carStyle) {
        this.carStyle = carStyle;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public String getMaintanceTrip() {
        return maintanceTrip;
    }

    public void setMaintanceTrip(String maintanceTrip) {
        this.maintanceTrip = maintanceTrip;
    }

    public Date getMaintenancePeriod() {
        return maintenancePeriod;
    }

    public void setMaintenancePeriod(Date maintenancePeriod) {
        this.maintenancePeriod = maintenancePeriod;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((autoKeyValue == null) ? 0 : autoKeyValue.hashCode());
        result = prime * result + ((carBrand == null) ? 0 : carBrand.hashCode());
        result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
        result = prime * result + ((carStyle == null) ? 0 : carStyle.hashCode());
        result = prime * result + ((maintanceTrip == null) ? 0 : maintanceTrip.hashCode());
        result = prime * result + ((maintenancePeriod == null) ? 0 : maintenancePeriod.hashCode());
        result = prime * result + ((pictureAddress == null) ? 0 : pictureAddress.hashCode());
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
        CarList other = (CarList) obj;
        if (autoKeyValue == null) {
            if (other.autoKeyValue != null)
                return false;
        } else if (!autoKeyValue.equals(other.autoKeyValue))
            return false;
        if (carBrand == null) {
            if (other.carBrand != null)
                return false;
        } else if (!carBrand.equals(other.carBrand))
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
        if (maintanceTrip == null) {
            if (other.maintanceTrip != null)
                return false;
        } else if (!maintanceTrip.equals(other.maintanceTrip))
            return false;
        if (maintenancePeriod == null) {
            if (other.maintenancePeriod != null)
                return false;
        } else if (!maintenancePeriod.equals(other.maintenancePeriod))
            return false;
        if (pictureAddress == null) {
            if (other.pictureAddress != null)
                return false;
        } else if (!pictureAddress.equals(other.pictureAddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CarList [autoKeyValue=" + autoKeyValue + ", carBrand=" + carBrand + ", carModel=" + carModel
                + ", carStyle=" + carStyle + ", pictureAddress=" + pictureAddress + ", maintanceTrip=" + maintanceTrip
                + ", maintenancePeriod=" + maintenancePeriod + "]";
    }

}
