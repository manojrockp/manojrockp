import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class Student extends JFrame implements ActionListener {
JButton btnAdd, btnBrowse,btnDele;
JLabel labid,labname,labidlist,labnamelist;
JTextArea tasid, tasname;
JTextField tfStudId, tfStudName;
Connection con;
public Student() {
btnAdd = new JButton("Add");
btnBrowse = new JButton("Browse");
// btnDele=new JButton("Delete");
labid=new JLabel("Student ID:");
labname=new JLabel("Student Name:");
//labidlist=new JLabel("ID");
// labnamelist=new JLabel("Names");
tasid = new JTextArea(20, 20);
tasname = new JTextArea(20, 20);
tfStudId = new JTextField(10);
tfStudName = new JTextField(10);
setTitle("Student Information");
setSize(500, 400);
setVisible(true);
getContentPane().add(labid);
getContentPane().add(tfStudId);
getContentPane().add(labname);
getContentPane().add(tfStudName);
//getContentPane().add(labidlist);
getContentPane().add(tasid);
//getContentPane().add(labnamelist);
getContentPane().add(tasname);
getContentPane().add(btnAdd);
getContentPane().add(btnBrowse);
// getContentPane().add(btnDele);
getContentPane().setLayout(new FlowLayout());
btnAdd.addActionListener(this);
btnBrowse.addActionListener(this);
addWindowListener(new WindowAdapter(){
public void windowOpened(WindowEvent e){
tfStudId.requestFocus();
}
});
}
public static void main(String args[]) {
new Student();
}
public void actionPerformed(ActionEvent ae) {
if(ae.getSource() == btnAdd) {
try {
personalConnection();
Statement st = con.createStatement();
String sid = tfStudId.getText();
String sname = tfStudName.getText();
st.executeUpdate("insert into student values('"+sid+"','"+sname+"')");
tfStudId.setText("");
tfStudName.setText("");
st.close();
con.close();
}catch(Exception e) { }
}
if(ae.getSource() == btnBrowse) {
try {
personalConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from student");
tasid.setText("");
tasname.setText("");
while(rs.next()){
tasid.append(rs.getString("sid") + "\n");
tasname.append(rs.getString("sname") + "\n");
}
st.close();
con.close();
}catch(Exception e2) { }
}

if(ae.getSource() == btnDele)
{
try {
personalConnection();
Statement st = con.createStatement();
st.executeUpdate("delete from student");

}
catch(Exception ee)
{}
}

}

public void personalConnection() {
try {
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "mca");
System.out.println("Connected " + con);
}catch(Exception e) { }
}
}