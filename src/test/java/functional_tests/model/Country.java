package functional_tests.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Country {

    @SerializedName("attendeeCount")
    @Expose
    public Integer attendeeCount;
    @SerializedName("attendees")
    @Expose
    public List<String> attendees = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("startDate")
    @Expose
    public String startDate;

}
