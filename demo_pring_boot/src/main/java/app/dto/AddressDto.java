package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {
    @JsonProperty("street")
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
