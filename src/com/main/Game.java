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
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;

	public static int FPS = 0;

	public static ObstacleGenerator generator;
	public static Player player;
	public static JFrame frame;
	public static Spritesheet spritesheet;
	public static World world;
	public static State state = State.MENU;

	public static List<Entity> entities;

	public static boolean canGenerate = true;

	private Thread thread;
	private BufferedImage image;
	private boolean isRunning = true;

	private int SCALE = 4;

	public Game() {
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// Inicializando Objetos
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheetDino.png");
		player = new Player(16, 16, Valores.TILE_SIZE, Valores.TILE_SIZE, spritesheet.getSprite(32, 0, Valores.TILE_SIZE, Valores.TILE_SIZE));
		entities.add(player);
		world = new World("/mapDino.png");

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

		if(state == State.NORMAL)
		{
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
		}
		
		generator.tick();
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

		/* Renderização do jogo */
		world.render(g); // Primeira cois a ser renderizada
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

		bs.show();

	}

	private void drawFPS(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 12));
		g.drawString("FPS: " + FPS, 10, 10);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() {
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

			if (System.currentTimeMillis() - timer >= 1000) { // Passou 1 segundo após a ultima vez que mostrou a
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
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}

	}

}
