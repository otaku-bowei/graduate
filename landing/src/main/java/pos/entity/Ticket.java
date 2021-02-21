package pos.entity;


public class Ticket {
    private String invoiceID ;
    private String branch ;
    private String city ;
    private int customer ;
    private String gender ;
    private String productLine ;
    private double unitPrice ;
    private int quantity ;
    private double tax ;
    private double total ;
    private String date ;
    private String time ;
    private String payment ;
    private double cogs ;
    private double grossMargin ;
    private double grossIncome ;
    private double rating ;

    public Ticket(){}

    public Ticket(String invoiceID, String branch, String city, int customer, String gender, String productLine, double unitPrice, int quantity, double tax, double total, String date, String time, String payment, double cogs, double grossMargin, double grossIncome, double rating) {
        this.invoiceID = invoiceID;
        this.branch = branch;
        this.city = city;
        this.customer = customer;
        this.gender = gender;
        this.productLine = productLine;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.tax = tax;
        this.total = total;
        this.date = date;
        this.time = time;
        this.payment = payment;
        this.cogs = cogs;
        this.grossMargin = grossMargin;
        this.grossIncome = grossIncome;
        this.rating = rating;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getCogs() {
        return cogs;
    }

    public void setCogs(double cogs) {
        this.cogs = cogs;
    }

    public double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(double grossMargin) {
        this.grossMargin = grossMargin;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "invoiceID='" + invoiceID + '\'' +
                ", 顾客=" + customer +
                ", 日期='" + date + '\'' +
                ", 付款='" + payment + '\'' +
                ", 折扣=" + rating +
                '}';
    }

    @Override
    public int hashCode() {
        return this.invoiceID.hashCode();
    }
}
