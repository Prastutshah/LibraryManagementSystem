

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Objects;

import net.proteanit.sql.DbUtils;


//Build a Main class extends JFrame implements ActionListener
public class Student extends JFrame implements ActionListener {

    Main main = new Main();

    //calling Database class
    Database database = new Database();

    //Initializing variable
    JLabel label1;
    JLabel label2;
    JButton reserve;
    JButton viewBorrowedBooks;
    JButton backButton;
    JComboBox<String> bookComboBox;

    JScrollPane studentScrollPane;
    private String selectedBook;

    //    making a constructor
    public Student() {

        //Declaring Variable
        label1 = new JLabel("Student Page");
        label1.setIcon(main.imageIcon);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setIconTextGap(20);
        label1.setBounds(485, 30, 450, 250);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label2 = new JLabel("Choose Book: ");
        label2.setBounds(500, 300, 100, 20);

        bookComboBox = new JComboBox<>();
        bookComboBox.setBounds(600, 300, 250, 20);
        bookBox();
        bookComboBox.addActionListener(this);

        reserve = new JButton("Reserve");
        reserve.setBounds(625, 350, 200, 40);
        reserve.addActionListener(this);

        viewBorrowedBooks = new JButton("Borrowed Books");
        viewBorrowedBooks.setBounds(625, 400, 200, 40);
        viewBorrowedBooks.addActionListener(this);

        this.setVisible(true);
        this.setTitle("Student");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));

        this.add(label1);
        this.add(label2);
        this.add(reserve);

        this.add(bookComboBox);
        this.add(viewBorrowedBooks);
        back();
        main.dispose();

    }

    public static void main(String[] args) {

        new Student();

    }

    void back() {

        backButton = new JButton("Back");
        backButton.setBounds(40, 20, 100, 25);
        backButton.addActionListener(this);

        this.add(backButton);

    }

    //    method to fetch module content from database
    void bookBox() {

        try {
            bookComboBox.addItem("--Select--");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookname FROM books;");
            while (rs.next()) {

                String name = rs.getString("bookname");
                bookComboBox.addItem(name);

            }
            con.close();

        } catch (Exception e3) {

            JOptionPane.showMessageDialog(this, e3.getMessage());

        }

    }

    void studentTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM studentData WHERE username = '" + main.student + "';");

            JTable table = new JTable();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            studentScrollPane = new JScrollPane(table);
            studentScrollPane.setBounds(350, 450, 800, 200);
            studentScrollPane.setVisible(true);
            this.add(studentScrollPane);


        } catch (Exception e1) {

            e1.printStackTrace();

        }
    }
    Method refresh() {
        bookComboBox.removeAllItems();
        bookBox();
        studentTable();
        return null;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookComboBox) {
            selectedBook = Objects.requireNonNull(bookComboBox.getSelectedItem()).toString();

        } else if (e.getSource() == reserve) {
            if (bookComboBox.getSelectedItem().equals("--Select--")) {
                JOptionPane.showMessageDialog(this, "Please select a book");
            } else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM studentData WHERE bookName = '" + selectedBook + "' AND username = '" + main.student + "';");

                    while (rs.next()) {
                        String bookStatus = rs.getString("status");
                        if (bookStatus.equals("Reserved")) {
                            JOptionPane.showMessageDialog(this, "Book is already reserved");
                            return;
                        } else if (bookStatus.equals("Issued")) {
                            JOptionPane.showMessageDialog(this, "Book is already borrowed");
                            return;
                        }

                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
                try {
                    String rSql = "SELECT * FROM `studentData`;";
                    String sql = "INSERT INTO `studentData`(`username`, `bookName`, `issued`, `deadline`, `status`) VALUES ('" + main.student + "','" + selectedBook + "','" + java.time.LocalDate.now() + "', '" + java.time.LocalDate.now().plusDays(7) + "','Reserved');";
                    String message = " " + bookComboBox.getSelectedItem() + " Book Reserved added successfully";
                    database.dbExecution(rSql, sql, message,refresh());
                    bookBox();
                    studentTable();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }


            }
        } else if (e.getSource() == backButton) {

            this.dispose();
            new Main();

        } else if (e.getSource() == viewBorrowedBooks) {

            studentTable();

        }

    }

}

