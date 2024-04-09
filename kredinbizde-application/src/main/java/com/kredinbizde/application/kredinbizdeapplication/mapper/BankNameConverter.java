package com.kredinbizde.application.kredinbizdeapplication.mapper;

import com.kredinbizde.application.kredinbizdeapplication.exception.BusinessException;

public class BankNameConverter {
    public static String AKBANK = "akbank";
    public static String GARANTI = "garanti";
    public static String determineRoutingKey(String bankName){
        if(bankName.equalsIgnoreCase(AKBANK)){
            return AKBANK;
        }
        else if (bankName.equalsIgnoreCase(GARANTI)){
            return GARANTI;
        }
        else{
            throw new BusinessException("Bank could not be found");
        }
    }
}
