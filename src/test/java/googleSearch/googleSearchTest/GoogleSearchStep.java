package googleSearch.googleSearchTest;

import org.junit.Assert;

public class GoogleSearchStep {

    GoogleSearchPage googleSearchPage;

    public GoogleSearchStep() {
        googleSearchPage = new GoogleSearchPage();
    }

    public void the_user_is_on_the_google_search_view() {
        //Assert.assertTrue(googleSearchPage.openGoogleView());
        googleSearchPage.openURL();
    }

    public void introduce_artist_name() {
        Assert.assertTrue(googleSearchPage.introduceRandomArtistName());
    }

    public void press_enter_keyboard() {
        googleSearchPage.pressEnterKey();
    }

    public void validate_search_result_criteria() {
        Assert.assertTrue(googleSearchPage.validateTitleFirstElement());
    }

    public void click_first_element() {
        Assert.assertTrue(googleSearchPage.validateLinkFirstElement());
    }
}
