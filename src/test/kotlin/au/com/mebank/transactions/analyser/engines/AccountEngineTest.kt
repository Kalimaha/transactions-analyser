package au.com.mebank.transactions.analyser.engines

import au.com.mebank.transactions.analyser.models.AccountBalance
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AccountEngineTest {
    private lateinit var accountBalance: AccountBalance
    private lateinit var filepath: String

    @Nested
    inner class WhenThereAreNoReversals {
        @BeforeEach
        fun init() {
            filepath        = javaClass.classLoader.getResource("example.csv").path
            accountBalance  = balance(filepath = filepath, accountId = "ACC998877", from = "20/10/2018 12:00:00", to = "20/10/2018 19:00:00")
        }

        @Test
        fun `it returns the account balance`() {
            Assertions.assertThat(accountBalance).isEqualTo(AccountBalance(-5.0, 1))
        }
    }
}