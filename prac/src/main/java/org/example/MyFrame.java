package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MyFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private ClockShop shop;
    private JLabel labelClock;
    private JLabel labelClock2;
    private JLabel labelClock3;
    private JButton butLeft;
    private JButton butRight;
    private int index;
    private Clocks c;
    private Gson gson;
    public MyFrame() {
        gson = new GsonBuilder().registerTypeAdapter(ClockShop.class, new ClockShopDeserializer()).create();
        shop = new ClockShop();
        //LoadFromFIleJSON();
        //LoadFromFIle();
        LoadFromDatabase();
        index = 0;
        frame = new JFrame();
        frame.setLayout(new GridLayout(3, 1));
        Panel1();
        Panel2();
        Panel3();
        frame.add(panel);
        frame.add(panel1);
        frame.add(panel2);
        frame.setSize(800, 1000);
        frame.setLocation(300, 20);
        frame.setTitle("Магазин часов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void Panel1(){
        panel = new JPanel();
        butLeft = new JButton();
        butLeft.setText("Предыдущий");
        butLeft.setFont(new Font("Arial", Font.PLAIN, 30));
        butLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (index > 0) {
                    index--;
                    c = shop.GetClock(index);
                    String min = c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes() + "";
                    String hor = c.getHours() < 10 ? "0" + c.getHours() : c.getHours() + "";
                    if (!c.isSeconds()) {
                        labelClock.setText(hor + ":" + min);
                    } else {
                        String sec = c.getSeconds() < 10 ? "0" + c.getSeconds() : c.getSeconds() + "";
                        labelClock.setText(hor + ":" + min + ":" + sec);
                    }
                    labelClock2.setText("Цена: " + c.getCost());
                    labelClock3.setText( "Марка: " + c.getBrand());
                }
            }
        });

        butRight = new JButton();
        butRight.setText("Следующий");
        butRight.setFont(new Font("Arial", Font.PLAIN, 30));
        butRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < shop.getLength() - 1) {
                    index++;
                    c = shop.GetClock(index);
                    String min = c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes() + "";
                    String hor = c.getHours() < 10 ? "0" + c.getHours() : c.getHours() + "";
                    if (!c.isSeconds()) {
                        labelClock.setText(hor + ":" + min);
                    } else {
                        String sec = c.getSeconds() < 10 ? "0" + c.getSeconds() : c.getSeconds() + "";
                        labelClock.setText(hor + ":" + min + ":" + sec);
                    }
                    labelClock2.setText("Цена: " + c.getCost());
                    labelClock3.setText( "Марка: " + c.getBrand());
                }
            }
        });


        panel.setLayout(new GridLayout(3, 3));
        DrawNewClock();
    }
    private void DrawNewClock(){
        labelClock = new JLabel();
        c = shop.GetClock(index);
        labelClock.setFont(new Font("Arial", Font.PLAIN, 40));
        String min = c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes() + "";
        String hor = c.getHours() < 10 ? "0" + c.getHours() : c.getHours() + "";
        if (!c.isSeconds()) {
            labelClock.setText(hor + ":" + min);
        } else {
            String sec = c.getSeconds() < 10 ? "0" + c.getSeconds() : c.getSeconds() + "";
            labelClock.setText(hor + ":" + min + ":" + sec);
        }
        labelClock.setHorizontalAlignment(SwingConstants.CENTER);
        labelClock2 = new JLabel();
        labelClock2.setFont(new Font("Arial", Font.PLAIN, 20));
        labelClock2.setText("Цена: " + c.getCost());
        labelClock2.setHorizontalAlignment(SwingConstants.CENTER);
        labelClock3 = new JLabel();
        labelClock3.setFont(new Font("Arial", Font.PLAIN, 20));
        labelClock3.setText("Марка: " + c.getBrand());
        labelClock3.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(new JLabel());
        panel.add(labelClock);
        panel.add(new JLabel());
        panel.add(butLeft);
        panel.add(labelClock2);
        panel.add(butRight);
        panel.add(new JLabel());
        panel.add(labelClock3);
        frame.setVisible(true);

    }
    private void Panel2(){
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4, 1, 30, 10));
        JButton b1 = new JButton("Добавить часы в магазин");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame();
                f.setSize(300, 200);
                f.setLocation(300, 20);
                f.setLayout(new GridLayout(6, 2));
                List<JLabel> list1 = new ArrayList<>();
                list1.add(new JLabel("Часы"));
                list1.add(new JLabel("Минуты"));
                list1.add(new JLabel("Секунды(если нужны)"));
                list1.add(new JLabel("Цена"));
                list1.add(new JLabel("Марка"));
                List<JTextField> list2 = new ArrayList<>();
                for (int i = 0; i < 5; i++){
                    list2.add(new JTextField());
                    f.add(list1.get(i));
                    f.add(list2.get(i));
                }
                JButton b1 = new JButton("Готово");
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Clocks tmp;
                        try{
                            if(list2.get(2).getText().equals("")){
                                tmp = new Clocks(Integer.valueOf(list2.get(0).getText()), Integer.valueOf(list2.get(1).getText()),
                                        Integer.valueOf(list2.get(3).getText()), list2.get(4).getText());

                            }
                            else{
                                tmp = new ClocksSec(Integer.valueOf(list2.get(0).getText()), Integer.valueOf(list2.get(1).getText()),
                                Integer.valueOf(list2.get(2).getText()), Integer.valueOf(list2.get(3).getText()), list2.get(4).getText());
                            }
                            shop.AddClock(tmp);
                            SaveInDatabase();
                        }
                        catch (ExceptionIncorrectInput ex){
                            JOptionPane.showMessageDialog(frame, "Неправильный ввод!" + ex.toString());
                        }
                        SaveInFileJSON();
                        SaveInFile();
                        f.setVisible(false);
                    }
                });
                f.add(b1);
                f.setVisible(true);
            }
        });
        JButton b2 = new JButton("Удалить выбранные часы из магазина");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shop.getLength() != 1) {
                    Clocks tmp = shop.GetClock(index);
                    shop.RemoveClock(index);
                    c = shop.GetClock(index);
                    if (!c.isSeconds()) {
                        labelClock.setText((c.getHours() < 10 ? "0" + c.getHours() : c.getHours()) + ":" + (c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes()));
                    } else {
                        labelClock.setText((c.getHours() < 10 ? "0" + c.getHours() : c.getHours()) + ":" + (c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes()) + ":" + (c.getSeconds() < 10 ? "0" + c.getSeconds() : c.getSeconds()));
                    }
                    labelClock2.setText("Цена: " + c.getCost());
                    labelClock3.setText("Марка: " + c.getBrand());
                    DeleteFromDatabase(tmp);
                    SaveInFileJSON();
                    SaveInFile();
                }
            }
        });
        JButton b3 = new JButton("Перевести выбранные часы на(вводить через пробел):");
        JTextField t1 = new JTextField();
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] s = t1.getText().split(" ");
                    c.TimeAdd(Hand.HOURS, Integer.valueOf(s[0]));
                    c.TimeAdd(Hand.MINUTES, Integer.valueOf(s[1]));
                    c.TimeAdd(Hand.SECONDS, Integer.valueOf(s[2]));
                }
                catch (ExceptionIncorrectInput ex){
                    JOptionPane.showMessageDialog(frame, "Неправильный ввод!" + ex.toString());
                }
                catch (ArrayIndexOutOfBoundsException ex){}
                catch (NumberFormatException ex){}

                if (!c.isSeconds()) {
                    labelClock.setText((c.getHours() < 10 ? "0" + c.getHours() : c.getHours()) + ":" + (c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes()));
                } else {
                    labelClock.setText((c.getHours() < 10 ? "0" + c.getHours() : c.getHours()) + ":" + (c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes()) + ":" + (c.getSeconds() < 10 ? "0" + c.getSeconds() : c.getSeconds()));
                }
                SaveInFileJSON();
                SaveInFile();
                SaveInDatabase();
                t1.setText("");
            }
        });

        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b3);
        panel1.add(t1);
    }
    private void Panel3(){
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 2, 30, 10));
        JLabel lblExp = new JLabel();
        JTextField lblTime = new JTextField();
        JLabel lblBrand = new JLabel();
        JLabel lblAllBrands = new JLabel();
        JButton btnExp = new JButton("Описание самых дорогих часов");
        btnExp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblExp.setText(shop.DescExpClock());
            }
        });

        JButton btnTime = new JButton("Устанавить заданное время(писать время через пробел)");

        btnTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] s = lblTime.getText().split(" ");
                    shop.SetTime(Hand.HOURS, Integer.valueOf(s[0]));
                    shop.SetTime(Hand.MINUTES, Integer.valueOf(s[1]));
                    shop.SetTime(Hand.SECONDS, Integer.valueOf(s[2]));
                }
                catch (ExceptionIncorrectInput ex){
                    JOptionPane.showMessageDialog(frame, "Неправильный ввод!" + ex.toString());
                }
                catch (ArrayIndexOutOfBoundsException ex){}
                catch (NumberFormatException ex){}

                if (!c.isSeconds()) {
                    labelClock.setText((c.getHours() < 10 ? "0" + c.getHours() : c.getHours()) + ":" + (c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes()));
                } else {
                    labelClock.setText((c.getHours() < 10 ? "0" + c.getHours() : c.getHours()) + ":" + (c.getMinutes() < 10 ? "0" + c.getMinutes() : c.getMinutes()) + ":" + (c.getSeconds() < 10 ? "0" + c.getSeconds() : c.getSeconds()));
                }
                SaveInFileJSON();
                SaveInFile();
                SaveInDatabase();
                lblTime.setText("");
            }
        });

        JButton btnBrand = new JButton("Получить название наиболее частой марки");
        btnBrand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblBrand.setText(shop.PopularBrand());
            }
        });
        JButton btnAllBrands = new JButton("Вывести марки часов");
        btnAllBrands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblAllBrands.setText(shop.PrintBrands());
            }
        });

        panel2.add(btnExp);
        panel2.add(lblExp);
        panel2.add(btnTime);
        panel2.add(lblTime);
        panel2.add(btnBrand);
        panel2.add(lblBrand);
        panel2.add(btnAllBrands);
        panel2.add(lblAllBrands);
    }
    private void SaveInFileJSON() {
        String col = gson.toJson(shop);
        try {
            FileOutputStream fos = new FileOutputStream("SaveJSON.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(col);

        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            System.out.println("Ошибка записи в файл JSON");
        }
    }
    private void LoadFromFIleJSON(){
        try {
            FileInputStream fis = new FileInputStream("SaveJSON.txt");
            DataInputStream dis = new DataInputStream(fis);

            String res = dis.readUTF();
            shop = gson.fromJson(res, ClockShop.class);
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения из файла JSON");
        }
    }

    private void SaveInFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SaveDefault.bin"));
            oos.writeObject(shop);
        }
        catch (IOException e){
            System.out.println("Ошибка записи в файл");
        }

    }
    private void LoadFromFIle() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SaveDefault.bin"));
            shop = (ClockShop)ois.readObject();
        }
        catch (IOException e){
            System.out.println("Ошибка чтения из файла");
        }
        catch (ClassNotFoundException e){
            System.out.println("Класс не найден");
        }
    }
    private void SaveInDatabase(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        ListIterator<Clocks> iter = shop.iterator();
        while(iter.hasNext()){
            Transaction tx1 = session.beginTransaction();
            session.remove(iter.next());
            tx1.commit();
        }
        iter = shop.iterator();
        while(iter.hasNext()){
            Transaction tx1 = session.beginTransaction();
            session.save(iter.next());
            tx1.commit();
        }
        session.close();
    }
    private void DeleteFromDatabase(Clocks c){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(c);
        tx1.commit();
        session.close();
    }
    private void LoadFromDatabase(){
        shop.SetShop((ArrayList<Clocks>)HibernateSessionFactoryUtil.
                getSessionFactory().openSession().
                createQuery("From org.example.ClockBase").list());

    }

}

