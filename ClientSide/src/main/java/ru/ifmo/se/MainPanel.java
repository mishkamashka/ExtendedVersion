package ru.ifmo.se;

import ru.ifmo.se.enums.State;
import ru.ifmo.se.person.Person;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.text.NumberFormat;
import java.util.*;

public class MainPanel extends JFrame {//TODO: текстовая панель, куда выовдится результат интеракшена, кнопки, вызывающие интеракшенпрезентер
    private ResourceBundle bundle;
    private JMenu menu;
    private JMenuBar jMenuBar;
    private JMenuItem jMenuItem;
    private JLabel label;
    private JLabel resLabel;
    private JLabel ageLabel;
    private JLabel distLabel;
    private JTextField textField;
    private JFormattedTextField formattedTextField;
    private JTree jTree;
    private JButton addButton;
    private JButton remButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton ruButton;
    private JButton engButton;
    private JButton uaButton;
    private JButton fiButton;
    private JCheckBox checkBoxN;
    private JCheckBox checkBoxA;
    private JCheckBox checkBoxI;
    private JCheckBox checkBoxB;
    private JSlider slider;
    private JPanel jPanel;
    private Container container;
    private DefaultTreeModel model;
    private DefaultMutableTreeNode root;
    private GroupLayout groupLayout;
    private ClientApp app;
    private GraphPanel graphPanel;
    private Thread thread;
    private Set<State> states = new HashSet<>();
    private volatile Set<Person> persons = new HashSet<>();
    private int age;
    private long distance;
    volatile static boolean isAuthorized = false;

