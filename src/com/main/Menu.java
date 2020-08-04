package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	public String[] options = { "Start Game", "Credits", "Exit" };
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	public boolean up, down, enter;
	public static boolean pause = false;

	public void tick() {
		if (up) {
			up = false;
			currentOption--;
			if (currentOption < 0) {
				currentOption = maxOption;
			}
		}
		if (down) {
			down = false;
			currentOption++;
			if (currentOption > maxOption) {
				currentOption = 0;
			}
		}
		if (enter) {
			enter = false;
			if (options[currentOption] == "Start Game" || options[currentOption] == "Resume") {
				Game.state = State.NORMAL;
				pause = false;
			} else if (options[currentOption] == "Exit") {
				System.exit(1);
			} else if (options[currentOption] == "Credits") {
				Game.state = State.CREDITS;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 50));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 70));
		g.drawString("> LogoAqui <", 265, 100);

		
		// Opções de menu
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		if (pause)
			g.drawString("Resume", 429, 250);
		else
			g.drawString("Start Game", 410, 250);
		g.drawString("Credits", 435, 300);
		g.drawString("Exit", 460, 350);

		if (options[currentOption] == "Start Game") {
			g.drawString(">", 385, 250);
		} else if (options[currentOption] == "Credits") {
			g.drawString(">", 410, 300);
		} else if (options[currentOption] == "Exit") {
			g.drawString(">", 435, 350);
		}

		
		
	}

}
