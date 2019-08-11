package au.com.mebank.transactions.analyser.inputs

import au.com.mebank.transactions.analyser.constants.TransactionType
import au.com.mebank.transactions.analyser.models.Transaction
import java.text.SimpleDateFormat

const val DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"

fun csv2Transaction(csvLine: String): Transaction {
    val values = csvLine.split(",")
    return Transaction(
        transactionId       = values[0].trim(),
        fromAccountId       = values[1].trim(),
        toAccountId         = values[2].trim(),
        createdAt           = SimpleDateFormat(DATE_FORMAT).parse(values[3].trim()),
        amount              = values[4].trim().toDouble(),
        transactionType     = TransactionType.valueOf(values[5].trim()),
        relatedTransaction  = if (values[5].trim().isEmpty()) values[5].trim() else null
    )
}