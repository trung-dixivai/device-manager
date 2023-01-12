package views.status;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Properties;

public class viewstatus  {

    public static void main(String[] args) {


        JFrame jFrame=new JFrame();

        jFrame.setTitle("VIEW DEVICE");
        ImageIcon imageIcon=new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(1100,600);
        jFrame.setLayout(new GridLayout(1,2));
        JPanel jPanel=new JPanel();


        Border border= BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle= BorderFactory.createTitledBorder(border, "Thông Tin");
        jPanel.setBorder(borderTitle);
        jPanel.setBackground(Color.CYAN);
        jPanel.setLayout(new GridLayout(2,1));
        JPanel jPaneld=new JPanel();
        jPaneld.setLayout(new GridLayout(1,2));
        JPanel jPanelc=new JPanel();
        jPanelc.setLayout(new GridLayout(1,1));
        jPanelc.add(new JTextField());
        jPanel.add(jPaneld);
        jPanel.add(jPanelc);
        JPanel jPanela=new JPanel();
        jPanela.setLayout(new GridLayout(5,1));
        jPanela.add(new JLabel("  ID"));
        jPanela.add(new JLabel("  Name "));

        JPanel jPanelb=new JPanel();
        jPanelb.setLayout(new GridLayout(5,1));
        String id_category[]={"1","2"};
        JComboBox cb=new JComboBox(id_category);
        jPanelb.add(cb);



        jPaneld.add(jPanela);
        jPaneld.add(jPanelb);
        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(new GridLayout(2,1));
        JPanel jpanel12= new JPanel();
        Border border1=BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle1= BorderFactory.createTitledBorder(border1, "Danh Sách");
        jpanel12.setBorder(borderTitle1);
        jpanel12.setBackground(Color.cyan);

        jpanel12.setLayout(new GridLayout(1,1));

        String data[][]={ };
        String column[]={"ID","Name"};
        JTable jt=new JTable(data,column);
        jt.setRowHeight(30);

        JScrollPane sp=new JScrollPane(jt);
        jpanel12.add(sp);
        JPanel jPanel3=new JPanel();
        Border border2=BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle2= BorderFactory.createTitledBorder(border1, "Công Cụ");
        jPanel3.setBorder(borderTitle2);
        jPanel3.setBackground(Color.CYAN);
        jPanel3.setLayout( new FlowLayout());
        Icon iconthem= new ImageIcon("C:\\Users\\admin\\Downloads\\save1.png");
        JButton jbuttonthem= new JButton("Thêm ",iconthem);

        jbuttonthem.setBackground(Color.YELLOW);
        jPanel3.add(jbuttonthem);
        Icon iconluu= new ImageIcon("C:\\Users\\admin\\Downloads\\save12.png");
        JButton jp2= new JButton("Lưu",iconluu);
        jp2.setBackground(Color.YELLOW);
        jPanel3.add(jp2);

        Icon iconsua= new ImageIcon("C:\\Users\\admin\\Downloads\\sua.png");
        JButton sua=new JButton("Sửa",iconsua);
        sua.setBackground(Color.YELLOW);
        jPanel3.add(sua);

        Icon iconxoa = new ImageIcon("C:\\Users\\admin\\Downloads\\delete.png");
        JButton xoa=new JButton("Xóa",iconxoa);
        xoa.setBackground(Color.YELLOW);
        jPanel3.add(xoa);
        Icon icontimkiem = new ImageIcon("C:\\Users\\admin\\Downloads\\111.png");
        JButton timkiem=new JButton("Tìm Kiếm",icontimkiem);
        timkiem.setBackground(Color.YELLOW);
        jPanel3.add(timkiem,icontimkiem);
        jPanel1.add(jpanel12);
        jPanel1.add(jPanel3);
        jFrame.add(jPanel);
        jFrame.add(jPanel1);
        jFrame.setVisible(true);

    }

}
