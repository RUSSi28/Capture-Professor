package com.example.captureprofessor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room

class GradeFragment:Fragment() {
    private lateinit var db:GradeDatabase
    private lateinit var dao:GradeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.db= Room.databaseBuilder(
            requireContext(),
            GradeDatabase::class.java,
            "GradeDatabase.kt"
        ).build()
        this.dao=this.db.gradeDao()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}