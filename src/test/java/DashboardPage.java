import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement buttonCard1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    //private SelenideElement buttonCard1 = $("[data-test-id=action-deposit] button");
    private SelenideElement buttonCard2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private SelenideElement amountTransfer = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement refillButton = $("[data-test-id=action-transfer]");
    private SelenideElement notificationError = $("[data-test-id=error-notification");


    public void dashboardPageVisible() {
        heading.shouldBe(visible);
    }

    public void refillCard1fromCard2 (int amount) {
        buttonCard1.click();
        amountTransfer.setValue(String.valueOf(amount));
        fromCard.setValue(DataHelper.DataCards.getCardNumber2());
        refillButton.click();
    }

    public void refillCard2FromCard1(int amount) {
        buttonCard2.click();
        amountTransfer.setValue(String.valueOf(amount));
        fromCard.setValue(DataHelper.DataCards.getCardNumber1());
        refillButton.click();
    }

    public void transferMoneyWithExcessBalance (int amount) {
        buttonCard1.click();
        amountTransfer.setValue(String.valueOf(amount));
        fromCard.setValue(DataHelper.DataCards.getCardNumber2());
        refillButton.click();
        notificationError.shouldBe(visible);
    }

}
