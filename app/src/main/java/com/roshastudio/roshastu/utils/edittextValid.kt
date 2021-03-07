package com.roshastudio.roshastu.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.databinding.BindingAdapter
import kotlin.properties.Delegates

object edittextValid {
    var name: String? = null
    var regstatus = false
    var status = false
    var mobilststus: Boolean = false
    var namestatus: Boolean = false
    var emailstatus: Boolean = false
    var pasststus: Boolean = false
    var login: Boolean = true
    var regester: Boolean = false

    fun getstatusserver(servrr: Boolean) {
        login = servrr
    }


    @BindingAdapter(value = ["app:typeed", "app:edterror"], requireAll = true)
    @JvmStatic
    fun error(
        edt: com.google.android.material.textfield.TextInputLayout,
        typeed: String?,
        edterror: String?
    ) {

        var edtt = edt.editText
        edtt!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                when (typeed) {
                    "mobile" -> {
                        if (s.isNullOrEmpty()) {
                            mobilststus = false
                            edt.error = "خالی نباشد"

                        } else if (s.length in 11..12) {
                            mobilststus = true
                            edt.error = null
                        } else if (s.length !in 11..12) {
                            mobilststus = false
                            edt.error = "تعداد صحیح نیست"
                        }

                    }
                    "pass" -> {
                        if (s.isNullOrEmpty()) {
                            pasststus = false
                            edt.error = "خالی "
                        } else if (s.length < 5) {
                            pasststus = false
                            edt.error = "کوتاه است"
                        } else if (name.equals(s.toString())) {
                            pasststus = false
                            edt.error = "نام و پسورد یکی نباشد"
                        } else if (s.length > 5) {
                            pasststus = true
                            edt.error = null
                        }

                    }
                    "name" -> {
                        if (s.isNullOrEmpty()) {

                            namestatus = false
                            edt.error = "خالی "
                        } else {
                            name = s.toString()
                            namestatus = true
                            edt.error = null
                        }
                    }
                    "email" -> {
                        if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                            emailstatus = false
                            edt.error = "فرمت ایمیل اشتباه است"
                        } else if (s.isNullOrEmpty()) {
                            emailstatus = false
                            edt.error = "خالی "
                        } else {
                            emailstatus = true
                            edt.error = null
                        }
                    }


                }

                status = pasststus == true && mobilststus == true
                regstatus = emailstatus == true && status == true && namestatus == true
            }

            override fun afterTextChanged(s: Editable?) {
                if (login == false && status == true) {
                    edt.error = "رمز یا پسورد اشتباه است"
                }


            }

        })


    }

}