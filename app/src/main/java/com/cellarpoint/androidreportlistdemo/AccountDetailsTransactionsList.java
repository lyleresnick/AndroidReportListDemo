package com.cellarpoint.androidreportlistdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by LyleResnick on 2015-08-21.
 */


public class AccountDetailsTransactionsList {

    public interface Initializer {
        void initializeAdapter(AccountDetailsTransactionListAdapter adapter);
    }

    private RecyclerView listView;

    public AccountDetailsTransactionsList(View mainView, Initializer initializer) {

        listView = (RecyclerView) mainView.findViewById(R.id.listView);

        //listView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager( mainView.getContext() );
        listView.setLayoutManager(layoutManager);

        AccountDetailsTransactionListAdapter adapter = new AccountDetailsTransactionListAdapter();
        initializer.initializeAdapter( adapter );

        listView.setAdapter( adapter );
    }




}
