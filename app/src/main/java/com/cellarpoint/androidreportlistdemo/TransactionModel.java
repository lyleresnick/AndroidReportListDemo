package com.cellarpoint.androidreportlistdemo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LyleResnick on 2016-12-04.
 */
public class TransactionModel {

    String group;
    String date;
    String description;
    String amount;
    String debit;



    TransactionModel( String group,
            String date,
            String description,
            String amount,
            String debit) {

        this.group = group;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.debit = debit;
    }

    static List<TransactionModel> authorizedData = Arrays.asList(

        new TransactionModel( "A", "2016-04-01", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-01", "Firkin", "15.34", "D" ),
        new TransactionModel( "A", "2016-04-02", "Starbucks", "5.22", "D" ),
        new TransactionModel( "A", "2016-04-05", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-07", "Starbucks", "4.32", "D" ),
        new TransactionModel( "A", "2016-04-07", "TTC", "28.00", "D" ),
        new TransactionModel( "A", "2016-04-09", "Starbucks", "15.20", "D" ),
        new TransactionModel( "A", "2016-04-10", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-11", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-11", "Pizza Pizza", "33.22", "D" ),
        new TransactionModel( "A", "2016-04-12", "Starbucks", "5.22", "D" ),
        new TransactionModel( "A", "2016-04-12", "PetroGaz Ottawa", "80.98", "D" ),
        new TransactionModel( "A", "2016-04-15", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-17", "Starbucks", "4.32", "D" ),
        new TransactionModel( "A", "2016-04-17", "LCBO", "400.55", "D" ),
        new TransactionModel( "A", "2016-04-19", "Starbucks", "15.20", "D" ),
        new TransactionModel( "A", "2016-04-20", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-20", "Mr Greek", "13.25", "D" ),
        new TransactionModel( "A", "2016-04-20", "Payment", "400.00", "C" ),
        new TransactionModel( "A", "2016-04-21", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-22", "Starbucks", "5.22", "D" ),
        new TransactionModel( "A", "2016-04-25", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-25", "Burrito Boyz", "23.49", "D" ),
        new TransactionModel( "A", "2016-04-27", "Starbucks", "4.32", "D" ),
        new TransactionModel( "A", "2016-04-27", "Georges Jazz Bar", "56.49", "D" ),
        new TransactionModel( "A", "2016-04-29", "Starbucks", "15.20", "D" ),
        new TransactionModel( "A", "2016-04-30", "Starbucks", "3.11", "D" ),
        new TransactionModel( "A", "2016-04-30", "Mark's Work Wearhouse", "206.66", "D" )
    );

    static List<TransactionModel> postedData = Arrays.asList(

        new TransactionModel( "P", "2016-05-01", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-02", "Starbucks", "5.22", "D" ),
        new TransactionModel( "P", "2016-05-02", "Shell Steeles West", "44.62", "D" ),
        new TransactionModel( "P", "2016-05-05", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-05", "The Rex", "53.41", "D" ),
        new TransactionModel( "P", "2016-05-07", "Starbucks", "4.32", "D" ),
        new TransactionModel( "P", "2016-05-09", "LCBO", "45.20", "D" ),
        new TransactionModel( "P", "2016-05-09", "Starbucks", "15.20", "D" ),
        new TransactionModel( "P", "2016-05-09", "Royal Ontario Museum", "115.60", "D" ),
        new TransactionModel( "P", "2016-05-10", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-11", "Starbucks", "6.21", "D" ),
        new TransactionModel( "P", "2016-05-11", "Domino's Pizza", "33.22", "D" ),
        new TransactionModel( "P", "2016-05-12", "Starbucks", "5.22", "D" ),
        new TransactionModel( "P", "2016-05-12", "PetroGaz Toronto", "80.98", "D" ),
        new TransactionModel( "P", "2016-05-15", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-17", "Tim Horton's", "4.32", "D" ),
        new TransactionModel( "P", "2016-05-17", "LCBO", "400.55", "D" ),
        new TransactionModel( "P", "2016-05-19", "Starbucks", "15.20", "D" ),
        new TransactionModel( "P", "2016-05-20", "Tim Hortons", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-20", "Mr Greek", "13.25", "D" ),
        new TransactionModel( "P", "2016-05-20", "Payment", "400.00", "C" ),
        new TransactionModel( "P", "2016-05-21", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-22", "Starbucks", "5.22", "D" ),
        new TransactionModel( "P", "2016-05-25", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-25", "Burrito Boyz", "23.49", "D" ),
        new TransactionModel( "P", "2016-05-27", "Starbucks", "4.32", "D" ),
        new TransactionModel( "P", "2016-05-27", "Georges Jazz Bar", "56.49", "D" ),
        new TransactionModel( "P", "2016-05-29", "Starbucks", "15.20", "D" ),
        new TransactionModel( "P", "2016-05-30", "Starbucks", "3.11", "D" ),
        new TransactionModel( "P", "2016-05-30", "Mark's Work Wearhouse", "206.66", "D" )
    );


}


