package com.peterdpong.checked

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peterdpong.checked.edittask.EditFragment
import com.peterdpong.checked.listtasks.ListFragment
import java.util.*

class MainActivity : AppCompatActivity(), ListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = ListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

    }

    override fun onTaskSelect(taskId: UUID) {
        val fragment = EditFragment.newInstance(taskId)
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(findViewById(R.id.taskItemLayout), taskId.toString())
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
