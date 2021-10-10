package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {

    public Integer attendeeCount;
    public List<String> attendees = null;
    public String name;
    public String startDate;

}
