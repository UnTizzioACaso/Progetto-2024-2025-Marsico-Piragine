package bankapp.progetto20242025piragine.db;

public class User {

    // 🔹 Campi
    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String birthDate;
    private String birthPlace;
    private String gender;
    private String email;
    private String passwordHash;
    private String pinHash;
    private String phoneNumber;
    private String state;
    private String province;
    private String city;
    private String address;
    private String streetNumber;
    private String cap;
    private boolean rememberCredentials = false;
    private String lastAccessDate;
    private String theme = "light";


    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getBirthPlace() { return birthPlace; }
    public void setBirthPlace(String birthPlace) { this.birthPlace = birthPlace; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getStreetNumber() { return streetNumber; }
    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }

    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getPinHash() { return pinHash; }
    public void setPinHash(String pinHash) { this.pinHash = pinHash; }

    public boolean isRememberCredentials() { return rememberCredentials; }
    public void setRememberCredentials(boolean rememberCredentials) { this.rememberCredentials = rememberCredentials; }

    public String getLastAccessDate() { return lastAccessDate; }
    public void setLastAccessDate(String lastAccessDate) { this.lastAccessDate = lastAccessDate; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
}
