/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DiepTCNN
 */
public class Card {
    private int cardNumber;
    private String accountNumber;
    private String customerName;
    private String expirationDate;
    private int isBlocked;

    public Card() {
    }

    public Card(int cardNumber, String accountNumber, String customerName, String expirationDate, int isBlocked) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.expirationDate = expirationDate;
        this.isBlocked = isBlocked;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "Card{" + "cardNumber=" + cardNumber + ", accountNumber=" + accountNumber + ", customerName=" + customerName + ", expirationDate=" + expirationDate + ", isBlocked=" + isBlocked + '}';
    }
    
}
