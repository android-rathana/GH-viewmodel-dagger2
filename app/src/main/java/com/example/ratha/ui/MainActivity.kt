package com.example.ratha.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ratha.ghviewmodel.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check when configuration change
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,RepoListFragment())
                    .commit()
        }

    }
}
