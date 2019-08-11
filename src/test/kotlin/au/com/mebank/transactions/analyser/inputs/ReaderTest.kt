package au.com.mebank.transactions.analyser.inputs

import au.com.mebank.transactions.analyser.constants.TransactionType
import au.com.mebank.transactions.analyser.models.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.stream.Stream
import kotlin.streams.toList

internal class ReaderTest {
    companion object {
        private const val VALID_CSV_LINE = "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT,"
    }

    @Nested
    inner class ReadStreamOfString {
        private lateinit var reader: Reader

        @BeforeEach
        fun setup() {
            reader = Reader()
        }

        @Nested
        inner class WhenStreamIsValid {
            @Test
            fun `result is not empty`() {
                val result = reader.read(Stream.of(VALID_CSV_LINE)).toList()
                assertThat(result).isNotEmpty
            }

            @Test
            fun `result is correctly deserialised`() {
                val result = reader.read(Stream.of(VALID_CSV_LINE)).toList()
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