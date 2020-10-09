package com.phptravels.Utility;

public class GlobalDataUtil {

    private String tabName;
    private String dateType;
    private String month;
    private String person;
    private String toFrom;
    private String rand_dest_txt;
    private String activeModule;
    private String currentDest;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {

        this.tabName = tabName.toLowerCase();
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getToFrom() {
        return toFrom;
    }

    public void setToFrom(String toFrom) {
        this.toFrom = toFrom;
    }

    public String getRand_dest_txt() {
        return rand_dest_txt;
    }

    public void setRand_dest_txt(String rand_dest_txt) {
        this.rand_dest_txt = rand_dest_txt;
    }

    public String getActiveModule() {
        return activeModule;
    }

    public void setActiveModule(String activeModule) {
        this.activeModule = activeModule;
    }

    public String getCurrentDest() {
        return currentDest;
    }

    public void setCurrentDest(String currentDest) {
        this.currentDest = currentDest;
    }

    public String getIndex(){

        String index="";
        if (tabName.equals("hotels")){
            if (dateType.equals("checkin")){
                index="1";
            }
            if (dateType.equals("checkout")){
                index="2";
            }
        }else if (tabName.equals("flights")){
            if (dateType.equals("depart")){
                index="9";
            }
            if (dateType.equals("retur")){
                index="10";
            }
        }else if (tabName.equals("boats")){
            index="6";
        }else if (tabName.equals("tours")){
            index="8";
        }else if (tabName.equals("cars")){
            if (dateType.equals("pickupdate")){
                index="4";
            }
            if (dateType.equals("dropoffdate")){
                index="5";
            }
        }else if (tabName.equals("visa")){
            index="3";
        }

        return index;
    }

    public String monthValue(){
        String value ="";
        switch (month){
            case "January":
                value="0";
                break;
            case "February":
                value="1";
                break;
            case "March":
                value="2";
                break;
            case "April":
                value="3";
                break;
            case "May":
                value="4";
                break;
            case "June":
                value="5";
                break;
            case "July":
                value ="6";
                break;
            case "August":
                value="7";
                break;
            case "September":
                value="8";
                break;
            case "October":
                value="9";
                break;
            case "November":
                value="10";
                break;
            case "December":
                value="11";
                break;
            default:
                value="0";
                break;
        }

        return value;
    }
}
