/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

/**
 * Класс представляет общее исключение, возникающее при работе с документами.
 * @author (C)Y.D.Zakovryashin, 12.11.2020
 */
public class DocumentException extends Exception {
    public DocumentException() {
    }

    public DocumentException(String string) {
        super(string);
    }
}
