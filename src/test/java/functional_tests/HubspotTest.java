package functional_tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    public void testHubspot() {

        Partners partners = getPartnersFromRest();


        ArrayList<Country> countriesToPostList = new ArrayList<>();
        HashMap<String, ArrayList<Country>> mapToPost = new HashMap<>();

        // Here we have something like "Canada": {"P1":{}, "P2"" {}}, "Singapore": {"P3": {}, "P4"}, "United Stated": {}
        HashMap<String, ArrayList<Partner>> countryPersonMap = getCountryAvailablePartners(partners);

        // This gives us a map like  { "2017-06-16" : "["attendee1@emai.com", "attendee2@email.com", "attendee3@emai.com"]", "2017-06-16" : "["attendee2@emai.com", "attendee4@emai.com"]", "2017-06-16" : "[]", "2017-06-16" : "[]"}
        for (String countryName : countryPersonMap.keySet()) {

            HashMap<String, ArrayList<String>> datePersonMap = getDateAvailablePartners(countryPersonMap, countryName);


            ArrayList<String> attendeesList = new ArrayList<>();
            TreeMap<String, ArrayList<String>> treeMap = new TreeMap<>(datePersonMap); // Sorting the map to make sure the dates are in a sequence

            int max = 0;

            Object[] dates = treeMap.keySet().toArray();
            String startDateWithMorePartners = (String) dates[0];
            String endDateWithMorePartners = (String) dates[0];

            // Map availability inside the country
            for (int i = 0; i < dates.length - 1; i++) {

                if (getDaysBetween(dates, i) == 1) { // Checking if the dates come in a sequence

                    ArrayList<String> currentDateList = treeMap.get(dates[i]);
                    ArrayList<String> nextDateList = treeMap.get(dates[i + 1]);

                    Collections.sort(currentDateList);
                    Collections.sort(nextDateList);

                    // Wrong because it's checking whether all in the array for a date are inside the array for the other date. It should be the most amount only.

                    int numElementsBothDates = 0;


                    for (String nextDate : nextDateList) {
                        for (String currentDate : currentDateList) {
                            if (currentDate.contains(nextDate)) {
                                numElementsBothDates++;
                            }
                        }
                    }


                    if (numElementsBothDates > max) {
                        max = numElementsBothDates;


                        startDateWithMorePartners = (String) dates[i];
                        endDateWithMorePartners = (String) dates[i + 1];


                    }


                }


            }


            System.out.println("Country: " + countryName);
            System.out.println("Date with more partners: " + startDateWithMorePartners);
            System.out.println("Attendee count: " + max);


            for (Partner p : partners.getPartners()) {

                if (p.getCountry().equals(countryName)) {
                    if (p.getAvailableDates().contains(startDateWithMorePartners) && p.getAvailableDates().contains(endDateWithMorePartners)) {
                        attendeesList.add(p.getEmail());
                    }
                }

            }


            if (attendeesList.size() == 0) {
                startDateWithMorePartners = null;
            }


            Country country = new Country();
            country.setAttendeeCount(attendeesList.size());
            country.setName(countryName);
            country.setStartDate(startDateWithMorePartners);
            country.setAttendees(attendeesList);
            countriesToPostList.add(country);

        }


        mapToPost.put("countries", countriesToPostList);

        String serialisedMap = Util.createJsonStringFromClassObject(mapToPost);

        RequestSpecification rq = getRequestSpecification();
        Response resp = rq.body(serialisedMap).post("https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=c967dc4a03fce49243388aef13f7");
        assertEquals(200, resp.getStatusCode());

    }

    private Partners getPartnersFromRest() {

        RequestSpecification rq = getRequestSpecification();
        Response resp = rq.get("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=c967dc4a03fce49243388aef13f7");

        assertEquals(200, resp.getStatusCode());

        HashMap hashmap = resp.getBody().jsonPath().get();

        return Util.createClassFromMap(hashmap, Partners.class);
    }

    private long getDaysBetween(Object[] keys, int i) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate partnerCurrentLocalDate = LocalDate.parse((String) keys[i], formatter);
        LocalDate partnerNextLocalDate = LocalDate.parse((String) keys[i + 1], formatter);

        return DAYS.between(partnerCurrentLocalDate, partnerNextLocalDate);
    }

    private HashMap getDateAvailablePartners(HashMap<String, ArrayList<Partner>> countryPersonMap, String countryName) {

        HashMap<String, ArrayList<String>> datePersonMap = new HashMap<>();

        for (Partner p : countryPersonMap.get(countryName)) {

            ArrayList<String> partnersList;

            for (String date : p.getAvailableDates()) {

                // if the key already exists we add the partner to the array
                if (datePersonMap.containsKey(date)) {
                    partnersList = datePersonMap.get(date);
                } else {
                    partnersList = new ArrayList<>();
                }

                partnersList.add(p.getEmail());
                datePersonMap.put((date), partnersList);
            }

        }

        return datePersonMap;
    }

    private HashMap getCountryAvailablePartners(Partners partners) {
        HashMap<String, ArrayList<Partner>> countryPersonMap = new HashMap<>();
        for (Partner p : partners.getPartners()) {

            ArrayList<Partner> partnerCountryList;
            if (countryPersonMap.containsKey(p.getCountry())) {

                partnerCountryList = countryPersonMap.get(p.getCountry());

            } else {
                partnerCountryList = new ArrayList<>();
            }


            partnerCountryList.add(p);
            countryPersonMap.put(p.getCountry(), partnerCountryList);

        }

        return countryPersonMap;
    }

}

