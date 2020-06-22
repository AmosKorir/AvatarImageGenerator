package com.avatarfirst.avatagen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.avatarfirst.avatagen.apis.ApiData
import com.avatarfirst.avatagen.apis.GithubUserApiResponseItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val apiCall = ApiData()
    private var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
    }

    private fun showUSer(users: List<GithubUserApiResponseItem>) {
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = UsersAdapter(this, users)
    }

    private fun loadData() {
        val disposable = apiCall.getUsersApi().getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showUSer(it)
                },
                {
                    Toast.makeText(this, "error occurred", Toast.LENGTH_LONG).show()
                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}
