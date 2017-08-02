package com.saim.bloodrose.Model;

/**
 * Created by Android on 7/31/2017.
 */

public class ModelDoner {

    String bloodDonorID, emailAddress, phoneNumber, fullName,
            presentAddress, birthDate, bloodGroup,donatedDate,
            registeredDate, locationID, locationName;

    public ModelDoner(String bloodDonorID, String emailAddress, String phoneNumber, String fullName, String presentAddress, String birthDate, String bloodGroup, String donatedDate, String registeredDate, String locationID, String locationName) {
        this.bloodDonorID = bloodDonorID;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.presentAddress = presentAddress;
        this.birthDate = birthDate;
        this.bloodGroup = bloodGroup;
        this.donatedDate = donatedDate;
        this.registeredDate = registeredDate;
        this.locationID = locationID;
        this.locationName = locationName;
    }

    public String getBloodDonorID() {
        return bloodDonorID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getDonatedDate() {
        return donatedDate;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public String getLocationID() {
        return locationID;
    }

    public String getLocationName() {
        return locationName;
    }
}
