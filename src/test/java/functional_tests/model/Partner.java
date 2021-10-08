package functional_tests.model;

import java.util.List;
import lombok.Data;

@Data
public class Partner {

    public String firstName;
    public String lastName;
    public String email;
    public String country;
    public List<String> availableDates = null;

}
