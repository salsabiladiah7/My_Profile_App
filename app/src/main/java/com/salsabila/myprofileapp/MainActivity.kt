package com.salsabila.myprofileapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var edtName: EditText? = null
    var spinnerGender: Spinner? = null
    var edtEmail: EditText? = null
    var edtTelp: EditText? = null
    var edtAlamat: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtName = findViewById(R.id.edtName)
        spinnerGender = findViewById(R.id.spinnerGender)
        edtEmail = findViewById(R.id.edtEmail)
        edtTelp = findViewById(R.id.edtTelp)
        edtAlamat = findViewById(R.id.edtAddres)

        btnSave.setOnClickListener { validasiInput() }
        setDataSpinnerGender()
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_list,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender?.adapter = adapter
    }

    private fun validasiInput() {
        val namaInput = edtName?.text.toString()
        val emailInput = edtEmail?.text.toString()
        val telpInput = edtTelp?.text.toString()
        val alamatInput = edtAlamat?.text.toString()
        val genderInput = spinnerGender?.selectedItem.toString()

        when {
            namaInput.isEmpty() -> edtName?.error = "Nama Tidak Boleh Kosong"
            genderInput.equals("Pilih Kelamin") -> "Kelamin harus dipilih"
            emailInput.isEmpty() -> edtEmail?.error = "Email Tidak Boleh Kosong"
            telpInput.isEmpty() -> edtTelp?.error = "Telp Tidak Boleh Kosong"
            alamatInput.isEmpty() -> edtAlamat?.error = "Alamat Tidak Boleh Kosong"

            else -> {
                Toast.makeText(this, "Navigasi ke halaman profil diri", Toast.LENGTH_SHORT).show()
                navigasiKeProfilDiri()
            }
        }
    }

    private fun navigasiKeProfilDiri() {
        val namaInput = edtName?.text.toString()
        val emailInput = edtEmail?.text.toString()
        val telpInput = edtTelp?.text.toString()
        val alamatInput = edtAlamat?.text.toString()
        val genderInput = spinnerGender?.selectedItem.toString()

        val intent = Intent(this, MyProfileActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)

        intent.putExtras(bundle)
        startActivity(intent)

    }

}