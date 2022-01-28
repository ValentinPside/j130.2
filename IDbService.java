/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab2;

/**
 *
 * @author user
 */
public interface IDbService extends AutoCloseable{
    boolean addAuthor(Authors author) throws DocumentException;
    boolean addDocument(Documents doc, Authors author) throws DocumentException;
    Documents[] findDocumentByAuthor(Authors author) throws DocumentException;
    Documents[] findDocumentByContent(String content) throws DocumentException;
    boolean deleteAuthor(Authors author) throws DocumentException;
    boolean deleteAuthor(int id) throws DocumentException;
}
