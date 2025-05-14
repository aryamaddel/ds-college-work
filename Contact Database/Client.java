class Client {
    private String name;
    private String mobile;
    private String email;
    private String address;
    private boolean isActive;

    public Client(String name, String mobile, String email, String address) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.isActive = true;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Name: " + name +
               "\nMobile: " + mobile +
               "\nEmail: " + email +
               "\nAddress: " + address +
               "\nStatus: " + (isActive ? "Active" : "Inactive");
    }
}
