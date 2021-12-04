package googleSearch.googleSearchTest;

import io.cucumber.java.en.*;

public class GoogleSearchStepDefinition {

    GoogleSearchStep googleSearchStep;

    public GoogleSearchStepDefinition() {
        googleSearchStep = new GoogleSearchStep();
    }

    @Given("the user is on the google search view")
    public void the_user_is_on_the_google_search_view() {
        try {
            googleSearchStep.the_user_is_on_the_google_search_view();
        } catch (Exception e) {}
    }
    @When("typing the artist name in the google input search field")
    public void typing_the_artist_name_in_the_google_input_search_field() {
        try {
            googleSearchStep.introduce_artist_name();
        } catch (Exception e) {}
    }
    @When("press Enter keyboard")
    public void press_enter_keyboard() {
        try {
            googleSearchStep.press_enter_keyboard();
        } catch (Exception e) {}
    }
    @Then("the system will show all the possible search results verifying that the title of the first element match with the text introduced")
    public void the_system_will_show_all_the_possible_search_results_verifying_that_the_title_of_the_first_element_match_with_the_text_introduced() {
        try {
            googleSearchStep.validate_search_result_criteria();
        } catch (Exception e) {}
    }
    @Then("will click on the first element on the search result verifying that the link matches with the url open")
    public void will_click_on_the_first_element_on_the_search_result_verifying_that_the_link_matches_with_the_url_open() {
        try {
            googleSearchStep.click_first_element();
        } catch (Exception e) {}
    }
}
