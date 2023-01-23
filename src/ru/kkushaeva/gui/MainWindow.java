package ru.kkushaeva.gui;

import ru.kkushaeva.Converter;
import ru.kkushaeva.graphics.*;
import ru.kkushaeva.graphics.Painter;
import ru.kkushaeva.math.Function;
import ru.kkushaeva.math.FunctionExp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private Dimension minSize = new Dimension(600,450); //размер окна
    private GraphicsPanel mainPanel;
    private JPanel controlPanel;
    private GroupLayout gl; //раскладка
    private GroupLayout glcp;
    private JLabel xmin, ymin, xmax, ymax, color1l, color2l, color3l;
    private JSpinner xmins, ymins, xmaxs, ymaxs;
    private SpinnerNumberModel nmxmins, nmxmaxs, nmymins, nmymaxs;
    private JPanel color1, color2, color3;
    private JCheckBox ch1, ch2, ch3;

    public MainWindow(){
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //чтобы программа переставала работать при нажатии на крестик

        gl = new GroupLayout(getContentPane());
        setLayout(gl); //настраиваем менеджер раскладок

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);

        xmin = new JLabel("xmin");
        ymin = new JLabel("ymin");
        xmax = new JLabel("xmax");
        ymax = new JLabel("ymax");

        color1l = new JLabel("Явное задание");
        color2l = new JLabel("Цвет полинома");
        color3l = new JLabel("Цвет производной");

        color1 = new JPanel();
        color2 = new JPanel();
        color3 = new JPanel();
        color1.setBackground(Color.BLACK);
        color2.setBackground(Color.GREEN);
        color3.setBackground(Color.ORANGE);

        nmxmins = new SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1);
        nmxmaxs = new SpinnerNumberModel(5.0, -4.9, 100.0, 0.1);
        nmymins = new SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1);
        nmymaxs = new SpinnerNumberModel(5.0, -4.9, 100.0, 0.1);

        xmins = new JSpinner(nmxmins);
        ymins = new JSpinner(nmymins);
        xmaxs = new JSpinner(nmxmaxs);
        ymaxs = new JSpinner(nmymaxs);

        ch1 = new JCheckBox("", true);
        ch2 = new JCheckBox("", true);
        ch3 = new JCheckBox("", true);

        var cnv = new Converter((double)xmins.getValue(), (double)xmaxs.getValue(),
                (double)ymins.getValue(), (double)ymaxs.getValue(),
                0, 0);

        //события для спиннеров
        xmins.addChangeListener(e -> {
            nmxmaxs.setMinimum((Double)nmxmins.getValue() + 2 * (Double)nmxmaxs.getStepSize());
            cnv.setXEdges((Double)nmxmins.getValue(), (Double)nmxmaxs.getValue());
            mainPanel.repaint();
        });
        xmaxs.addChangeListener(e -> {
            nmxmins.setMaximum((Double)nmxmaxs.getValue() - 2 * (Double)nmxmins.getStepSize());
            cnv.setXEdges((Double)nmxmins.getValue(), (Double)nmxmaxs.getValue());
            mainPanel.repaint();
        });
        ymins.addChangeListener(e -> {
            nmymaxs.setMinimum((Double)nmymins.getValue() + 2 * (Double)nmymaxs.getStepSize());
            cnv.setYEdges((Double)nmymins.getValue(), (Double)nmymaxs.getValue());
            mainPanel.repaint();
        });
        ymaxs.addChangeListener(e -> {
            nmymins.setMaximum((Double)nmymaxs.getValue() - 2 * (Double)nmymins.getStepSize());
            cnv.setYEdges((Double)nmymins.getValue(), (Double)nmymaxs.getValue());
            mainPanel.repaint();
        });

        var crtp = new CrtPainter(cnv);
        var pts = new ArrayList<Painter>();
        pts.add(crtp);

        mainPanel = new GraphicsPanel(pts);
        mainPanel.setBackground(Color.WHITE);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                cnv.setWidth(mainPanel.getWidth());
                cnv.setHeight(mainPanel.getHeight());
                mainPanel.repaint();
            }
        });

        //функция, заданная явно
        Function f = new FunctionExp();
        var fpnts = new FunctionPainterExp(cnv, f, color1.getBackground(), ch1.isSelected());
        mainPanel.addPainter(fpnts);

        //изменения цветов
        color1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var newColor =
                        JColorChooser.showDialog(
                                MainWindow.this,
                                "Выбор цвета графика функции, заданной явно",
                                color1.getBackground()
                        );
                if (newColor != null){
                    color1.setBackground(newColor);
                    fpnts.setColor(newColor);
                    mainPanel.repaint();
                }
            }
        });

        color2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var newColor =
                        JColorChooser.showDialog(
                                MainWindow.this,
                                "Выбор цвета графика полинома",
                                color2.getBackground()
                        );
                if (newColor != null){
                    color2.setBackground(newColor);
                    fpnts.setColor(newColor);
                    mainPanel.repaint();
                }
            }
        });

        //события для чекбоксов
        ch1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ch1.isSelected()) mainPanel.addPainter(fpnts);
                else mainPanel.removePainter(fpnts);
            }
        });
        ch2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ch2.isSelected()) {
                    mainPanel.addPainter(fpnts);
                    if (ch1.isSelected()) {
                        mainPanel.addPainterToTheEnd(fpnts);
                        mainPanel.removePainter(fpnts);
                    }
                }
                else mainPanel.removePainter(fpnts);
            }
        });

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(controlPanel, 83, 83, 83)
                .addGap(8)
        );

        glcp = new GroupLayout(controlPanel);
        controlPanel.setLayout(glcp);

        glcp.setHorizontalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(xmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(xmins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ymin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(ymins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                )
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(xmax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(xmaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ymax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(ymaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                )
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ch1)
                                .addGap(8)
                                .addComponent(color1, 45, 45, 45)
                                .addGap(8)
                                .addComponent(color1l, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ch2)
                                .addGap(8)
                                .addComponent(color2, 45, 45, 45)
                                .addGap(8)
                                .addComponent(color2l, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ch3)
                                .addGap(8)
                                .addComponent(color3, 45, 45, 45)
                                .addGap(8)
                                .addComponent(color3l, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                )
                .addGap(8)
        );

        glcp.setVerticalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(xmin, GroupLayout.Alignment.CENTER)
                                        .addComponent(xmins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                                .addGap(5)
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ymin, GroupLayout.Alignment.CENTER)
                                        .addComponent(ymins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        )
                        .addGroup(glcp.createSequentialGroup()
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(xmax, GroupLayout.Alignment.CENTER)
                                        .addComponent(xmaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                                .addGap(5)
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ymax, GroupLayout.Alignment.CENTER)
                                        .addComponent(ymaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        )
                        .addGroup(glcp.createSequentialGroup()
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ch1)
                                        .addComponent(color1, 20, 20, 20)
                                        .addComponent(color1l, GroupLayout.Alignment.CENTER))
                                .addGap(5)
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ch2)
                                        .addComponent(color2, 20, 20, 20)
                                        .addComponent(color2l, GroupLayout.Alignment.CENTER))
                                .addGap(5)
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ch3)
                                        .addComponent(color3, 20, 20, 20)
                                        .addComponent(color3l, GroupLayout.Alignment.CENTER))
                        )
                )
                .addGap(8)
        );


    }
}