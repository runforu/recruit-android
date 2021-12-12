package nz.co.test.transactions.services

import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.math.BigDecimal
import java.time.OffsetDateTime

data class Transaction(
    val id: Int,
    val transactionDate: OffsetDateTime,
    val summary: String,
    val debit: Double,
    val credit: Double
) : Serializable