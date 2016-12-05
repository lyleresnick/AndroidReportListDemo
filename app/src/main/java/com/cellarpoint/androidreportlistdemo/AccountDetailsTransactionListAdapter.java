package com.cellarpoint.androidreportlistdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LyleResnick on 2015-08-20.
 */

public class AccountDetailsTransactionListAdapter extends RecyclerView.Adapter<AccountDetailsTransactionListAdapter.TransactionsViewHolder> {

    private final String TAG = "AccntDtlsTrnsLstAdptr";

    private List<RowInterface> dataset = new ArrayList<RowInterface>();

    private boolean odd = false;

    final static DateFormat inboundDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public void appendHeaderRow( String name ) {
        dataset.add( new HeaderRow( name ) );
    }

    public void appendSubHeaderRow( String inboundDate ) {

        odd = !odd;

        Date date = null;
        try {
            date = inboundDateFormat.parse(inboundDate);
        }
        catch ( ParseException e ) {
            Log.d(TAG, "Transaction Date format is wrong");
        }


        dataset.add( new SubHeaderRow( date, odd ) );
    }

    public void appendDetailRow( String description, String amount, String debit ) {
        dataset.add( new Row( description, amount, debit, odd ) );
    }

    public void appendSubFooterRow() {

        dataset.add(new SubFooterRow( odd ));
    }


    public void appendTotalRow(String total) {

        odd = !odd;

        dataset.add(new TotalRow(total, odd));
    }



    public void appendMessageRow( String message ) {
        dataset.add( new MessageRow( message ) );
    }


    // overides - not public

    @Override
    public int getItemViewType(int position) {
        return dataset.get( position ).getType();
    }

    @Override
    public TransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        TransactionsViewHolder viewHolder = null;
        switch( viewType )
        {
            case RowInterface.HEADER:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_header_row, parent, false);
                viewHolder = new HeaderViewHolder(v);
                break;

