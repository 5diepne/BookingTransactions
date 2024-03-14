<%-- 
    Document   : index
    Created on : Mar 13, 2024, 10:43:03 PM
    Author     : DiepTCNN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="data" class="dao.DBContext"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INSERT NEW TRANSACTION</h1>
        <p style="color: green">${valid}</p>
        <p style="color: red">${invalid}</p>
        <form action="insert" method="post">
            <table>
                <tr>
                    <td>Card number:</td><td><input name="cardNumber" type="number"/></td>
                </tr>
                <tr>
                    <td>Transaction Date:</td><td><input name="transactionDate" type="date"/></td>
                </tr>
                <tr>
                    <td>Amount:</td><td> <input name="amount" type="number" step="0.01"/></td>
                </tr>
                <tr>
                    <td>Type:</td><td><input name="type" type="text"/></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" value="INSERT"/></td>
                </tr>
            </table>
        </form>
        <h1>LIST OF TRANSACTIONS</h1>
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>Card number</th>
                <th>Transaction Date</th>
                <th>Amount</th>
                <th>Type</th>
            </tr>
            <c:forEach items="${data.allTransactions}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.cardNumber}</td>
                    <td>${o.transactionDate}</td>
                    <td>${o.amount}</td>
                    <td>${o.type}</td>
                </tr>
            </c:forEach>
        </table>
        
        
        <form action="index.jsp">
            <table>
                <tr>
                    <td>Card number:</td><td><input name="card_Number" type="number"/></td>
                </tr>
                <tr>
                    <td>Start Date:</td><td><input name="startDate" type="date"/></td>
                </tr>
                <tr>
                    <td>End Date:</td><td> <input name="endDate" type="date"/></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" value="FIND"/></td>
                </tr>
            </table>
        </form>
        <jsp:setProperty name="data" property="cardNumber" value="${param.card_Number}"/>
        <jsp:setProperty name="data" property="startDate" value="${param.startDate}"/>
        <jsp:setProperty name="data" property="endDate" value="${param.endDate}"/>
        <h1>LIST OF TRANSACTIONS FROM <jsp:getProperty name="data" 
                         property="startDate"/> TO <jsp:getProperty name="data" 
                         property="endDate" /> OF CARD NUMBER <jsp:getProperty name="data" 
                         property="cardNumber" /></h1>
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>Card number</th>
                <th>Transaction Date</th>
                <th>Amount</th>
                <th>Type</th>
            </tr>
            <c:forEach items="${data.transactionsWithDate}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.cardNumber}</td>
                    <td>${o.transactionDate}</td>
                    <td>${o.amount}</td>
                    <td>${o.type}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