    public MainPanel() {
        app = new ClientApp();
        bundle = ResourceBundle.getBundle("ru.ifmo.se.resources.GuiLabels", Locale.getDefault());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(bundle.getString("title"));
        setResizable(false);
        createMenu();
        container = getContentPane();
        jPanel = new JPanel();
        groupLayout = new GroupLayout(jPanel);
        container.add(jPanel);
        root = new DefaultMutableTreeNode("People");
        jTree = new JTree(root);
        jTree.addTreeSelectionListener(e -> {
            String[] node = jTree.getLastSelectedPathComponent().toString().split(" ");
            Person selectedPerson;
            if (node.length > 1)
                selectedPerson = new Person(node[0], node[1]);
            else
                selectedPerson = new Person(node[0]);
            for (Person person: app.collec) {
                if (person.equals(selectedPerson)) {
                    resLabel.setText(person.toString() + " " + person.getTime().toString());
                    break;
                }
            }
        });
        app.connect();
        while (!isAuthorized) {
        }
        ClientApp.toServer.println("data_request");
        app.clear();
        app.load();
        updateTree();

        model = (DefaultTreeModel) jTree.getModel();
        createOptions();
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addComponent(jTree).addGap(100)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(label).addGap(10)
                            .addComponent(textField).addGap(10)
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(addButton).addGap(10)
                                .addComponent(remButton)).addGap(10)
                            .addComponent(resLabel))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(startButton).addGap(10)
                            .addComponent(stopButton).addGap(10)
                            .addComponent(checkBoxN).addGap(5)
                            .addComponent(checkBoxA).addGap(5)
                            .addComponent(checkBoxB).addGap(5)
                            .addComponent(checkBoxI).addGap(10)
                            .addComponent(ageLabel).addGap(10)
                            .addComponent(slider).addGap(10)
                            .addComponent(distLabel).addGap(10)
                            .addComponent(formattedTextField,20,20,20).addGap(30)
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(engButton).addGap(5)
                                .addComponent(ruButton).addGap(5)
                                .addComponent(fiButton).addGap(5)
                                .addComponent(uaButton))).addGap(10)
                        .addComponent(graphPanel, 300,400,500)
                        );
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(jTree).addGap(100)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(label).addGap(10)
                            .addComponent(textField).addGap(10)
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(addButton).addGap(10)
                                .addComponent(remButton)).addGap(10)
                                .addComponent(resLabel)).addGap(50)
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(startButton).addGap(10)
                                .addComponent(stopButton).addGap(10)
                                .addComponent(checkBoxN).addGap(10)
                                .addComponent(checkBoxA).addGap(10)
                                .addComponent(checkBoxB).addGap(10)
                                .addComponent(checkBoxI).addGap(10)
                                .addComponent(ageLabel).addGap(10)
                                .addComponent(slider).addGap(10)
                                .addComponent(distLabel).addGap(10)
                                .addComponent(formattedTextField, 100,150,200).addGap(50)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(engButton).addGap(10)
                                    .addComponent(ruButton).addGap(10)
                                    .addComponent(fiButton).addGap(10)
                                    .addComponent(uaButton))).addGap(10)
                            .addComponent(graphPanel, 300, 400, 500));
        model.reload();
        groupLayout.linkSize(textField);
        jPanel.setLayout(groupLayout);
        pack();
        setVisible(true);
    }

    public void updateTree(){
        root.removeAllChildren();
        app.collec.forEach(person -> root.add(new DefaultMutableTreeNode(person.toString())));
        jTree.updateUI();
        jPanel.updateUI();
    }

    public void createMenu(){
        jMenuBar = new JMenuBar();
        menu = new JMenu(bundle.getString("menu"));
        jMenuItem = new JMenuItem(bundle.getString("load"));
        jMenuItem.addActionListener(a -> {
            ClientApp.toServer.println("data_request");
            app.clear();
            app.load();
            updateTree();
        });
        menu.add(jMenuItem);
        jMenuItem = new JMenuItem(bundle.getString("save"));
        jMenuItem.addActionListener(a -> {
            ClientApp.toServer.println("save");
            app.giveCollection();
            resLabel.setText(app.gettingResponse());
        });
        menu.add(jMenuItem);
        jMenuItem = new JMenuItem(bundle.getString("clear"));
        jMenuItem.addActionListener(a -> {
            app.clear();
            updateTree();
        });
        menu.add(jMenuItem);
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
    }

    public void createOptions(){
        label = new JLabel(bundle.getString("label"));
        resLabel = new JLabel();
        textField = new JTextField("{\"name\":\"Andy\"}",15);
        addButton = new JButton(bundle.getString("addButton"));
        addButton.addActionListener(e -> {
            String string = textField.getText();
            resLabel.setText(app.addObject(string));
            updateTree();
        });
        remButton = new JButton(bundle.getString("remButton"));
        remButton.addActionListener(e -> {
            String string = textField.getText();
            resLabel.setText(app.removeGreater(string));
            updateTree();
        });
        startButton = new JButton(bundle.getString("startButton"));
        startButton.addActionListener(e -> {
            thread = new Thread(() -> {
                if (formattedTextField.getValue() != null)
                    distance = (Long) formattedTextField.getValue();
                else distance = 20000;
                int i = 0;
                app.collec.forEach(person -> {
                    for (State state: states){
                        if (person.getState().equals(state) && (person.getAge() >= age) && (person.getSteps_from_door() <= distance))
                            persons.add(person);
                    }
                });
                int color = maxColor();
                while (i < persons.size()*3) {
                    i = makeBrighter();
                    try {
                        Thread.sleep(5000/color);
                    } catch (InterruptedException ee) {
                        return;
                    }
                }
                i = 0;
                while (i < persons.size()*3) {
                    i = makeDarker();
                    try {
                        Thread.sleep(5000/color);
                    } catch (InterruptedException ee) {
                        return;
                    }
                }
            });
            thread.start();
            persons.clear();
        });
        stopButton = new JButton(bundle.getString("stopButton"));
        stopButton.addActionListener(e -> thread.interrupt());
        checkBoxN = new JCheckBox(bundle.getString("checkBoxN"));
        checkBoxN.addChangeListener(e -> {
            if (checkBoxN.isSelected())
                states.add(State.NEUTRAL);
            else
                states.remove(State.NEUTRAL);
        });
        checkBoxA = new JCheckBox(bundle.getString("checkBoxA"));
        checkBoxA.addChangeListener(e -> {
            if (checkBoxA.isSelected())
                states.add(State.ANGRY);
            else
                states.remove(State.ANGRY);
        });
        checkBoxI = new JCheckBox(bundle.getString("checkBoxI"));
        checkBoxI.addChangeListener(e -> {
            if (checkBoxI.isSelected())
                states.add(State.INTERESTED);
            else
                states.remove(State.INTERESTED);
        });
        checkBoxB = new JCheckBox(bundle.getString("checkBoxB"));
        checkBoxB.addChangeListener(e -> {
            if (checkBoxB.isSelected())
                states.add(State.BORED);
            else
                states.remove(State.BORED);
        });
        ageLabel = new JLabel(bundle.getString("ageLabel"));
        slider = new JSlider(0,120,25);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(25);
        slider.addChangeListener(e -> age = slider.getValue());
        distLabel = new JLabel(bundle.getString("distLabel"));
        formattedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        graphPanel = new GraphPanel(app);
        graphPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int personX;
                int personY;
                for (Map.Entry<Person, Ellipse2D> entry: graphPanel.ellipsMap.entrySet()){
                    personX = entry.getKey().getX();
                    personY = entry.getKey().getY();
                    if ((personX <= x && x <= (personX + 40))&&(personY <= y && y <=(personY + 20)))
                        graphPanel.setToolTipText(entry.getKey().toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        ruButton = new JButton("ru");
        ruButton.addActionListener(e -> changeLocale(ResourceBundle.getBundle("ru.ifmo.se.resources.GuiLabels", new Locale("ru_RU"))));
        engButton = new JButton("eng");
        engButton.addActionListener(e -> changeLocale(ResourceBundle.getBundle("ru.ifmo.se.resources.GuiLabels", new Locale("en"))));
        fiButton = new JButton("fi");
        fiButton.addActionListener(e -> changeLocale(ResourceBundle.getBundle("ru.ifmo.se.resources.GuiLabels", new Locale("fi"))));
        uaButton = new JButton("ua");
        uaButton.addActionListener(e -> changeLocale(ResourceBundle.getBundle("ru.ifmo.se.resources.GuiLabels", new Locale("uk"))));
    }

    private int makeBrighter(){
        int r;
        int g;
        int b;
        int i = 0;
        for (Person person: persons) {
            r = person.getColor().getRed();
            g = person.getColor().getGreen();
            b = person.getColor().getBlue();
            if (r < 255)
                r++;
            else i++;
            if (g < 255)
                g++;
            else i++;
            if (b < 255)
                b++;
            else i++;
            person.setColor(new Color(r,g,b));
        }
        graphPanel.repaint();
        return i;
    }

    private int makeDarker(){
        int r;
        int g;
        int b;
        int final_r;
        int final_g;
        int final_b;
        int i = 0;
        for (Person person: persons) {
            final_r = person.getState().getColor().getRed();
            final_g = person.getState().getColor().getGreen();
            final_b = person.getState().getColor().getBlue();
            r = person.getColor().getRed();
            g = person.getColor().getGreen();
            b = person.getColor().getBlue();
            if (r > final_r)
                r--;
            else i++;
            if (g > final_g)
                g--;
            else i++;
            if (b > final_b)
                b--;
            else i++;
            person.setColor(new Color(r,g,b));
        }
        graphPanel.repaint();
        return i;
    }

    private int maxColor(){
        int max = 0;
        int r;
        int g;
        int b;
        for(Person person: persons){
            r = person.getColor().getRed();
            g = person.getColor().getGreen();
            b = person.getColor().getBlue();
            if (r > max)
                max = r;
            if (g > max)
                max = g;
            if (b > max)
                max = b;
            if (max == 255)
                break;
        }
        return max;
    }

    private void changeLocale(ResourceBundle bundle){
        this.bundle = bundle;
        changeTexts();
    }

    private void changeTexts(){
        this.setTitle(bundle.getString("title"));
        label.setText(bundle.getString("label"));
        ageLabel.setText(bundle.getString("ageLabel"));
        distLabel.setText(bundle.getString("distLabel"));
        addButton.setText(bundle.getString("addButton"));
        remButton.setText(bundle.getString("remButton"));
        startButton.setText(bundle.getString("startButton"));
        stopButton.setText(bundle.getString("stopButton"));
        checkBoxN.setText(bundle.getString("checkBoxN"));
        checkBoxA.setText(bundle.getString("checkBoxA"));
        checkBoxI.setText(bundle.getString("checkBoxI"));
        checkBoxB.setText(bundle.getString("checkBoxB"));
        createMenu();
    }
}
