package Interfaces;

public interface TestActions {
    void userNavigatesToTheLoginPage();
    void userEntersUsernameAndPassword();
    void userIsNavigatedToTheHomePage();
    void userEnterInvalidUsername();
    void userSeeErrorMessageInvalidUsername();
    void userEnterInvalidPassword();
    void userSeeErrorMessageInvalidPassword();
    void errorMessageIsDisplayed();
}
