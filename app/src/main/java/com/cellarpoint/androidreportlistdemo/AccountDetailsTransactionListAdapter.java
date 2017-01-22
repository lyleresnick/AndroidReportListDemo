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

    private static String TAG = "AccountDetailsTransactionListAdapter";

    private List<Row> dataset = new ArrayList<Row>();

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

    public void appendDetailRow( String description, String amount ) {
        dataset.add( new DetailRow( description, amount, odd ) );
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
            case Row.HEADER:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_header_row, parent, false);
                viewHolder = new HeaderViewHolder(v);
                break;

            case Row.SUBHEADER:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_subheader_row, parent, false);
                viewHolder = new SubHeaderViewHolder(v);
                break;

            case Row.DETAIL:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_row, parent, false);
                viewHolder = new RowViewHolder(v);
                break;

            case Row.MESSAGE:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_none_row, parent, false);
                viewHolder = new MessageRowViewHolder(v);
                break;

            case Row.TOTAL:

                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_details_transactions_list_total_row, parent, false);
                viewHolder = new TotalRowViewHolder(v);
                break;

            case Row.SUBFOOTER:

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

            DetailRow row = (DetailRow) dataset.get(position);
            description.setText( row.description );
            amount.setText( row.amount );

            processBackgroundColour(row.odd, rootView);
        }
    }

    private class SubFooterRowViewHolder extends TransactionsViewHolder {

        public View rootView;

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

    private interface Row {

        int HEADER = 0;
        int SUBHEADER = 1;
        int DETAIL = 2;
        int MESSAGE = 3;
        int TOTAL = 4;
        int SUBFOOTER = 5;

        int getType();
    }

    private static class HeaderRow implements Row {

        private String title;

        public HeaderRow(String title) {
            this.title = title;
        }

        public int getType() {
            return HEADER;
        }
    }

    private static class SubHeaderRow implements Row {

        final static DateFormat df = new SimpleDateFormat("EEEE MMM dd, yyyy");

        private String title;
        private boolean odd;

        public SubHeaderRow(Date date, boolean odd) {

            this.odd = odd;
            String camelCaseDate = df.format( date );
            title = camelCaseDate.toUpperCase();
        }

        public int getType() {
            return SUBHEADER;
        }
    }

    private static class DetailRow implements Row {

        private String description;
        private String amount;
        private boolean odd;

        public DetailRow(String description, String amount, boolean odd ) {

            this.odd = odd;
            this.description = description;
            this.amount = amount;
        }

        public int getType() {
            return DETAIL;
        }
    }

    private static class SubFooterRow implements Row {

        private boolean odd;

        public SubFooterRow( boolean odd ) {
            this.odd = odd;
        }

        public int getType() {
            return SUBFOOTER;
        }
    }

    private static class TotalRow implements Row {

        private String total;
        private boolean odd;

        public TotalRow(String total, boolean odd) {

            this.odd = odd;
            this.total = total;
        }

        public int getType() {
            return TOTAL;
        }
    }

    private static class MessageRow implements Row {

        private String message;

        public MessageRow(String message ) {
            this.message = message;
        }

        public int getType() {
            return MESSAGE;
        }
    }
}