            case RowInterface.SUBHEADER:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_subheader_row, parent, false);
                viewHolder = new SubHeaderViewHolder(v);
                break;

            case RowInterface.ROW:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_row, parent, false);
                viewHolder = new RowViewHolder(v);
                break;

            case RowInterface.MESSAGE_ROW:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_none_row, parent, false);
                viewHolder = new MessageRowViewHolder(v);
                break;

            case RowInterface.TOTAL_ROW:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_total_row, parent, false);
                viewHolder = new TotalRowViewHolder(v);
                break;

            case RowInterface.SUB_FOOTER_ROW:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_subfooter_row, parent, false);
                viewHolder = new SubFooterRowViewHolder(v);
                break;


        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionsViewHolder holder, int position) {
        holder.bind( position );
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    // TransactionsViewHolder

    private void processBackgroundColour(boolean odd, View rootView) {

        int backgroundColor;
        if (odd) {
            backgroundColor = rootView.getResources().getColor(R.color.odd_band_background );
        } else {
            backgroundColor = rootView.getResources().getColor(R.color.even_band_background );
        }
        rootView.setBackgroundColor(backgroundColor);

    }



    public abstract class TransactionsViewHolder extends RecyclerView.ViewHolder {

        public TransactionsViewHolder(View v) {
            super( v );
        }
        public abstract void bind( int position );
    }

    private class HeaderViewHolder extends TransactionsViewHolder {

        public TextView title;

        public HeaderViewHolder(View v) {

            super(v);
            title = (TextView)v.findViewById( R.id.title);
        }

        public void bind( int position ) {

            HeaderRow headerRow = (HeaderRow) dataset.get(position);
            title.setText( headerRow.title );
        }

    }

    private class SubHeaderViewHolder extends TransactionsViewHolder {

        public View rootView;
        public TextView title;

        public SubHeaderViewHolder(View v ) {

            super(v);

            rootView = v;
            title = (TextView)v.findViewById( R.id.title);
        }

        public void bind( int position ) {

            SubHeaderRow subheaderRow = (SubHeaderRow) dataset.get(position);
            title.setText( subheaderRow.title );

            processBackgroundColour(subheaderRow.odd, rootView);
        }
    }

    private class RowViewHolder extends TransactionsViewHolder {

        public View rootView;
        public TextView description;
        public TextView amount;


        public RowViewHolder(View v) {

            super(v);

            rootView = v;
            description = (TextView)v.findViewById( R.id.description);
            amount = (TextView)v.findViewById( R.id.amount);
        }

        public void bind( int position ) {

            Row row = (Row) dataset.get(position);
            description.setText( row.description );
            amount.setText( row.amount );

            processBackgroundColour(row.odd, rootView);
        }
    }

    private class SubFooterRowViewHolder extends TransactionsViewHolder {

        public View rootView;
        public TextView description;
        public TextView amount;


        public SubFooterRowViewHolder(View v) {

            super(v);
            rootView = v;
        }

        public void bind(int position) {

            SubFooterRow row = (SubFooterRow) dataset.get(position);

            processBackgroundColour(row.odd, rootView);
        }
    }

    private class TotalRowViewHolder extends TransactionsViewHolder {

        public View rootView;
        public TextView total;


        public TotalRowViewHolder(View v) {

            super(v);
            rootView = v;
            total = (TextView) v.findViewById(R.id.total);
        }

        public void bind(int position) {

            TotalRow row = (TotalRow) dataset.get(position);
            total.setText(row.total);
            odd = false;
            processBackgroundColour(row.odd, rootView);

        }
    }


    private class MessageRowViewHolder extends TransactionsViewHolder {

        public View rootView;
        public TextView message;


        public MessageRowViewHolder(View v) {

            super(v);
            message = (TextView)v.findViewById( R.id.message);

            rootView = v;
        }

        public void bind( int position ) {

            MessageRow row = (MessageRow) dataset.get(position);

            int  backgroundColor = rootView.getResources().getColor(R.color.odd_band_background );
            message.setText( row.message );

            processBackgroundColour(true, rootView);
        }
    }



    // Row

    private interface RowInterface {

        int HEADER = 0;
        int SUBHEADER = 1;
        int ROW = 2;
        int MESSAGE_ROW = 3;
        int TOTAL_ROW = 4;
        int SUB_FOOTER_ROW = 5;


        int getType();
    }

    private static class HeaderRow implements RowInterface {

        public String title;

        public HeaderRow(String title) {
            this.title = title;
        }

        public int getType() {
            return HEADER;
        }
    }

    private static class SubHeaderRow implements RowInterface {

        final static DateFormat df = new SimpleDateFormat("EEEE MMM dd, yyyy");

        public String title;
        public boolean odd;

        public SubHeaderRow(Date date, boolean odd) {

            this.odd = odd;
            String camelCaseDate = df.format( date );
            title = camelCaseDate.toUpperCase();
        }

        public int getType() {
            return SUBHEADER;
        }
    }

    private static class Row implements RowInterface {

        public String description;
        public String amount;
        public boolean odd;


        public Row(String description, String amount, String debit, boolean odd ) {

            this.odd = odd;
            this.description = description;
            if( debit.equals("D") )
                this.amount = amount;
            else
                this.amount = "-" + amount ;

        }

        public int getType() {
            return ROW;
        }
    }

    private class SubFooterRow implements RowInterface {

        public boolean odd;

        public SubFooterRow( boolean odd ) {
            this.odd = odd;
        }

        public int getType() {

            return SUB_FOOTER_ROW;
        }
    }

    private class TotalRow implements RowInterface {

        public String description;
        public String total;
        public String totalAccess;
        public boolean odd;


        public TotalRow(String total, boolean odd) {

            this.odd = odd;
            this.total = total;

        }

        public int getType() {

            return TOTAL_ROW;
        }
    }


    private static class MessageRow implements RowInterface {

        public String message;


        public MessageRow(String message ) {

            this.message = message;
        }

        public int getType() {
            return MESSAGE_ROW;
        }
    }



}



