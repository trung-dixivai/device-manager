package views.device;

import daos.DeviceDao;
import models.Category;
import models.Device;
import models.Notification;
import models.Status;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;
import java.util.Vector;

public class DeviceManger {
    private DeviceDao deviceDao = new DeviceDao();

    private JComboBox<Category> categoryJComboBox = new JComboBox<>();
    private JComboBox<Status> statusJComboBox = new JComboBox<>();
    private JTable table;
    private JTextField namedevice;
    private JTextField JTQuantity;
    private Vector vData, vTitle;
    private JTextArea mota;

    private JDatePickerImpl datePicker1;
    Vector<Device> deviceVector = this.deviceDao.findAll();
    Vector<Status> statusVector = this.deviceDao.getAllStatus();
    Vector<Category> categoryVector = this.deviceDao.getAllCateory();

    private int id_update = 1;


    public DeviceManger() {
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
        JPanel jPanelmota = new JPanel();
        jPanelmota.setLayout(new GridLayout(1, 1));
        mota = new JTextArea();
        mota.setFont(new Font("Monaco", Font.PLAIN, 20));
        jPanelmota.add(mota);
        jPanel.add(jPaneld);
        jPanel.add(jPanelmota);
        JPanel jPanela = new JPanel();
        jPanela.setLayout(new GridLayout(7, 1));
        jPanela.add(new JLabel("  ID"));
        jPanela.add(new JLabel("  Name Decive"));
        jPanela.add(new JLabel("  ID category"));
        jPanela.add(new JLabel("  ID status"));
        jPanela.add(new JLabel("  Import date"));
        jPanela.add(new JLabel("  Quantity"));
        jPanela.add(new JLabel("  Description"));
        JPanel jPanelb = new JPanel();
        jPanelb.setLayout(new GridLayout(7, 1));
        JLabel ID = new JLabel(" ID TỰ ĐỘNG");
        jPanelb.add(ID);
        namedevice = new JTextField();
        jPanelb.add(namedevice);

        JComboBox<Category> categoryJComboBox = new JComboBox(this.categoryVector);
        jPanelb.add(categoryJComboBox);

        JComboBox<Status> statusJComboBox = new JComboBox<>(this.statusVector);
        jPanelb.add(statusJComboBox);
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, new Properties());
        datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        jPanelb.add(datePicker1);
        JTQuantity = new JTextField();
        jPanelb.add(JTQuantity);
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
        vTitle = new Vector();
        vTitle.add("ID");
        vTitle.add("Name device");
        vTitle.add("Category");
        vTitle.add("Status");
        vTitle.add("Description");
        vTitle.add("Import date");
        vTitle.add("Quantity");

        vData = deviceDao.getData();
        table = new JTable();
        table.setModel(new DefaultTableModel(vData, vTitle));
        table.setRowHeight(30);
        JScrollPane sp = new JScrollPane(table);
        jpanel12.add(sp);
        JPanel jPanel3 = new JPanel();
        Border border2 = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle2 = BorderFactory.createTitledBorder(border1, "Công Cụ");
        jPanel3.setBorder(borderTitle2);
        jPanel3.setBackground(Color.CYAN);
        jPanel3.setLayout(new FlowLayout());
        Icon iconthem = new ImageIcon("C:\\Users\\admin\\Downloads\\save1.png");
        JButton jbuttonthem = new JButton("Thêm ", iconthem);

        jbuttonthem.setBackground(Color.YELLOW);
        jPanel3.add(jbuttonthem);
//        Icon iconluu = new ImageIcon("C:\\Users\\admin\\Downloads\\save12.png");
//        JButton jp2 = new JButton("Lưu", iconluu);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                int row = table.getSelectedRow();
                Vector devices = deviceDao.findAll();
                //Lay id cua dong vua click
                id_update = ((Device) devices.get(row)).getId();
                //System.out.println(((Device)devices.get(row)).getCategory().getId());
                //System.out.println(((Device)devices.get(row)).getStatus().getId());
                Status status = ((Device) devices.get(row)).getStatus();
                for (int i = 0; i < statusJComboBox.getModel().getSize(); i++) {
                    if (statusJComboBox.getModel().getElementAt(i).getId() == status.getId())
                        statusJComboBox.setSelectedItem(statusJComboBox.getModel().getElementAt(i));
                }
                namedevice.setText(table.getModel().getValueAt(row, 1) + "");
                JTQuantity.setText(table.getModel().getValueAt(row, 6) + " ");
                mota.setText(table.getModel().getValueAt(row, 4) + "");
                datePicker1.getJFormattedTextField().setText(table.getModel().getValueAt(row, 5) + "");
                // Lay category theo id_category cua dong vua click
                Category category = ((Device) devices.get(row)).getCategory();
                // Duyet tat ca category trong jcombobox
                for (int i = 0; i < categoryJComboBox.getModel().getSize(); i++)
                    // Kiem tra xem minh can cap nhat cai nao
                    if (categoryJComboBox.getModel().getElementAt(i).getId() == category.getId())
                        categoryJComboBox.setSelectedItem(categoryJComboBox.getModel().getElementAt(i));

                //  System.out.println(row);


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


        jbuttonthem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Device device = new Device();
                    device.setName(namedevice.getText());
                    Status status1 = (Status) statusJComboBox.getSelectedItem();
                    device.setStatus(status1);
                    Category category2 = (Category) categoryJComboBox.getSelectedItem();
                    device.setCategory(category2);
                    device.setQuantity(Integer.parseInt(JTQuantity.getText()));
                    device.setImportDate(datePicker1.getJFormattedTextField().getText());
                    device.setDescription(mota.getText());
                    Notification<Device> deviceNotification = deviceDao.create(device);
                    thongBao(deviceNotification);
                    System.out.println(deviceNotification);
                    nodata();
                } catch (Exception ee) {

                }
            }
        });


        Icon iconsua = new ImageIcon("C:\\Users\\admin\\Downloads\\sua.png");
        JButton sua = new JButton("Sửa", iconsua);
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Device device = new Device();
                device.setId(id_update);
                device.setName(namedevice.getText());
                device.setDescription(device.getDescription());
                Category category2 = (Category) categoryJComboBox.getSelectedItem();
                device.setCategory(category2);
                Status status1 = (Status) statusJComboBox.getSelectedItem();
                device.setStatus(status1);
                device.setQuantity(Integer.parseInt(JTQuantity.getText()));
                device.setImportDate(datePicker1.getJFormattedTextField().getText());
                device.setDescription(mota.getText());
                Notification<Device> deviceNotification = deviceDao.update(device.getId(), device);
                thongBao(deviceNotification);
                System.out.println(deviceNotification);
                nodata();

                ;
            }
        });
        sua.setBackground(Color.YELLOW);
        jPanel3.add(sua);
        Icon iconxoa = new ImageIcon("C:\\Users\\admin\\Downloads\\delete.png");
        JButton xoa = new JButton("Xóa", iconxoa);
        xoa.setBackground(Color.YELLOW);
        jPanel3.add(xoa);
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Device device = new Device();
                device.setId(id_update);
                Notification<Device> deviceNotification = deviceDao.delete(device.getId());
                thongBao(deviceNotification);
                System.out.println(deviceNotification);
                nodata();

            }
        });
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

    public void showData() {
        vData = deviceDao.getData();
        table.setModel(new DefaultTableModel(vData, vTitle));
    }

    public void nodata() {
        namedevice.setText(null);
        datePicker1.getJFormattedTextField().setText(null);
        mota.setText(null);
        JTQuantity.setText(null);
    }


    private void thongBao(Notification<Device> notification) {
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

        new DeviceManger();
    }

}
