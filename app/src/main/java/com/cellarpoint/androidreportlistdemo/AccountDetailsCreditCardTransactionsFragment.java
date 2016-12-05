package com.cellarpoint.androidreportlistdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;
import java.util.List;


public class AccountDetailsCreditCardTransactionsFragment extends Fragment implements AccountDetailsTransactionsList.Initializer {

    private final String TAG = "AccntDtlsCrdtCrdTrnsActn";

    public AccountDetailsCreditCardTransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.account_details_list_fragment, container, false);

        new AccountDetailsTransactionsList(mainView, this);

        return mainView;
    }


    public void initializeAdapter(AccountDetailsTransactionListAdapter adapter) {

        appendAuthorizedTransactions(adapter);
        appendPostedTransactions(adapter);
    }

    private void appendAuthorizedTransactions( AccountDetailsTransactionListAdapter adapter) {


        List<TransactionModel> transactions = TransactionModel.authorizedData;

        adapter.appendHeaderRow( getResources().getString(R.string.authorized_transactions) );

        if( transactions == null ) {

            adapter.appendMessageRow(getResources().getString(R.string.authorized_transactions_not_available));
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


    private void appendPostedTransactions( AccountDetailsTransactionListAdapter adapter) {

        List<TransactionModel> transactions = TransactionModel.postedData;


        adapter.appendHeaderRow(getResources().getString(R.string.posted_transactions) );

        if( transactions == null ) {

            adapter.appendMessageRow(getResources().getString(R.string.posted_transactions_not_available));
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


