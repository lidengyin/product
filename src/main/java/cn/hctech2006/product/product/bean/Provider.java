package cn.hctech2006.product.product.bean;

import java.io.Serializable;

public class Provider implements Serializable {
    private Long id;

    private String name;

    private String shorts;

    private String address;

    private String zip;

    private String telephone;

    private String fax;

    private String contacts;

    private String contactsTel;

    private String bank;

    private String account;

    private String email;

    private Integer available;

    private String providerId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShorts() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts = shorts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsTel() {
        return contactsTel;
    }

    public void setContactsTel(String contactsTel) {
        this.contactsTel = contactsTel;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", shorts=").append(shorts);
        sb.append(", address=").append(address);
        sb.append(", zip=").append(zip);
        sb.append(", telephone=").append(telephone);
        sb.append(", fax=").append(fax);
        sb.append(", contacts=").append(contacts);
        sb.append(", contactsTel=").append(contactsTel);
        sb.append(", bank=").append(bank);
        sb.append(", account=").append(account);
        sb.append(", email=").append(email);
        sb.append(", available=").append(available);
        sb.append(", providerId=").append(providerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
}