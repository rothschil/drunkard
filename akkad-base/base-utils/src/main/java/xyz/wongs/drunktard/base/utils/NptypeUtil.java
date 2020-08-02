package xyz.wongs.drunktard.base.utils;

public class NptypeUtil {

    public static String[] getNpType(String npType) {
        //                携出-携入方   001-电信 002-移动 003-联通
        //String arr[] ={"001","002"};
        String carryIn = null;
        String carryOut = null;
        switch (npType) {
            case "102":
                carryIn = "002";
                carryOut = "001";
                break;
            case "103":
                carryIn = "003";
                carryOut = "001";
                break;
            case "201":
                carryIn = "001";
                carryOut = "002";
                break;
            case "203":
                carryIn = "003";
                carryOut = "002";
                break;
            case "301":
                carryIn = "001";
                carryOut = "003";
                break;
            case "302":
                carryIn = "001";
                carryOut = "003";
                break;
            default:
                break;
        }
        String arr[] = {carryOut, carryIn};
        return arr;
    }
}
