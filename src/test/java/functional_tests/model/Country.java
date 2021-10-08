package functional_tests.model;

import java.util.List;
import lombok.Data;

@Data
public class Country {

    public Integer attendeeCount;
    public List<String> attendees = null;
    public String name;
    public String startDate;

}
