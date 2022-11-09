
//Importing package

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

import net.proteanit.sql.DbUtils;


//Building Classes
public class Administrator extends JFrame implements ActionListener {

    //Calling Main class
    Main main = new Main();

    //Calling Database class
    Database database = new Database();

    //Initializing variable
    JLabel jImageIcon, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;

    JTextField bookName;

    JTextField bookNumber;

    JComboBox<String> bookBox;
    JComboBox<String> bookIssueBox;
    JComboBox<String> studentBox;
    JComboBox<String> reservedBookBox;
    JComboBox<String> reservedStudentBox;

    JComboBox<String> returnBookBox;
    JComboBox<String> returnStudentBox;
    JButton deleteBook;
    JButton addBook;
    JButton backButton;

    JButton issueBook;
    JButton reservedIssueBook;

    JButton reservedBook;

    JButton libraryBook;

    JButton returnBook;

    JButton showStudentDetails;

    JButton showBookDetails;

    //    making a constructor
    public Administrator() {

//        Declaring variable
        jImageIcon = new JLabel(main.imageIcon);
        jImageIcon.setHorizontalTextPosition(JLabel.CENTER);
        jImageIcon.setVerticalTextPosition(JLabel.BOTTOM);
        jImageIcon.setBounds(485, 0, 440, 180);

        label1 = new JLabel("Administrator Page");
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setIconTextGap(20);
        label1.setBounds(650, 140, 250, 130);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));


        label2 = new JLabel("Books");
        label2.setBounds(200, 250, 100, 20);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label3 = new JLabel("Book Name: ");
        label3.setBounds(30, 300, 100, 20);

        bookName = new JTextField();
        bookName.setBounds(120, 300, 300, 20);

        bookBox = new JComboBox<>();
        bookBox.setBounds(120, 330, 300, 20);
        bookCombobox();
        bookBox.addActionListener(this);

        label4 = new JLabel("No of Books: ");
        label4.setBounds(30, 360, 150, 20);

        bookNumber = new JTextField();
        bookNumber.setBounds(120, 360, 300, 20);

        addBook = new JButton("Add");
        addBook.setBounds(125, 400, 80, 20);
        addBook.addActionListener(this);

        deleteBook = new JButton("Remove");
        deleteBook.setBounds(325, 400, 80, 20);
        deleteBook.addActionListener(this);

        label5 = new JLabel("Issue Book");
        label5.setBounds(200, 450, 100, 20);
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        reservedBook = new JButton("Reserved Book");
        reservedBook.setBounds(30, 490, 150, 20);
        reservedBook.addActionListener(this);

        reservedBookBox = new JComboBox<>();
        reservedBookBox.setBounds(120, 540, 300, 20);
        reservedBookCombobox();
        reservedBookBox.addActionListener(this);

        reservedStudentBox = new JComboBox<>();
        reservedStudentBox.setBounds(120, 580, 300, 20);
        reservedStudentCombobox();
        reservedStudentBox.addActionListener(this);

        reservedIssueBook = new JButton("Issue Book");
        reservedIssueBook.setBounds(150, 620, 200, 20);
        reservedIssueBook.addActionListener(this);

        libraryBook = new JButton("Library Book");
        libraryBook.setBounds(330, 490, 150, 20);
        libraryBook.addActionListener(this);

        label6 = new JLabel("Select Book: ");
        label6.setBounds(30, 540, 100, 20);

        bookIssueBox = new JComboBox<>();
        bookIssueBox.setBounds(120, 540, 300, 20);
        bookIssueCombobox();
        bookIssueBox.addActionListener(this);

        label7 = new JLabel("Select Student: ");
        label7.setBounds(30, 580, 100, 20);

        studentBox = new JComboBox<>();
        studentBox.setBounds(120, 580, 300, 20);
        studentCombobox();
        studentBox.addActionListener(this);

        issueBook = new JButton("Issue Book");
        issueBook.setBounds(150, 620, 200, 20);
        issueBook.addActionListener(this);

        label8 = new JLabel("Return Book");
        label8.setBounds(200, 650, 120, 20);
        label8.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label9 = new JLabel("Select Book: ");
        label9.setBounds(30, 700, 100, 20);

        returnBookBox = new JComboBox<>();
        returnBookBox.setBounds(120, 700, 300, 20);
        returnBookCombobox();
        returnBookBox.addActionListener(this);

        label10 = new JLabel("Select Student: ");
        label10.setBounds(30, 750, 100, 20);

        returnStudentBox = new JComboBox<>();
        returnStudentBox.setBounds(120, 750, 300, 20);
        returnStudentCombobox();
        returnStudentBox.addActionListener(this);

        returnBook = new JButton("Return Book");
        returnBook.setBounds(150, 800, 200, 20);
        returnBook.addActionListener(this);

        showStudentDetails = new JButton("Refresh Student Details");
        showStudentDetails.setBounds(700, 250, 200, 20);
        showStudentDetails.addActionListener(this);

        showBookDetails = new JButton("Refresh Book Details");
        showBookDetails.setBounds(700, 600, 200, 20);
        showBookDetails.addActionListener(this);


        this.setVisible(true);
        this.setTitle("Administrator");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));

        this.add(jImageIcon);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        label6.setVisible(false);
        this.add(label7);
        label7.setVisible(false);
        this.add(label8);
        this.add(label9);
        this.add(label10);


        this.add(bookName);
        this.add(bookBox);

        this.add(bookNumber);
        this.add(addBook);
        this.add(deleteBook);

        this.add(reservedBook);
        this.add(libraryBook);

        this.add(bookIssueBox);
        bookIssueBox.setVisible(false);
        this.add(studentBox);
        studentBox.setVisible(false);
        this.add(issueBook);
        issueBook.setVisible(false);

        this.add(reservedBookBox);
        reservedBookBox.setVisible(false);
        this.add(reservedStudentBox);
        reservedStudentBox.setVisible(false);

        this.add(reservedIssueBook);
        reservedIssueBook.setVisible(false);

        this.add(returnBookBox);
        this.add(returnStudentBox);
        this.add(returnBook);

        this.add(showStudentDetails);
        this.add(showBookDetails);

        studentTable();
        bookTable();

        back();
        main.dispose();
    }

    public static void main(String[] args) {


        new Administrator();

    }

    //Adding Back button in the JFrame
    void back() {

        backButton = new JButton("Back");
        backButton.setBounds(40, 20, 100, 25);
        backButton.addActionListener(this);

        this.add(backButton);

    }

    //    method to fetch book content from database
    void bookCombobox() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookname FROM books;");

            while (rs.next()) {

                String name = rs.getString("bookname");

                bookBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }

    void bookIssueCombobox() {

        try {
            bookIssueBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookname FROM books;");

            while (rs.next()) {

                String name = rs.getString("bookname");

                bookIssueBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }

    void studentCombobox() {
        try {
            studentBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT username FROM login where user='student';");

            while (rs.next()) {

                String name = rs.getString("username");

                studentBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }

    void reservedBookCombobox() {

        try {
            reservedBookBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookName FROM `studentData` WHERE `status` = 'Reserved';");

            while (rs.next()) {

                String name = rs.getString("bookName");

                reservedBookBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }

    void reservedStudentCombobox() {
        try {
            reservedStudentBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT username FROM `studentData` WHERE bookName = '" + reservedBookBox.getSelectedItem() + "' AND `status` = 'Reserved';");

            while (rs.next()) {

                String name = rs.getString("username");
                reservedStudentBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }

    void returnBookCombobox() {

        try {
            returnBookBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookName FROM studentData where status='Issued';");

            while (rs.next()) {

                String name = rs.getString("bookName");

                returnBookBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }


    void returnStudentCombobox() {
        try {
            returnStudentBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT username FROM studentData WHERE bookName = '" + returnBookBox.getSelectedItem() + "' AND status='Issued';");

            while (rs.next()) {

                String name = rs.getString("username");

                returnStudentBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }


    void studentTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM studentData;");
            JTable student_list = new JTable();
            student_list.setEnabled(false);
            student_list.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(student_list);
            scrollPane.setSize(800, 200);
            scrollPane.setBounds(500, 300, 800, 200);
            scrollPane.createVerticalScrollBar();
            scrollPane.setVisible(true);
            this.add(scrollPane);

            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }

    void bookTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books;");
            JTable book_list = new JTable();
            book_list.setEnabled(false);
            book_list.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(book_list);
            scrollPane.setSize(800, 200);
            scrollPane.setBounds(500, 650, 800, 200);
            scrollPane.createVerticalScrollBar();
            scrollPane.setVisible(true);
            this.add(scrollPane);

            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }


    }

    Method refresh() {
        bookBox.removeAllItems();
        bookCombobox();
        bookIssueBox.removeAllItems();
        bookIssueCombobox();
        studentBox.removeAllItems();
        studentCombobox();
        returnBookBox.removeAllItems();
        returnBookCombobox();
        returnStudentBox.removeAllItems();
        returnStudentCombobox();
        reservedBookBox.removeAllItems();
        reservedBookCombobox();
        reservedStudentBox.removeAllItems();
        reservedStudentCombobox();
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookBox) {

            bookName.setText((String) bookBox.getSelectedItem());

        } else if (e.getSource() == backButton) {

            this.dispose();
            new Main();

        } else if (e.getSource() == reservedBookBox) {
            reservedStudentBox.removeAllItems();
            reservedStudentCombobox();
        } else if (e.getSource() == returnBookBox) {
            returnStudentBox.removeAllItems();
            returnStudentCombobox();

        } else if (e.getSource() == deleteBook) {
            if (bookBox.getSelectedItem() == "-----Select-----") {
                JOptionPane.showMessageDialog(this, "Please select a book to delete");
            } else{
                String noOfBook = bookNumber.getText();
                int noOfBookInt = Integer.parseInt(noOfBook);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                            "");

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM books WHERE bookName = '" + bookBox.getSelectedItem() + "';");
                    while (rs.next()) {
                        int bookNumber = noOfBookInt;
                        int quantity = rs.getInt("quantity");
                        if (quantity - bookNumber == 0) {
                            st.executeUpdate("DELETE FROM books WHERE bookName = '" + bookBox.getSelectedItem() + "';");
                            JOptionPane.showMessageDialog(this, "Book deleted successfully");
                            refresh();
                        } else if (quantity - bookNumber < 0) {
                            JOptionPane.showMessageDialog(this, "You can't delete more than the quantity of the book");
                        } else {
                            st.executeUpdate("UPDATE books SET quantity = '" + (quantity - bookNumber) + "' WHERE bookName = '" + bookBox.getSelectedItem() + "';");
                            JOptionPane.showMessageDialog(this, "Book deleted successfully");
                            refresh();
                        }
                    }
                    con.close();

                } catch (Exception e2) {

                    JOptionPane.showMessageDialog(this, e2.getMessage());

                }
            }

        } else if (e.getSource() == addBook) {

            try {
                //update quantity if bookName already exists else insert new book
                if (Objects.equals(bookBox.getSelectedItem(), bookName.getText())) {
                    String rSql = "SELECT * FROM `books` WHERE bookname = '" + bookName.getText() + "' ";
                    String sql = "UPDATE `books` SET quantity = quantity + " + bookNumber.getText() + " WHERE bookname = '" + bookName.getText() + "' ";
                    String message = " " + bookName.getText() + " Book added successfully";
                    database.dbExecution(rSql, sql, message, refresh());
                    refresh();
                } else {
                    String rSql = "SELECT * FROM `books`;";
                    String sql = "INSERT INTO `books`(`bookname`, `quantity`) VALUES ('" + bookName.getText() + "', '" + bookNumber.getText() + "')";
                    String message = " " + bookName.getText() + " Book added successfully";
                    database.dbExecution(rSql, sql, message, refresh());
                    refresh();
                }


            } catch (Exception e9) {

                e9.printStackTrace();

            }

        } else if (e.getSource() == issueBook) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM studentData WHERE bookName = '" + bookIssueBox.getSelectedItem() + "' AND username = '" + studentBox.getSelectedItem() + "';");

                while (rs.next()) {
                    String bookStatus = rs.getString("status");
                    if (bookStatus.equals("Reserved")) {
                        JOptionPane.showMessageDialog(this, "Book is already reserved Issue it from the reserve section");
                        return;
                    } else if (bookStatus.equals("Issued")) {
                        JOptionPane.showMessageDialog(this, "Book is already borrowed by the student");
                        return;
                    }

                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
            try {
                try {
                    String rSql = "SELECT * FROM `books` WHERE bookname = '" + bookIssueBox.getSelectedItem() + "' ";
                    String sql = "INSERT INTO `studentData`(`username`, `bookName`, `issued`, `deadline`,`status`) VALUES ('" + studentBox.getSelectedItem() + "', '" + bookIssueBox.getSelectedItem() + "', '" + LocalDate.now() + "', '" + LocalDate.now().plusDays(30) + "', 'Issued')";
                    String message = " " + bookIssueBox.getSelectedItem() + " Book issued successfully";
                    database.dbExecution(rSql, sql, message, refresh());
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                String rSql = "SELECT * FROM `books` WHERE bookname = '" + bookIssueBox.getSelectedItem() + "' ";
                String sql = "UPDATE `books` SET quantity = quantity - 1 WHERE bookname = '" + bookIssueBox.getSelectedItem() + "' ";
                String message = " " + bookIssueBox.getSelectedItem() + " Book provide from library successfully";
                database.dbExecution(rSql, sql, message, refresh());
                refresh();


            } catch (Exception e10) {

                e10.printStackTrace();

            }

        } else if (e.getSource() == reservedIssueBook) {
            try {
                try {
                    String rSql = "SELECT * FROM `studentData` WHERE bookName = '" + reservedBookBox.getSelectedItem() + "' ";
                    String sql = "UPDATE `studentData` SET `issued` = '" + LocalDate.now() + "', `deadline` = '" + LocalDate.now().plusDays(30) + "', `status` = 'Issued' WHERE bookName = '" + reservedBookBox.getSelectedItem() + "' AND username = '" + reservedStudentBox.getSelectedItem() + "' ";
                    String message = " " + reservedBookBox.getSelectedItem() + " Book Issued successfully";
                    database.dbExecution(rSql, sql, message, refresh());
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                String rSql = "SELECT * FROM `books` WHERE bookname = '" + reservedBookBox.getSelectedItem() + "' ";
                String sql = "UPDATE `books` SET quantity = quantity - 1 WHERE bookname = '" + reservedBookBox.getSelectedItem() + "' ";
                String message = " " + reservedBookBox.getSelectedItem() + " Book provided from reservation successfully";
                database.dbExecution(rSql, sql, message, refresh());
                refresh();

            } catch (Exception e10) {
                e10.printStackTrace();
            }


        } else if (e.getSource() == returnBook) {

            try {
                try {
                    String rSql = "SELECT * FROM `studentData` WHERE bookName = '" + returnBookBox.getSelectedItem() + "' ";
                    String sql = "DELETE FROM `studentData` WHERE bookName = '" + returnBookBox.getSelectedItem() + "' AND username = '" + returnStudentBox.getSelectedItem() + "'AND status = 'Issued' ";
                    String message = " " + returnBookBox.getSelectedItem() + " Book returned successfully";
                    database.dbExecution(rSql, sql, message, refresh());
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                String rSql = "SELECT * FROM `books` WHERE bookname = '" + returnBookBox.getSelectedItem() + "' ";
                String sql = "UPDATE `books` SET quantity = quantity + 1 WHERE bookname = '" + returnBookBox.getSelectedItem() + "' ";
                String message = " " + returnBookBox.getSelectedItem() + " Book added back to library successfully";
                database.dbExecution(rSql, sql, message, refresh());


            } catch (Exception e11) {

                e11.printStackTrace();


            }

        } else if (e.getSource() == reservedBook) {
            reservedBook.setVisible(false);
            libraryBook.setVisible(true);
            libraryBook.setBounds(150, 490, 200, 20);
            label6.setVisible(true);
            label7.setVisible(true);
            bookIssueBox.setVisible(false);
            studentBox.setVisible(false);
            issueBook.setVisible(false);
            reservedBookBox.setVisible(true);
            reservedStudentBox.setVisible(true);
            reservedIssueBook.setVisible(true);

        } else if (e.getSource() == libraryBook) {
            libraryBook.setVisible(false);
            reservedBook.setVisible(true);
            reservedBook.setBounds(150, 490, 200, 20);
            label6.setVisible(true);
            label7.setVisible(true);
            bookIssueBox.setVisible(true);
            studentBox.setVisible(true);
            issueBook.setVisible(true);


        } else if (e.getSource() == showStudentDetails) {
            studentTable();
        } else if (e.getSource() == showBookDetails) {
            bookTable();

        }

    }


}
