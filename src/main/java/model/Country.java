package model;

import lombok.Data;

import java.util.List;

@Data
public class Country {

    public Integer attendeeCount;
    public List<String> attendees = null;
    public String name;
    public String startDate;

}
