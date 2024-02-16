package lk.jiat.app.varandesigns.modals;

public class User {
    String name;
    String email;
    String password;
    String profileImage;
    String contact;
    String address;

    public User() {
    }

    public User(String name, String email, String password, String contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }



    public User(String name, String email, String password, String profileImage, String contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.contact = contact;
    }


    public User(String name, String email, String password, String profileImage, String contact, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.contact = contact;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
