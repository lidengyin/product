package cn.hctech2006.product.product.view.tv;

import javafx.beans.property.SimpleStringProperty;

public class ProviderTV {
    private SimpleStringProperty name;

    private SimpleStringProperty shorts;

    private SimpleStringProperty address;

    private SimpleStringProperty zip;

    private SimpleStringProperty telephone;

    private SimpleStringProperty fax;

    private SimpleStringProperty contacts;


    private SimpleStringProperty providerId;
    private SimpleStringProperty number;

    public ProviderTV(String name, String shorts
            , String address, String zip
            , String telephone, String fax
            , String contacts, String providerId) {
        this.name = new SimpleStringProperty(name);
        this.shorts = new SimpleStringProperty(shorts);
        this.address = new SimpleStringProperty(address);
        this.zip = new SimpleStringProperty(zip);
        this.telephone = new SimpleStringProperty(telephone);
        this.fax = new SimpleStringProperty(fax);
        this.contacts = new SimpleStringProperty(contacts);
        this.providerId = new SimpleStringProperty(providerId);
    }



    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShorts() {
        return shorts.get();
    }

    public SimpleStringProperty shortsProperty() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts.set(shorts);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getZip() {
        return zip.get();
    }

    public SimpleStringProperty zipProperty() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getFax() {
        return fax.get();
    }

    public SimpleStringProperty faxProperty() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax.set(fax);
    }

    public String getContacts() {
        return contacts.get();
    }

    public SimpleStringProperty contactsProperty() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts.set(contacts);
    }

    public String getProviderId() {
        return providerId.get();
    }

    public SimpleStringProperty providerIdProperty() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId.set(providerId);
    }
}
