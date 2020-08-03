package com.world;

import java.util.Random;

import com.entities.Obstacle;
import com.main.Game;
import com.main.Valores;

public class ObstacleGenerator
{
    private Random random;
    
    private int time = 0;
    private int maxTime = 120;

    public ObstacleGenerator()
    {
        random = new Random();
    }
    
    public void tick()
    {
        if(!Game.canGenerate)
            return;

        time++;

        if(time >= maxTime)
        {
            time = 0;
            maxTime = random.nextInt(180 - 120) + 120;

            createEntity();
        }
    }

    private void createEntity()
    {
        Obstacle obstacle = new Obstacle(Game.WIDTH, Valores.floor + 6, 10, 10, null);
        Game.entities.add(obstacle);
    }
}