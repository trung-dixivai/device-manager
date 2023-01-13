package views.status;

import daos.StatusDao;
import models.Device;
import models.Notification;
import models.Status;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class viewstatus {
    private static StatusDao statusDao = new StatusDao();
    private Vector<Status> statuses = statusDao.findAll();
    private Vector vtdata, colum;
    private JTable jtable;
    private JTextField name;
    private int id_update = 1;

    public viewstatus() {


        JFrame jFrame = new JFrame();

        jFrame.setTitle("VIEW DEVICE");
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(1100, 600);
        jFrame.setLayout(new GridLayout(1, 2));
        JPanel jPanel = new JPanel();


        Border border = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle = BorderFactory.createTitledBorder(border, "Thông Tin");
        jPanel.setBorder(borderTitle);
        jPanel.setBackground(Color.CYAN);
        jPanel.setLayout(new GridLayout(2, 1));
        JPanel jPaneld = new JPanel();
        jPaneld.setLayout(new GridLayout(1, 2));
        JPanel jPanelc = new JPanel();
        jPanelc.setLayout(new GridLayout(1, 1));
        jPanelc.add(new JTextField());
        jPanel.add(jPaneld);
        jPanel.add(jPanelc);
        JPanel jPanela = new JPanel();
        jPanela.setLayout(new GridLayout(5, 1));

        jPanela.add(new JLabel("  ID"));
        jPanela.add(new JLabel("  Name "));

        JPanel jPanelb = new JPanel();
        jPanelb.setLayout(new GridLayout(5, 1));
        JLabel idstatus = new JLabel();
        name = new JTextField();
        jPanelb.add(idstatus);
        jPanelb.add(name);


        jPaneld.add(jPanela);
        jPaneld.add(jPanelb);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(2, 1));
        JPanel jpanel12 = new JPanel();
        Border border1 = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle1 = BorderFactory.createTitledBorder(border1, "Danh Sách");
        jpanel12.setBorder(borderTitle1);
        jpanel12.setBackground(Color.cyan);

        jpanel12.setLayout(new GridLayout(1, 1));
        colum = new Vector();
        colum.add("ID");
        colum.add("Name");
        vtdata = statusDao.getData();

        jtable = new JTable();
        jtable.setModel((new DefaultTableModel(vtdata, colum)));
        jtable.setRowHeight(30);
        jtable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int row = jtable.getSelectedRow();
                Vector status = statusDao.findAll();
                name.setText(jtable.getModel().getValueAt(row, 1)+"");
                id_update=((Status)status.get(row)).getId();


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JScrollPane sp = new JScrollPane(jtable);
        jpanel12.add(sp);
        JPanel jPanel3 = new JPanel();
        Border border2 = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle2 = BorderFactory.createTitledBorder(border1, "Công Cụ");
        jPanel3.setBorder(borderTitle2);
        jPanel3.setBackground(Color.CYAN);
        jPanel3.setLayout(new FlowLayout());
        Icon iconthem = new ImageIcon("C:\\Users\\admin\\Downloads\\save1.png");
        JButton jbuttonthem = new JButton("Thêm ", iconthem);
        jbuttonthem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Status status = new Status();
                    status.setName(name.getText());
                    Notification<Status> notification = statusDao.create(status);
                    thongBao(notification);
                    System.out.println(notification);
                    nodata();

                } catch (Exception ex) {

                }


            }
        });

        jbuttonthem.setBackground(Color.YELLOW);
        jPanel3.add(jbuttonthem);
        Icon iconluu = new ImageIcon("C:\\Users\\admin\\Downloads\\save12.png");
        JButton jp2 = new JButton("Lưu", iconluu);
        jp2.setBackground(Color.YELLOW);
        jPanel3.add(jp2);

        Icon iconsua = new ImageIcon("C:\\Users\\admin\\Downloads\\sua.png");
        JButton sua = new JButton("Sửa", iconsua);
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Status status = new Status();
                    status.setId(id_update);
                    status.setName(name.getText());
                    Notification<Status> notification = statusDao.update(id_update, status);
                    thongBao(notification);
                    System.out.println(notification);
                    nodata();

                } catch (Exception ex) {

                }
            }
        });
        sua.setBackground(Color.YELLOW);
        jPanel3.add(sua);

        Icon iconxoa = new ImageIcon("C:\\Users\\admin\\Downloads\\delete.png");
        JButton xoa = new JButton("Xóa", iconxoa);
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Notification<Status> notification = statusDao.delete(id_update);
                    thongBao(notification);
                    System.out.println(notification);
                    nodata();
                } catch (Exception ex) {

                }
            }
        });
        xoa.setBackground(Color.YELLOW);
        jPanel3.add(xoa);
        Icon icontimkiem = new ImageIcon("C:\\Users\\admin\\Downloads\\111.png");
        JButton timkiem = new JButton("Tìm Kiếm", icontimkiem);
        timkiem.setBackground(Color.YELLOW);
        jPanel3.add(timkiem, icontimkiem);
        jPanel1.add(jpanel12);
        jPanel1.add(jPanel3);
        jFrame.add(jPanel);
        jFrame.add(jPanel1);
        jFrame.setVisible(true);


    }

    public void nodata() {
        name.setText(null);

    }

    public void showData() {
        vtdata = statusDao.getData();
        jtable.setModel(new DefaultTableModel(vtdata, colum));
    }


    private void thongBao(Notification<Status> notification) {
        if (notification.getCheck() == true) {
            showData();
            viewNotification(notification.getString());

        } else {
            viewNotification(notification.getString());
        }
    }

    private void viewNotification(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

    public static void main(String[] args) {
        new viewstatus();
    }
}
