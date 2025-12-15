package bankapp.progetto20242025piragine.db;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String birthDate;
    private String birthPlace;
    private String gender;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String state;
    private String province;
    private String city;
    private String address;
    private String streetNumber;
    private String cap;
    private String pinHash;
    private boolean rememberCredentials;
    private String lastAccessDate;
    private String theme;
    public void setCap(String cap)
    {
        this.cap = cap;
    }
    public String getCap()
    {
        return this.cap;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return this.address;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public  void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }
    public String getPasswordHash()
    {
        return this.passwordHash;
    }
    public  void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public   void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public  void setUsername(String userName)
    {
        this.username = userName;
    }
    public String getUsername()
    {
        return this.username;
    }
    public  void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
    }
    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthPlace(String birthPlace)
    {
        this.birthPlace = birthPlace;
    }
    public String getBirthPlace()
    {
        return this.birthPlace;
    }


    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return this.state;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return this.province;
    }


    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getLastAccessDate()
    {
        return lastAccessDate;
    }

    public void setLastAccessDate(String lastAccessDate)
    {
        this.lastAccessDate = lastAccessDate;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public boolean isRememberCredentials()
    {
        return rememberCredentials;
    }

    public void setRememberCredentials (boolean rememberCredentials)
    {
        this.rememberCredentials = rememberCredentials;
    }


    public String getLastAccess()
    {
        return lastAccessDate;
    }

    public void setLastAccess(String lastAccess)
    {
        this.lastAccessDate = lastAccess;
    }

    public String getPinHash()
    {
        return pinHash;
    }

    public void setPinHash(String pinHash)
    {
        this.pinHash = pinHash;
    }

    public String getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }
}