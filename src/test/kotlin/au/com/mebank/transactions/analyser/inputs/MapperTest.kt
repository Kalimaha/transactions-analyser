package au.com.mebank.transactions.analyser.inputs

import au.com.mebank.transactions.analyser.constants.TransactionType
import au.com.mebank.transactions.analyser.models.Transaction
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class MapperTest {
    companion object {
        private const val VALID_CSV_LINE = "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT,"
    }

    @Nested
    inner class WhenCSVIsValid {
        @Test
        fun `result is not empty`() {
            val result = csv2Transaction(VALID_CSV_LINE)
            Assertions.assertThat(result).isEqualTo(
                Transaction(
                    transactionId = "TX10001",
                    fromAccountId = "ACC334455",
                    toAccountId = "ACC778899",
                    createdAt = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("20/10/2018 12:47:55"),
                    transactionType = TransactionType.PAYMENT,
                    amount = 25.00
                )
            )
        }
    }
}