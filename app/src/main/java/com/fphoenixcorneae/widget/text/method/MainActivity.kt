package com.fphoenixcorneae.widget.text.method

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fphoenixcorneae.widget.text.method.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also {
            mViewBinding = it
            it.lifecycleOwner = this
            setContentView(it.root)
        }
    }

    fun onHide(view: View) {
        with(mViewBinding) {
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            tvPhoneNo.transformationMethod = PhoneNumberTransformationMethod.getInstance()
            tvUserName1.transformationMethod = UserNameTransformationMethod.getInstance()
            tvUserName2.transformationMethod = UserNameTransformationMethod.getInstance()
            tvUserName3.transformationMethod = UserNameTransformationMethod.getInstance()
            tvPrice.transformationMethod = PriceTransformationMethod.getInstance()
            tvIdCard.transformationMethod = IdCardTransformationMethod.getInstance()
        }
    }

    fun onShow(view: View) {
        with(mViewBinding) {
            etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            tvPhoneNo.transformationMethod = HideReturnsTransformationMethod.getInstance()
            tvUserName1.transformationMethod = HideReturnsTransformationMethod.getInstance()
            tvUserName2.transformationMethod = HideReturnsTransformationMethod.getInstance()
            tvUserName3.transformationMethod = HideReturnsTransformationMethod.getInstance()
            tvPrice.transformationMethod = HideReturnsTransformationMethod.getInstance()
            tvIdCard.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }
}