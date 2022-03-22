package stepDefinitions;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 Feature: Application Login

 Scenario: Home page default login
 Given User is on NetBanking landing page
 When User login into application with username and password
 Then Home page is populated
 And Cards are displayed
 */
public class stepDefinition {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @Given("^User is on NetBanking landing page$")
    public void user_is_on_NetBanking_Landing() {
        System.out.println("user_is_on_NetBanking_Landing");
    }

    @Given(": a map")
    public void a_map(io.cucumber.datatable.DataTable dataTable) {
//        List<List<String>> rows = dataTable.asLists(String.class);
//        List<CountryStateEntry> countryStateEntries = new ArrayList<>();
//        int header = 1;
//        for(List<String> row : rows) {
//            if(header == 1) {
//                ++header;
//                continue;
//            }
//            countryStateEntries.add(CountryStateEntry.builder()
//                            .country(row.get(0))
//                            .state(row.get(1))
//                    .build());
//        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> listOfMaps = dataTable.asMaps(String.class, String.class);
        List<CountryStateEntry> countryStateEntries = new ArrayList<>();
        listOfMaps.forEach(map -> countryStateEntries.add(objectMapper.convertValue(map, CountryStateEntry.class)));

        System.out.println(countryStateEntries);
    }

    @When("^User login into application with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_login_into_application_username_password(String s1, String s2) {
        System.out.println(String.format("user_login_into_application_username_password %s, %s", s1, s2));
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated() {
        System.out.println("home_page_is_populated");
    }

    @Then("Cards displayed are {string}")
    public void cards_displayed_are(String string) {
        System.out.println(String.format("cards_displayed_are - %s", string));
    }

    @When("^User sign up with following details$")
    public void user_sign_up_with_following_details(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println(dataTable.transpose().asList());
    }

    @When("^User login into app with (.+) and (.+)$")
    public void user_login_into_application_with_user1_and_pass1(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(String.format("Username %s, Password %s", username, password));
    }
}
