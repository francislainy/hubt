package functional_tests.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Countries {

    @SerializedName("countries")
    @Expose
    public List<Country> countries = null;

}
