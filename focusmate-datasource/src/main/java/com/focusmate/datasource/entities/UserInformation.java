package com.focusmate.datasource.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserInformation")
public class UserInformation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 4585567249909236235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private String userBand;

    private String cellPhone;

    private String nickName;

    private String trueName;

    private Integer gender;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBand() {
        return userBand;
    }

    public void setUserBand(String userBand) {
        this.userBand = userBand;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cellPhone == null) ? 0 : this.cellPhone.hashCode());
        result = prime * result + ((this.gender == null) ? 0 : this.gender.hashCode());
        result = prime * result + ((this.nickName == null) ? 0 : this.nickName.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.trueName == null) ? 0 : this.trueName.hashCode());
        result = prime * result + ((this.userBand == null) ? 0 : this.userBand.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
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
        UserInformation other = (UserInformation) obj;
        if (this.cellPhone == null) {
            if (other.cellPhone != null)
                return false;
        } else if (!this.cellPhone.equals(other.cellPhone))
            return false;
        if (this.gender == null) {
            if (other.gender != null)
                return false;
        } else if (!this.gender.equals(other.gender))
            return false;
        if (this.nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!this.nickName.equals(other.nickName))
            return false;
        if (this.password == null) {
            if (other.password != null)
                return false;
        } else if (!this.password.equals(other.password))
            return false;
        if (this.trueName == null) {
            if (other.trueName != null)
                return false;
        } else if (!this.trueName.equals(other.trueName))
            return false;
        if (this.userBand == null) {
            if (other.userBand != null)
                return false;
        } else if (!this.userBand.equals(other.userBand))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        } else if (!this.userId.equals(other.userId))
            return false;
        if (this.userName == null) {
            if (other.userName != null)
                return false;
        } else if (!this.userName.equals(other.userName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserInformation [userId=" + this.userId + ", userName=" + this.userName + ", userBand=" + this.userBand
                + ", cellPhone=" + this.cellPhone + ", nickName=" + this.nickName + ", trueName=" + this.trueName
                + ", gender=" + this.gender + ", password=" + this.password + "]";
    }

}
