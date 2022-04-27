package com.example.roomapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomapplication.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    lateinit var context: Context

    lateinit var strUsername: String
    lateinit var strPassword: String

    private var btnAddLogin:AppCompatButton ?= null
    private var btnReadLogin:AppCompatButton ?= null
    private var txtUsername : AppCompatEditText ?= null
    private var txtPassword : AppCompatEditText ?= null
    private var lblInsertResponse : AppCompatTextView ?= null
    private var lblReadResponse : AppCompatTextView ?= null
    private var lblUseraname : AppCompatTextView ?= null
    private var lblPassword : AppCompatTextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddLogin = findViewById(R.id.btnAddLogin)
        btnReadLogin = findViewById(R.id.btnReadLogin)
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        lblInsertResponse = findViewById(R.id.lblInsertResponse)
        lblReadResponse = findViewById(R.id.lblReadResponse)
        lblUseraname = findViewById(R.id.lblUseraname)
        lblPassword = findViewById(R.id.lblPassword)

        context = this@MainActivity

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnAddLogin?.setOnClickListener {

            strUsername = txtUsername?.text.toString().trim()
            strPassword = txtPassword?.text.toString().trim()

            if (strPassword.isEmpty()) {
                txtUsername?.error = "Please enter the username"
            }
            else if (strPassword.isEmpty()) {
                txtPassword?.error = "Please enter the username"
            }
            else {
                loginViewModel.insertData(context, strUsername, strPassword)
                lblInsertResponse?.text = "Inserted Successfully"
            }
        }

        btnReadLogin?.setOnClickListener {

            strUsername = txtUsername?.text.toString().trim()

            loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {

                if (it == null) {
                    lblReadResponse?.text = "Data Not Found"
                    lblUseraname?.text = "- - -"
                    lblPassword?.text = "- - -"
                }
                else {
                    lblUseraname?.text = it.Username
                    lblPassword?.text = it.Password

                    lblReadResponse?.text = "Data Found Successfully"
                }
            })
        }
    }
}