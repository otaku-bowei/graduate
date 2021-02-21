package crm.entity;

import java.util.Objects;

public class Member {

    private int customerId ;
    private String genre ;
    private int age ;
    private double annualIncome ;
    private int spendingScore ;
    private String name ;

    public Member(){
    }

    public Member(int customerId, String genre, int age, double annualIncome, int spendingScore, String name) {
        this.customerId = customerId;
        this.genre = genre;
        this.age = age;
        annualIncome = annualIncome;
        this.spendingScore = spendingScore;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return customerId == member.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return "Member{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                '}';
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        annualIncome = annualIncome;
    }

    public int getSpendingScore() {
        return spendingScore;
    }

    public void setSpendingScore(int spendingScore) {
        this.spendingScore = spendingScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
