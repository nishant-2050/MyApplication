package com.nis.myapplication.countries.data

data class Countries(val countries: ArrayList<Country>)

data class Country(val code: String, val name: String)