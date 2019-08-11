package au.com.mebank.transactions.analyser.models

import au.com.mebank.transactions.analyser.constants.TransactionType
import java.util.*

data class Transaction(
    val transactionId:      String,
    val fromAccountId:      String,
    val toAccountId:        String,
    val createdAt:          Date,
    val amount:             Double,
    val transactionType:    TransactionType,
    val relatedTransaction: String? = null
)