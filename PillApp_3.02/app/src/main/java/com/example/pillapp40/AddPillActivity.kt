package com.example.pillapp40


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pillapp40.databinding.ActivityAddPillBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddPillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPillBinding

    private var database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myRef = database.getReference("Pills")
        val name = binding.nameEditText.text
        val date = binding.dateEditText.text
        val description = binding.descriptionEditText.text

        binding.saveButton.setOnClickListener {

            val Pills = singlePill(name.toString(), date.toString(), description.toString())
            myRef.child(myRef.push().key.toString()).setValue(Pills)
            finish()

            //sending to ListActivity
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
