package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Save
{
    private HashMap<String, String> data = new HashMap<>();
	private File folder, file;
	
	public Save()
	{
		folder = new File(System.getProperty("user.home", "GameData"));
		
		if(!folder.exists())
		{
			folder.mkdir();
		}
		
		file = new File(folder, "Save.dat");
	}
	
	public void setString(String key, String value)
	{
		if(value == null)
		{
			data.remove(key);
			return;
		}
		
		data.put(key, value);
	}
	
	public String getString(String key)
	{
		return data.containsKey(key) ? data.get(key) : null;
	}
	
    public void loadConfig()
    {
        if(!file.exists())
        {
            System.out.println("File not found");
            return;
        }
        
        BufferedReader br;
        
        try
        {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] info = line.split(";");
            data.clear();

            for(String datazinha : info)
            {
                String[] keyValue = datazinha.split(":");
                

                try
                {
                    data.put(keyValue[0], keyValue[1]);
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    continue;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

	public void saveFile()
	{
		try
        {
            if(file.exists())
            {
                file.delete();
            }

            FileOutputStream fos = new FileOutputStream(file);
            StringBuilder sb = new StringBuilder();

            synchronized(this) // Previnir o erro ConcurrentModificationException
            {
                for(String key : data.keySet())
                {
                    sb.append(key);
                    sb.append(":");
                    sb.append(data.get(key));
                    sb.append(";");
                }
            }

            String gameData = sb.toString();

            fos.write(gameData.getBytes());
            fos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
	}
}