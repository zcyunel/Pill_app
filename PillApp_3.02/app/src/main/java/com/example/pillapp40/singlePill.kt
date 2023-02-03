package com.example.pillapp40
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class singlePill(val name: String? = null,val date: String? = null, val description: String? = null, @Exclude val key: String? = null ){
}