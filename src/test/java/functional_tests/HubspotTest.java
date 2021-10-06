package functional_tests;

import functional_tests.model.Partner;
import functional_tests.model.Partners;
import functional_tests.util.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static functional_tests.util.Util.getRequestSpecification;
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

        // todo: each country
        HashMap<String, ArrayList<String>> datePersonMap = new HashMap<>();
        HashMap<String, ArrayList<Partner>> countryPersonMap = new HashMap<>();
        ArrayList<Partner> countryList;
        for (Partner p : partners.getPartners()) {

            if (countryPersonMap.containsKey(p.getCountry())) {

                ArrayList<String> partnersList;
                if (p.getAvailableDates() != null) {
                    for (int i = 0; i < p.getAvailableDates().size(); i++) {

                        // if key already exists add partner to their array
                        if (datePersonMap.containsKey(p.getAvailableDates().get(i))) {
                            partnersList = datePersonMap.get(p.getAvailableDates().get(i));
                        } else {
                            partnersList = new ArrayList<>();
                        }

                        partnersList.add(p.getFirstName());
                        datePersonMap.put(p.getAvailableDates().get(i), partnersList);
                    }

                }

                countryList = countryPersonMap.get(p.getCountry());

            }
            else {
                countryList = new ArrayList<>();
            }

            countryList.add(p);
            countryPersonMap.put(p.getCountry(), countryList);

        }


        TreeMap<String, ArrayList<String>> treeMap = new TreeMap<>();
        treeMap.putAll(datePersonMap);

        int max = 0;
        int partnerAmountCurrentDate;
        int partnerAmountNextDate;



        Object[] keys = treeMap.keySet().toArray();
        String dateWithMorePartners = (String) keys[0];
        for (int i = 0; i < keys.length - 1; i++) {


            partnerAmountCurrentDate = treeMap.get(keys[i]).size();
            partnerAmountNextDate = treeMap.get(keys[i + 1]).size();

            if (partnerAmountCurrentDate + partnerAmountNextDate > max) {
                max = partnerAmountCurrentDate + partnerAmountNextDate;
                dateWithMorePartners = (String) keys[i];
            }

        }

        System.out.println(dateWithMorePartners);


    }


}

