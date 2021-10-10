package model;

import lombok.Data;

import java.util.List;

@Data
public class Partner {

    public String firstName;
    public String lastName;
    public String email;
    public String country;
    public List<String> availableDates = null;

}
