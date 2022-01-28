/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

/**
 * ����� ������������ ���� ������ �� ������� Authors ���� ������, �.�. ������ ��
 * ������� ����������. ������� Authors ����� ��������� ���������: -
 * ������������� ������, �������������� ����� ������, �������� ��������� ������
 * �������; - ���� � ������ � �������� ������ ������ �� 64 ��������
 * ������������, ������� �� ����� ���� ������; - ���� ���������� ������ �� 255
 * �������� ������������.
 *
 * @author ((C)Y.D.Zakovryashin, 12.11.2020
 */

import java.util.Objects;

/**
 *
 * @author user
 */
public class Authors{
   public static final int VERSION = 79897;
    private final int author_id;
    private String author;
    private String notes;

    public Authors(int author_id, String author) {
        this(author_id, author, null);
    }

    public Authors(int author_id, String author, String notes) {
        this.author_id = author_id;
        this.author = author;
        this.notes = notes;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        return VERSION + this.author_id + Objects.hashCode(this.author);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Authors)) {
            return false;
        }

        final Authors other = (Authors) obj;
        return !(this.author_id != other.author_id
                || !Objects.equals(this.author, other.author));
    }
}
