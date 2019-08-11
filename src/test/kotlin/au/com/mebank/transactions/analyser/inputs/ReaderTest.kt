package au.com.mebank.transactions.analyser.inputs

import au.com.mebank.transactions.analyser.constants.TransactionType
import au.com.mebank.transactions.analyser.models.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import kotlin.streams.toList

internal class ReaderTest {
    @Nested
    inner class ReadStreamOfString {
        private lateinit var filepath: String
        private lateinit var result: List<Transaction>

        @BeforeEach
        fun setup() {
            filepath    = javaClass.classLoader.getResource("example.csv").path
            result      = read(filepath).toList()
        }

        @Nested
        inner class WhenStreamIsValid {
            @Test
            fun `result is not empty`() {
                assertThat(result.size).isEqualTo(5)
            }

            @Test
            fun `result is correctly deserialised`() {
                assertThat(result).first().isEqualTo(
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
}