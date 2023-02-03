package com.example.pillapp40

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pillapp40.databinding.ActivitySinglePillDetailBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class singlePillDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySinglePillDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_pill_detail)

        val key = intent.getStringExtra("key")
        val database = Firebase.database
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val myRef =
            key?.let { database.getReference("Pills").child(it) }

        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val Pills: singlePill? = dataSnapshot.getValue(singlePill::class.java)
                if (Pills != null) {
                    binding.nameTextView.text = Pills.name.toString()
                    binding.descriptionTextView.text = Pills.description.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })

        binding = ActivitySinglePillDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.EditButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

    }

}