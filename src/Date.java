public class Date {
    public static int day;
    public static int month;
    public static int year;



    /**
     * Constructor to set the date
     * @param day
     * @param month
     * @param year
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Empty constructor
     */
    public Date(){}

    public String toString(){
        return day + "-" + month + "-" + year;
    }
}




