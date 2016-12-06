package com.cellarpoint.androidreportlistdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;
import java.util.List;


public class AccountDetailsCreditCardTransactionsFragment extends Fragment {

    public AccountDetailsCreditCardTransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.account_details_list_fragment, container, false);
        initializeListView(mainView);
        return mainView;
    }

    public void initializeListView(View mainView) {

        RecyclerView  listView = (RecyclerView) mainView.findViewById(R.id.listView);

        LinearLayoutManager layoutManager = new LinearLayoutManager( mainView.getContext() );
        listView.setLayoutManager(layoutManager);

        AccountDetailsTransactionListAdapter adapter = new AccountDetailsTransactionListAdapter();
        initializeAdapter( adapter );
        listView.setAdapter( adapter );
    }

    public void initializeAdapter(AccountDetailsTransactionListAdapter adapter) {

        appendTransactions(adapter, TransactionModel.authorizedData, getResources().getString(R.string.authorized_transactions), getResources().getString(R.string.authorized_transactions_not_available ) );
        appendTransactions(adapter, TransactionModel.postedData, getResources().getString(R.string.posted_transactions), getResources().getString(R.string.posted_transactions_not_available ) );
    }

    private void appendTransactions( AccountDetailsTransactionListAdapter adapter, List<TransactionModel> transactions, String headerText, String noTransText) {

        adapter.appendHeaderRow( headerText );

        if( transactions == null ) {

            adapter.appendMessageRow(noTransText);
            return;
        }

        int i = 0;
        TransactionModel curTransaction = ( i < transactions.size() ) ? transactions.get( i++ ) : null;
        BigDecimal total = new BigDecimal(0.0 );

        while( curTransaction != null ) {

            String curDate = curTransaction.date;
            adapter.appendSubHeaderRow(curDate);
            while ((curTransaction != null) && curTransaction.date.equals(curDate)) {

                BigDecimal amountBigDec = new BigDecimal(curTransaction.amount);
                if (!curTransaction.debit.equals("D")) {
                    total = total.subtract(amountBigDec);
                }
                else {
                    total = total.add(amountBigDec);
                }
                adapter.appendDetailRow(curTransaction.description, curTransaction.amount, curTransaction.debit );
                curTransaction = ( i < transactions.size() ) ? transactions.get( i++ ) : null;
            }
            adapter.appendSubFooterRow();
        }
        adapter.appendTotalRow(total.toString());
    }
}


