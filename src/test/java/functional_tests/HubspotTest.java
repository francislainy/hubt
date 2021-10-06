package functional_tests;

import functional_tests.model.Country;
import functional_tests.model.Partner;
import functional_tests.model.Partners;
import functional_tests.util.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static functional_tests.util.Util.getRequestSpecification;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;

// mvn -Dtest=functional_tests.*Test test


@Slf4j
public class HubspotTest {

    @Test
    public void getResponseFromApi() {

        RequestSpecification rq = getRequestSpecification();
        Response resp = rq.get("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=c967dc4a03fce49243388aef13f7");

        assertEquals(200, resp.getStatusCode());

        HashMap hashmap = resp.getBody().jsonPath().get();

        Partners partners = Util.createClassFromMap(hashmap, Partners.class);


        HashMap<String, ArrayList<String>> datePersonMap = new HashMap<>();
        HashMap<String, ArrayList<Partner>> countryPersonMap = new HashMap<>();
        ArrayList<Partner> partnerCountryList;

        ArrayList<Country> countriesToPostList = new ArrayList<>();
        HashMap<String, ArrayList<Country>> mapToPost = new HashMap();

        for (Partner p : partners.getPartners()) {

            if (countryPersonMap.containsKey(p.getCountry())) {

                partnerCountryList = countryPersonMap.get(p.getCountry());

            } else {
                partnerCountryList = new ArrayList<>();
            }

            partnerCountryList.add(p);
            countryPersonMap.put(p.getCountry(), partnerCountryList);

        }


        for (String countryName : countryPersonMap.keySet()) {

            ArrayList<String> attendeesList = new ArrayList<>();
            for (Partner p : countryPersonMap.get(countryName)) {

                ArrayList<String> partnersList;
                if (p.getAvailableDates() != null) {
                    for (int i = 0; i < p.getAvailableDates().size(); i++) {

                        // if the key already exists we add the partner to the array
                        if (datePersonMap.containsKey(p.getAvailableDates().get(i))) {
                            partnersList = datePersonMap.get(p.getAvailableDates().get(i));
                        } else {
                            partnersList = new ArrayList<>();
                        }

                        partnersList.add(p.getEmail());
                        datePersonMap.put(p.getAvailableDates().get(i), partnersList);
                    }

                }

            }


            TreeMap<String, ArrayList<String>> treeMap = new TreeMap<>();
            treeMap.putAll(datePersonMap); // Sorting the map to make sure the dates are in a sequence

            int max = 0;
            int keyIndexDate = 0;

            Object[] keys = treeMap.keySet().toArray();
            String dateWithMorePartners = (String) keys[0];

            // Map availabity inside the country
            for (int i = 0; i < keys.length - 1; i++) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                int partnerAmountCurrentDate = treeMap.get(keys[i]).size();
                int partnerAmountNextDate = treeMap.get(keys[i + 1]).size();

                LocalDate partnerCurrentLocalDate = LocalDate.parse((String) keys[i], formatter);
                LocalDate partnerNextLocalDate = LocalDate.parse((String) keys[i + 1], formatter);

                long daysBetween = DAYS.between(partnerCurrentLocalDate, partnerNextLocalDate);


                if (daysBetween == 1) { // Checking if the dates come in a sequence

                    if (partnerAmountCurrentDate + partnerAmountNextDate > max) {
                        max = partnerAmountCurrentDate + partnerAmountNextDate;

                        dateWithMorePartners = (String) keys[i];
                        keyIndexDate = i;
                    }
                }

            }


            System.out.println("Country: " + countryName);
            System.out.println("Date with more partners: " + dateWithMorePartners);


            attendeesList.addAll(treeMap.get(keys[keyIndexDate]));
            Country country = new Country();
            country.setAttendeeCount(attendeesList.size());
            country.setName(countryName);
            country.setStartDate(dateWithMorePartners);
            country.setAttendees(attendeesList);
            countriesToPostList.add(country);

        }


        mapToPost.put("countries", countriesToPostList);

        resp = rq.body(mapToPost).post("https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=c967dc4a03fce49243388aef13f7");
        assertEquals(200, resp.getStatusCode());

    }

}

