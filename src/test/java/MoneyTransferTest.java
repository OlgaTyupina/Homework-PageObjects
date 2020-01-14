import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @BeforeEach
    void loginVerification() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Тест проверяет перевод с карты 001 на карту 002")
    void shouldTransferMoneyToCard2 () {
        val dashBoardPage = new DashboardPage();
        dashBoardPage.refillCard1fromCard2(700);
        dashBoardPage.dashboardPageVisible();

    }

    @Test
    @DisplayName("Тест проверяет переовд с карты 002 на карту 001")
    void shouldTransferMoneyToCard1() {
        val dashBoardPage = new DashboardPage();
        dashBoardPage.refillCard2FromCard1(900);
        dashBoardPage.dashboardPageVisible();
    }

    @Test
    @DisplayName("Должна появиться ошибка при переводе суммы превышающей баланс карты")
    void shouldTransferMoneyMoreThanBalance() {
        val dashBoardPage = new DashboardPage();
        dashBoardPage.transferMoneyWithExcessBalance(20000);
    }
}
