package com.istad.dataanalyticrestfulapi.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class TransactionProvider {

    public static String getTransactions(){

        return new SQL(){{

            SELECT("*");
            FROM("transactions_tb");

        }}.toString();
    }
}
