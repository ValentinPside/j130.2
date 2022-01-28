/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Valentin
 */
public class DbServer implements IDbService{
    
    public DbServer(){
    }

    @Override
    public boolean addAuthor(Authors author) throws DocumentException {
        if (author.getAuthor_id() == 0 && author.getAuthor().isBlank()) {
            throw new DocumentException("Neither the author's id nor his name is specified!");
        }
        boolean isSuccess = false;
        try(Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/lab1", "lab1", "lab1")){
            if(author.getAuthor_id() != 0 && !author.getAuthor().isBlank()){
                try (PreparedStatement pstmt = conn.prepareStatement(
                    "insert into LAB1.AUTHORS (AUTHOR_ID, AUTHOR_NAME, NOTE)\n"
                            + "    values (?, ?, ?)")) {
                pstmt.setInt(1, author.getAuthor_id());
                pstmt.setString(2, author.getAuthor());
                pstmt.setString(3, author.getNotes());
                pstmt.executeUpdate();
                isSuccess = true;
                }
            }
            if(author.getAuthor_id() == 0 && !author.getAuthor().isBlank()){
                try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE LAB1.AUTHORS\n" +
                "SET NOTE = ?\n" +
                "WHERE AUTHOR_NAME = ?")){
                    pstmt.setString(1, author.getNotes());
                    pstmt.setString(2, author.getAuthor());
                    pstmt.executeUpdate();
                }
            }
            if(author.getAuthor_id() != 0 && author.getAuthor().isBlank()){
                try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE LAB1.AUTHORS\n" +
                "SET NOTE = ?\n" +
                "WHERE AUTHOR_ID = ?")){
                    pstmt.setString(1, author.getNotes());
                    pstmt.setInt(2, author.getAuthor_id());
                    pstmt.executeUpdate();
                }
            }
            
    }   catch (SQLException ex) {
        ex.getSQLState();
        }
        return isSuccess;
    }

    @Override
    public boolean addDocument(Documents doc, Authors author) throws DocumentException {
        if (author.getAuthor_id() == 0 && author.getAuthor().isBlank()) {
            throw new DocumentException("The author is not specified!");
        }
        if (doc.getDocument_id() == 0 && doc.getTitle().isBlank() && doc.getText().isBlank()) {
            throw new DocumentException("An empty document is specified!");
        }
        boolean isSucsses = false;
        try(Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/lab1", "lab1", "lab1")){
            if(doc.getDocument_id() != 0 && !doc.getTitle().isBlank() && !doc.getText().isBlank() && 
                    doc.getAuthor_id() != 0 && author.getAuthor_id() != 0 && 
                    !author.getAuthor().isBlank() && doc.getAuthor_id() == author.getAuthor_id()){
            try (PreparedStatement pstmt = conn.prepareStatement(
                    "insert into LAB1.DOCUMENTS (DOCUMENTS_ID, DOCUMENTS_NAME, TEXT, CREATE_DATE, AUTHOR_ID)\n"
                            + "    values (?, ?, ?, ?, ?)")) {
                pstmt.setInt(1, doc.getDocument_id());
                pstmt.setString(2, doc.getTitle());
                pstmt.setString(3, doc.getText());
                pstmt.setDate(4, new java.sql.Date(doc.getDate().getTime()));
                pstmt.setInt(5, doc.getAuthor_id());
                pstmt.executeUpdate();
                isSucsses = true;
            }}
            if(doc.getDocument_id() == 0 && !doc.getTitle().isBlank() && !doc.getText().isBlank() && 
                    doc.getAuthor_id() != 0){
                try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE LAB1.DOCUMENTS\n" +
                "SET TEXT = ?\n" +
                "WHERE DOCUMENTS_NAME = ?")){
                    pstmt.setString(1, doc.getText());
                    pstmt.setString(2, doc.getTitle());
                    pstmt.executeUpdate();
                }
            }
            if(doc.getDocument_id() != 0 && doc.getTitle().isBlank() && !doc.getText().isBlank() && 
                    doc.getAuthor_id() == 0){
                try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE LAB1.DOCUMENTS\n" +
                "SET TEXT = ?\n" +
                "WHERE DOCUMENTS_ID = ?")){
                    pstmt.setString(1, doc.getText());
                    pstmt.setInt(2, doc.getDocument_id());
                    pstmt.executeUpdate();
                }
            }
            if(doc.getDocument_id() != 0 && !doc.getTitle().isBlank() && !doc.getText().isBlank() && 
                    doc.getAuthor_id() != 0){
                try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE LAB1.DOCUMENTS\n" +
                "SET TEXT = ?\n" +
                "WHERE AUTHOR_ID = ?")){
                    pstmt.setString(1, doc.getText());
                    pstmt.setInt(2, doc.getAuthor_id());
                    pstmt.executeUpdate();
                }
            }
            if(doc.getDocument_id() == 0 && doc.getTitle().isBlank() && !doc.getText().isBlank() && 
                    doc.getAuthor_id() != 0){
                try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE LAB1.DOCUMENTS\n" +
                "SET TEXT = ?\n" +
                "WHERE AUTHOR_ID = ?")){
                    pstmt.setString(1, doc.getText());
                    pstmt.setInt(2, doc.getAuthor_id());
                    pstmt.executeUpdate();
                }
            }
    }   catch (SQLException ex) {
        ex.getMessage();
        }
        return isSucsses;
    }

    @Override
    public Documents[] findDocumentByAuthor(Authors author) throws DocumentException {
        if (author.getAuthor_id() == 0 && author.getAuthor().isBlank()) {
            throw new DocumentException("Neither the author's id nor his name is specified!");
        }
        ArrayList<Documents> doc = new ArrayList<>();
        Documents[] docs = null;
        int id;
        try(Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/lab1", "lab1", "lab1")){
            if(author.getAuthor_id() == 0){
                try(PreparedStatement prst = conn.prepareStatement(
                            "SELECT AUTHOR_ID\n"+
                            "FROM LAB1.AUTHORS\n"+
                            "WHERE AUTHOR_NAME = ?")){
                prst.setString(1, author.getAuthor());
                ResultSet rs = prst.executeQuery();
                rs.next();
                id = rs.getInt(1);
                }                          
            try(PreparedStatement prst = conn.prepareStatement("SELECT *\n"+
                            "FROM LAB1.DOCUMENTS\n"+
                            "WHERE AUTHOR_ID = ?")){
                prst.setInt(1, id);
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {                    
                    doc.add(new Documents(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getDate(4), rs.getInt(5)));
                }
                docs = new Documents[doc.size()];
                docs = doc.toArray(docs);
            }
    }
            if(author.getAuthor_id() != 0){
               try(PreparedStatement prst = conn.prepareStatement("SELECT *\n"+
                            "FROM LAB1.DOCUMENTS\n"+
                            "WHERE AUTHOR_ID = ?")){
                prst.setInt(1, author.getAuthor_id());
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {                    
                    doc.add(new Documents(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getDate(4), rs.getInt(5)));
                }
                docs = new Documents[doc.size()];
                docs = doc.toArray(docs);
               }
            }
        }   catch (SQLException ex) {
            ex.getMessage();
            }
        return docs;
    }

    @Override
    public Documents[] findDocumentByContent(String content) throws DocumentException {
        if (content.isBlank()) {
            throw new DocumentException("No search word entered!");
        }
        Documents[] doc = null;
        ArrayList<Documents> doc1 = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/lab1", "lab1", "lab1")){
             try(PreparedStatement prst = conn.prepareStatement("SELECT *\n"+
                            "FROM LAB1.DOCUMENTS\n"+
                            "WHERE (DOCUMENTS_NAME like '%?%') OR (TEXT like %?%)" )){
                prst.setString(1, content);
                prst.setString(2, content);
                ResultSet rs1 = prst.executeQuery();
                 while (rs1.next()) {                     
                     doc1.add(new Documents(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getDate(4), rs1.getInt(5)));
                 }
                 if (doc1.isEmpty()) {
            throw new DocumentException("No matches found!");
        }
                doc = new Documents[doc1.size()];
                doc = doc1.toArray(doc);
                }
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return doc;
    }

    @Override
    public boolean deleteAuthor(Authors author) throws DocumentException {
        if (author.getAuthor_id() == 0 && author.getAuthor().isBlank()) {
            throw new DocumentException("Neither the author's id nor his name is specified!");
        }
        boolean isSuccess = false;
        String aut = "DELETE FROM LAB1.AUTHORS\n"+
                    "WHERE AUTHOR_ID = ?\n";
        String doc = "DELETE FROM LAB1.DOCUMENTS\n"+
                    "WHERE AUTHOR_ID = ?\n";
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/lab1", "lab1", "lab1")){
            if(author.getAuthor_id() !=0){
            try(PreparedStatement pst = conn.prepareStatement(doc)){
                pst.setInt(1, author.getAuthor_id());
                pst.executeUpdate();
            }
            try(PreparedStatement pst = conn.prepareStatement(aut)){
                pst.setInt(1, author.getAuthor_id());
                pst.executeUpdate();
                isSuccess = true;
            }
        }
        if(author.getAuthor_id() == 0 && !author.getAuthor().isBlank()){
            int id;
                try(PreparedStatement prst = conn.prepareStatement(
                            "SELECT AUTHOR_ID\n"+
                            "FROM LAB1.AUTHORS\n"+
                            "WHERE AUTHOR_NAME = ?")){
                prst.setString(1, author.getAuthor());
                ResultSet rs = prst.executeQuery();
                rs.next();
                id = rs.getInt(1);
                }                          
            try(PreparedStatement pst = conn.prepareStatement(doc)){
                pst.setInt(1, id);
                pst.executeUpdate();
            }
            try(PreparedStatement pst = conn.prepareStatement(aut)){
                pst.setInt(1, id);
                pst.executeUpdate();
                isSuccess = true;
            }
    }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return isSuccess;
    }

    @Override
    public boolean deleteAuthor(int id) throws DocumentException {
        boolean isSuccess = false;
        String author = "DELETE FROM LAB1.AUTHORS\n"+
                    "WHERE AUTHOR_ID = ?\n";
        String doc = "DELETE FROM LAB1.DOCUMENTS\n"+
                    "WHERE AUTHOR_ID = ?\n";
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/lab1", "lab1", "lab1")){
            try(PreparedStatement prst = conn.prepareStatement(doc)){
            prst.setInt(1, id);
            prst.executeUpdate();
            }
            try(PreparedStatement prst1 = conn.prepareStatement(author)){
            prst1.setInt(1, id);
            prst1.executeUpdate();
            isSuccess = true;
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return isSuccess;
    }  

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
