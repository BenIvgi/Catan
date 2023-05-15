package Catan.GUI;
import Catan.Logic.Game;
import Catan.Logic.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;
public class GameFrame extends Frame {
    // Properties.
    private ButtonState state;
    private JButton save;
    private JButton recipes;
    private JButton buildset;

    private JButton buildroad;
    private JButton exitgame;
    private JButton roll;

    private Game game;
    // Constructor

    public GameFrame() throws IOException {
        super();
        // Set title
        f.setTitle("Settlers Of Catan - New Game");

        // Load Image.
        BufferedImage background_image = ImageIO.read(
                new File("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\GameScreen\\framebackground.png"));

        // Rescale Image.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Image scaledImage = background_image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
        // Set BackGround.
        ImagePanel background = new ImagePanel(scaledImage);
        this.f.setLayout(new BorderLayout());
        this.f.getContentPane().setPreferredSize(screenSize);
        this.f.getContentPane().setMaximumSize(screenSize);
        this.f.getContentPane().setMinimumSize(screenSize);
        this.f.setContentPane(background);

        // Load button and cube image.
        Icon button_image = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\GameScreen\\buttontexture.png");
        Icon CubeImage = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\GameScreen\\cubetexture.png");

        // Save Button
        this.save = new JButton("Save", button_image);
        this.save.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.save.setBounds(30, 30, 222, 88);
        this.save.setHorizontalTextPosition(JButton.CENTER);
        this.save.setVerticalTextPosition(JButton.CENTER);
        this.save.setForeground(Color.WHITE);
        this.save.setIcon(button_image);
        this.f.add(this.save);

        // Roll Button
        this.roll = new JButton();
        this.roll.setBounds(30, 600, 100, 100);
        this.roll.setHorizontalTextPosition(JButton.CENTER);
        this.roll.setVerticalTextPosition(JButton.CENTER);
        this.roll.setForeground(Color.WHITE);
        this.roll.setIcon(CubeImage);
        this.f.add(this.roll);

        // Open Recipes Button
        this.recipes = new JButton("Show Recipes", button_image);
        this.recipes.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.recipes.setBounds(30, 30 + 88 * 1 + 15 * 1, 222, 88);
        this.recipes.setHorizontalTextPosition(JButton.CENTER);
        this.recipes.setVerticalTextPosition(JButton.CENTER);
        this.recipes.setForeground(Color.WHITE);
        this.f.add(this.recipes);

        // Build Settlement Button.
        this.buildset = new JButton("Settlement", button_image);
        this.buildset.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.buildset.setBounds(30, 30 + 88 * 2 + 15 * 2, 222, 88);
        this.buildset.setHorizontalTextPosition(JButton.CENTER);
        this.buildset.setVerticalTextPosition(JButton.CENTER);
        this.buildset.setForeground(Color.WHITE);
        this.f.add(this.buildset);

        // Build Road Button.
        this.buildroad = new JButton("Build Road", button_image);
        this.buildroad.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.buildroad.setBounds(30, 30 + 88 * 3 + 15 * 3, 222, 88);
        this.buildroad.setHorizontalTextPosition(JButton.CENTER);
        this.buildroad.setVerticalTextPosition(JButton.CENTER);
        this.buildroad.setForeground(Color.WHITE);
        this.f.add(this.buildroad);

        // Exit Game Button.
        this.exitgame = new JButton("Exit", button_image);
        this.exitgame.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.exitgame.setBounds(30, 30 + 88 * 4 + 15 * 4, 222, 88);
        this.exitgame.setHorizontalTextPosition(JButton.CENTER);
        this.exitgame.setVerticalTextPosition(JButton.CENTER);
        this.exitgame.setForeground(Color.WHITE);
        this.exitgame.setIcon(button_image);
        this.f.add(this.exitgame);

        // Init Game
        this.game = new Game(2, 54, 72, 19);
        this.game.Start();
        initTiles(this.game.getBoard(), screenSize);

        // Add Button Listeners to call Handle Function.
        this.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(ButtonState.SAVE);
                HandleAction();
            }
        });

        this.recipes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(ButtonState.SHOW_RECIPES);
                HandleAction();
            }
        });

        this.buildset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(state != ButtonState.ROLL && game.getClock() > 4){
                    showMessageDialog(f, "First you must roll the dice!");
                }
                else{
                        setState(ButtonState.BUILD_SETTLEMENT);
                        HandleAction();
                }
            }
        });

        this.buildroad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(state != ButtonState.ROLL){
                    showMessageDialog(f, "First you must roll the dice!");
                }
                else{
                        setState(ButtonState.BUILD_ROAD);
                        HandleAction();
                }
            }
        });

        this.roll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(state == ButtonState.ROLL){
                    ChangePlayer();
                }
                setState(ButtonState.ROLL);
                HandleAction();
            }
        });



    }

    // Init tiles Graphics.
    private void initTiles(Catan.Logic.Board b, Dimension screenSize) {

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set Font and size.
                g.setFont(new Font("Georgia", Font.PLAIN, 40));
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(7));
                // Add tiles.
                for (int i = 0; i < 19; i++) {
                    g2.setColor(Color.BLACK);
                    g2.drawPolygon(b.getTile(i).getShape());
                    g2.setColor(b.getTile(i).getColor());
                    g2.fillPolygon(b.getTile(i).getShape());
                    g2.setColor(Color.BLACK);
                    g2.drawString(Integer.toString(b.getTile(i).getNumber()), (int) b.getTile(i).getPos().getX() - 10, (int) b.getTile(i).getPos().getY());
                }
            }
        };
        // Add Tiles.
        p.setOpaque(false);
        p.setBounds(0, 0, screenSize.width, screenSize.height);
        p.setVisible(true);
        this.f.add(p);

    }

    // Start Window.
    public ButtonState start() {
        this.state = null;
        this.f.setVisible(true);

        // Get 2 Players details.
        String[] pNames = new String[2];
        Color[] pColors = new Color[2];
        String message = "Enter Player 1 Name";
        pNames[0] = JOptionPane.showInputDialog(this.f, message);
        message = "Player 1 Color";
        pColors[0] = JColorChooser.showDialog(this.f, message, Color.WHITE);
        message = "Enter Player 2 Name";
        pNames[1] = JOptionPane.showInputDialog(this.f, message);
        message = "Player 2 Color";
        pColors[1] = JColorChooser.showDialog(this.f, message, Color.WHITE);

        this.game.InitPlayers(pNames, pColors);

        // Allow Players to Place settlements.
        JPanel pset = showSettlements();
        showMessageDialog(f, game.getCurrentPlayer().getName() + " ,Choose First Settlement!", "Catan", 1);

        MouseListener m = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(game.getClock()<4){
                        Point pos = new Point(e.getX() - 8, e.getY() - 30);
                        if(game.getCurrentPlayer().canBuildSettlement()){
                            for(int i = 0; i<54; i++) {
                                if (game.getBoard().getVertex(i).getShape().contains(pos)&&
                                        !game.getBoard().getVertex(i).getIsOccupied() &&
                                game.canPlaceSettlement(i)) {
                                    game.PlaceSettlement(i);
                                    placeSettlement(i);
                                    break;
                                }
                            }
                        }
                        else {
                            showMessageDialog(f, "You can't place a settlement!", "Catan", 0);
                        }
                        ChangePlayer();
                        if(game.getClock()<2) {
                            showMessageDialog(f, game.getCurrentPlayer().getName() + " ,Choose First Settlement!", "Catan", 1);
                        }
                        else
                        if(game.getClock()<4) {
                            showMessageDialog(f, game.getCurrentPlayer().getName() + " ,Choose Second Settlement!", "Catan", 1);
                        }
                        if(game.getClock()==4){
                            f.remove(pset);
                            f.revalidate();
                            f.repaint();
                        }
                    }
                    else
                        if(game.getClock()==4) {
                            f.removeMouseListener(this);
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
            };
            this.f.addMouseListener(m);

        // Show Inventory of Each Player.
        JPanel p = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("Georgia", Font.PLAIN, 40));
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(7));
                g2.setColor(Color.BLACK);
                int length = (game.getCurrentPlayer().getName().length() + 2 + 12) * 18;
                g2.drawRect(1000, 200, length, 40);
                g2.setColor(game.getCurrentPlayer().getColor());
                g2.fillRect(1000, 200, length, 40);
                g2.setColor(Color.BLACK);
                g2.drawString(game.getCurrentPlayer().getName() + "( " + game.getCurrentPlayer().getPtcount() + " Points)", 1010, 235);
                Resources[] resArr = {Resources.WOOD, Resources.BRICKS, Resources.WHEAT, Resources.WOOL, Resources.IRON};
                g2.setFont(new Font("Georgia", Font.PLAIN, 14));
                for(int i = 0; i < 5; i++){
                    g2.drawRect(1000 + 100*i, 250 , 100, 40);
                    g2.setColor(Color.WHITE);
                    g2.fillRect(1000 + 100*i, 250 , 100, 40);
                    g2.setColor(Color.BLACK);
                    g2.drawString(resArr[i].name() +": "+ game.getCurrentPlayer().getAmnt(resArr[i]), 1010 + 100*i, 285);
                }
            }
        };

        p.setOpaque(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        p.setBounds(0, 0, width, height);
        p.setVisible(true);
        this.f.add(p);
        this.f.revalidate();
        this.f.repaint();

        // Window Loop.
        while (this.state != ButtonState.EXIT) {
            this.exitgame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    setState(ButtonState.EXIT);
                }
            });
        }
        return this.state;
    }

    // Change Player Stats Showing on Screen.
    public void ChangePlayer() {
        game.shiftClock();
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("Georgia", Font.PLAIN, 40));
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(7));
                g2.setColor(Color.BLACK);
                int length = (game.getCurrentPlayer().getName().length() + 2) * 18;
                g2.drawRect(1000, 200, length, 40);
                g2.setColor(game.getCurrentPlayer().getColor());
                g2.fillRect(1000, 200, length, 40);
                g2.setColor(Color.BLACK);
                g2.drawString(game.getCurrentPlayer().getName(), 1010, 235);
                Resources[] resArr = {Resources.WOOD, Resources.BRICKS, Resources.WHEAT, Resources.WOOL, Resources.IRON};
                g2.setFont(new Font("Georgia", Font.PLAIN, 14));
                for(int i = 0; i < 5; i++){
                    g2.drawRect(1000 + 100*i, 250 , 100, 40);
                    g2.setColor(Color.WHITE);
                    g2.fillRect(1000 + 100*i, 250 , 100, 40);
                    g2.setColor(Color.BLACK);
                    g2.drawString(resArr[i].name() +": "+ game.getCurrentPlayer().getAmnt(resArr[i]), 1010 + 100*i, 285);
                }
            }
        };
        p.setOpaque(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        p.setBounds(0, 0, width, height);
        p.setVisible(true);
        this.f.add(p);
        this.f.revalidate();
        this.f.repaint();
    }

    // Close Window.
    public void close() {
        this.f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.f.dispatchEvent(new WindowEvent(this.f, WindowEvent.WINDOW_CLOSING));
    }

    public void HandleAction() {
        // Write functionality for each button.
        switch (this.state) {
            case SAVE: {
                break;
            }
            case SHOW_RECIPES: {
                JOptionPane.showMessageDialog(this.f, "Road:\nWood x1\nWheat x0\nBricks x1\nIron x0\nWool x0\n\n" +
                                "Settlement:\nWood x1\nWheat x1\nBricks x1\nIron x0\nWool x1\n\n" +
                                "City:\nWood x0\nWheat x2\nBricks x0\nIron x3\nWool x0"
                        , "Catan Recipes", 3);
                break;
            }
            case BUILD_ROAD: {

                JPanel p = showRoads();
                MouseListener m = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        f.remove(p);
                        Point pos = new Point(e.getX() - 8, e.getY() - 30);
                        f.revalidate();
                        f.repaint();
                        if(game.getCurrentPlayer().canBuildRoad()){
                            for(int i = 0; i<72; i++) {
                                if (game.getBoard().getEdge(i).getShape().ptSegDist(pos)<5&&
                                        game.canPlaceRoad(i)) {
                                    game.PlaceRoad(i);
                                    placeRoad(i);
                                    break;
                                }
                            }
                        }
                        else {
                            showMessageDialog(f, "You can't place a road!", "Catan", 0);
                        }
                        ChangePlayer();
                        f.removeMouseListener(this);
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
                };
                this.f.addMouseListener(m);
                break;
            }
            case BUILD_SETTLEMENT: {
                JPanel p = showSettlements();

                MouseListener m = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        f.remove(p);
                        Point pos = new Point(e.getX() - 8, e.getY() - 30);
                        f.revalidate();
                        f.repaint();
                        if(game.getCurrentPlayer().canBuildSettlement()){
                            for(int i = 0; i<54; i++) {
                                if (game.getBoard().getVertex(i).getShape().contains(pos)&&
                                        !game.getBoard().getVertex(i).getIsOccupied() &&
                                game.canPlaceSettlement(i)) {
                                    game.PlaceSettlement(i);
                                    placeSettlement(i);
                                    if(game.getCurrentPlayer().isWin()){
                                        JOptionPane.showMessageDialog(f,
                                                game.getCurrentPlayer().getName() + " Won!", "Catan", 1);
                                        setState(ButtonState.EXIT);

                                    }
                                    break;
                                }
                            }
                        }
                        else {
                            showMessageDialog(f, "You can't place a settlement!", "Catan", 0);
                        }
                        ChangePlayer();
                        f.removeMouseListener(this);
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
                };
                this.f.addMouseListener(m);
                break;
            }
            case ROLL: {
                int num = this.game.roll();
                JOptionPane.showMessageDialog(this.f, "The number rolled by the dice was " + num + ".", "Catan Dice", 1);
                for (int i = 0; i < 19; i++) {
                    if (this.game.getBoard().getTile(i).getNumber() == num) {
                        for (int j = 0; j < 6; j++) {
                            if (this.game.getBoard().getTile(i).getSettlement(j).getOwner() != null) {
                                this.game.getBoard().getTile(i).getSettlement(j).getOwner()
                                        .incResource(this.game.getBoard().getTile(i).getType());
                            }
                        }
                    }
                }
                JPanel p = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(7));
                        Resources[] resArr = {Resources.WOOD, Resources.BRICKS, Resources.WHEAT, Resources.WOOL, Resources.IRON};
                        g2.setFont(new Font("Georgia", Font.PLAIN, 14));
                        for(int i = 0; i < 5; i++){
                            g2.drawRect(1000 + 100*i, 250 , 100, 40);
                            g2.setColor(Color.WHITE);
                            g2.fillRect(1000 + 100*i, 250 , 100, 40);
                            g2.setColor(Color.BLACK);
                            g2.drawString(resArr[i].name() +": "+ game.getCurrentPlayer().getAmnt(resArr[i]), 1010 + 100*i, 285);
                        }
                    }
                };
                p.setOpaque(false);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) screenSize.getWidth();
                int height = (int) screenSize.getHeight();
                p.setBounds(0, 0, width, height);
                p.setVisible(true);
                this.f.add(p);
                this.f.revalidate();
                this.f.repaint();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setState(ButtonState state) {
        this.state = state;
    }

    // Display free settlements on board.
    public JPanel showSettlements() {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(7));
                for (int i = 0; i < 54; i++) {
                    Rectangle2D r = game.getBoard().getVertex(i).getShape();
                    g2.setColor(new Color(0f, 0f,0f, .5f));
                    g2.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                    g2.setColor(new Color(.5f, .5f, .5f, .5f));
                    g2.fillRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                }
            }
        };
        p.setOpaque(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        p.setBounds(0, 0, width, height);
        p.setVisible(true);
        this.f.add(p, 0);
        this.f.revalidate();
        this.f.repaint();
        return p;
    }

    // Display free roads on board.
    public JPanel showRoads() {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                g2.setColor(new Color(0f, 0f,0f, .5f));
                for (int i = 0; i < 72; i++) {
                    if(game.getBoard().getEdge(i)!= null) {
                        Line2D l = game.getBoard().getEdge(i).getShape();
                        g2.draw(l);
                    }
                }
            }
        };
        p.setOpaque(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        p.setBounds(0, 0, width, height);
        p.setVisible(true);
        this.f.add(p, 0);
        this.f.revalidate();
        this.f.repaint();
        return p;
    }

    // Place a settlement.
    public void placeSettlement(int i) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(7));
                Rectangle2D r = game.getBoard().getVertex(i).getShape();
                g2.setColor(new Color(0f, 0f,0f));
                g2.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                g2.setColor(game.getBoard().getVertex(i).getColor());
                g2.fillRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
            }
        };
        p.setOpaque(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        p.setBounds(0, 0, width, height);
        p.setVisible(true);
        this.f.add(p, 0);
        this.f.revalidate();
        this.f.repaint();
    }

    // Place a road.
    public void placeRoad(int i) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                g2.setColor(new Color(
                        (float)game.getBoard().getEdge(i).getColor().getRed()/255,
                        (float)game.getBoard().getEdge(i).getColor().getGreen()/255,
                        (float)game.getBoard().getEdge(i).getColor().getBlue()/255,
                        0.7f)
                );
                Line2D l = game.getBoard().getEdge(i).getShape();
                g2.draw(l);

            }
        };
        p.setOpaque(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        p.setBounds(0, 0, width, height);
        p.setVisible(true);
        this.f.add(p, 0);
        this.f.revalidate();
        this.f.repaint();
    }
}