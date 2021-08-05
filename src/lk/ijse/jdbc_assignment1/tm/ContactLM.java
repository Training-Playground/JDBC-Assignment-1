package lk.ijse.jdbc_assignment1.tm;

import java.io.Serializable;

public class ContactLM implements Serializable {
    private String contact;
    private int providerID;
    private String providerDescriptio;

    public ContactLM(String contact, int providerID, String providerDescriptio) {
        this.contact = contact;
        this.providerID = providerID;
        this.providerDescriptio = providerDescriptio;
    }

    public ContactLM() {
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public String getProviderDescriptio() {
        return providerDescriptio;
    }

    public void setProviderDescriptio(String providerDescriptio) {
        this.providerDescriptio = providerDescriptio;
    }

    @Override
    public String toString() {
        return contact;
    }
}
