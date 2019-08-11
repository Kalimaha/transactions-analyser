package au.com.mebank.transactions.analyser.models

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AccountBalanceTest {
    private lateinit var accountBalance: AccountBalance

    @BeforeEach
    fun init() {
        accountBalance = AccountBalance(balance = 42.0, numberOfTransactions = 3)
    }

    @Test
    fun `represents the object with a human readable string`() {
        Assertions.assertThat(accountBalance.toString())
            .isEqualTo("Relative balance for the period is: \$42.00\nNumber of transactions included is: 3")
    }
}