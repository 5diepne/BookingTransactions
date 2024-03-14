package dao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.Transaction;

public class DBContext implements DatabaseInfo, Serializable {

    private int cardNumber;
    private String startDate;
    private String endDate;

    public DBContext() {
    }

    public DBContext(int cardNumber, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<Card> getAll() {
        List<Card> list = new ArrayList<>();
        String sql = "select * from Cards";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Card c = new Card(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        String sql = "select * from Transactions";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Transaction> getTransactionsHistory(String card_number) {
        List<Transaction> list = new ArrayList<>();
        String sql = "select * from Transactions where card_number = ?";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, card_number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertTransaction(String cardNumber, String transactionDate, String amount, String type) {
        String sql = "INSERT INTO [dbo].[Transactions]\n"
                + "           ([card_number]\n"
                + "           ,[transaction_date]\n"
                + "           ,[amount]\n"
                + "           ,[transaction_type])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cardNumber);
            ps.setString(2, transactionDate);
            ps.setString(3, amount);
            ps.setString(4, type);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Transaction> getTransactionsWithDate() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM Transactions\n"
                + "WHERE card_number = ? \n"
                + "	AND transaction_date >= ?\n"
                + "	AND transaction_date <= ?";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cardNumber);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Card getCardByCardNumber(String cardNumber) {
        String sql = "select * from Cards where card_number = ?";
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cardNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Card(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        DBContext dao = new DBContext();
//        dao.setCardNumber(1);
//        dao.setStartDate("2024-01-05");
//        dao.setEndDate("2024-06-05");
//        List<Transaction> list = dao.getTransactionsWithDate();
//        for (Transaction o : list) {
//            System.out.println(o);
//        }
//
//        System.out.println(dao.getCardByCardNumber("10"));
        System.out.println(dao.getAllTransactions().get(0));
    }
//---------------------------------------------------------

//---------------------------------------------------------------------------
}
