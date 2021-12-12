package nz.co.test.transactions.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nz.co.test.transactions.Constans
import nz.co.test.transactions.R
import nz.co.test.transactions.databinding.ActivityDetailBinding
import nz.co.test.transactions.services.Transaction

class DetailActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        var bundle = intent.getExtras()
        val transaction = bundle?.get(Constans.INTENT_EXTRA_NAME) as? Transaction
        mBinding.mTransaction = transaction
    }
}