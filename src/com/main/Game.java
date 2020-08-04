package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.entities.Entity;
import com.entities.Player;
import com.graphics.Spritesheet;
import com.world.ObstacleGenerator;
import com.world.World;

public class Game extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 0L;

	public static final int WIDTH = 240;
	public static final int HEIGHT = 135;

	public static int FPS = 0;

	public static ObstacleGenerator generator;
	public static Player player;
	public static JFrame frame;
	public static Spritesheet spritesheet;
	public static World world;
	public Menu menu;
	public static State state = State.MENU;

	public static List<Entity> entities;

	public static boolean canGenerate = true;

	private Thread thread;
	private BufferedImage image;
	private boolean isRunning = true;

	public static int SCALE = 4;

	public Game() {
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// Inicializando Objetos
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheetDino.png");
		player = new Player(16, 16, Valores.TILE_SIZE, Valores.TILE_SIZE,
				spritesheet.getSprite(32, 0, Valores.TILE_SIZE, Valores.TILE_SIZE));
		entities.add(player);
		world = new World("/mapDino.png");
		menu = new Menu();

		generator = new ObstacleGenerator();
	}

	public void initFrame() {
		frame = new JFrame("Jogo Dino");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		Thread thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {

		if (state == State.NORMAL) {
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
		} else if (state == State.MENU) {
			menu.tick();
		}
		
		generator.tick();
		
		if(Menu.pause) {
			canGenerate = false;
		}else {
			canGenerate = true;
		}
		
		
			
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		/* Renderiza�ao do jogo */
		world.render(g); // Primeira coisa ser renderizada
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		drawFPS(g);
		if (state == State.MENU) {
			menu.render(g);
		}

		if(Game.state == State.CREDITS) {
			String texto = "Cr�ditos:";
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial" , Font.BOLD, 40));
			int x = (Game.WIDTH * Game.SCALE - g.getFontMetrics().stringWidth(texto)) / 2;
			g.drawString("Cr�ditos:", x, 70);
		}
		
		bs.show();
	}

	private void drawFPS(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 12));
		g.drawString("FPS: " + FPS, 12, 12);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime(); // Pega o tempo do computador em nano segundo.
		double amountOfTicks = 60.0; // 60 frames por segundo FPS
		double ns = 1000000000 / amountOfTicks; // 1 segundo em nano/fps
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) { // Passou 1 segundo ap�s a ultima vez que mostrou a
																// mensagem
				FPS = frames;
				frames = 0;
				timer += 1000;
			}

		}
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			player.jump = true;
			break;

		default:
			break;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN && state == State.MENU) {
			menu.down = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP && state == State.MENU) {
			menu.up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && state == State.MENU) {
			menu.enter = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			state = State.MENU;
			menu.pause = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_M && state == State.CREDITS){
			state = State.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			player.jump = false;
			break;

		default:
			break;
		}

	}

}
