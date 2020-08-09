package com.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound
{
	public static Clips music = load("/msc_do_jogo.wav", 1);
	public static Clips jump = load("/pulo_dino.wav", 1);
	public static Clips gameover = load("/dino_morrendo.wav", 1);
	
	public static class Clips
	{
		public Clip clips;
		
		public Clips(byte[] buffer) throws IllegalArgumentException, LineUnavailableException, IOException, UnsupportedAudioFileException
		{
			if(buffer == null)
			{
				throw new IllegalArgumentException("Buffer n�o pode ser nulo");
			}
			
			clips = AudioSystem.getClip();
			clips.open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
		}
		
		public void stop()
		{
			clips.stop();
			clips.setFramePosition(0);
		}
		
		public void play(float volume) throws IllegalStateException
		{
			if(clips == null)
			{
				throw new IllegalStateException("Lista de clips est� vazia");
			}
			
			FloatControl gainControl = ((FloatControl) clips.getControl(FloatControl.Type.MASTER_GAIN));
			gainControl.setValue((gainControl.getMaximum() - gainControl.getMinimum() * volume) + gainControl.getMinimum());
			
			stop();
			
			clips.start();
		}
		
		public void loop(float volume) throws IllegalStateException
		{
			if(clips == null)
			{
				throw new IllegalStateException("Lista de clips est� vazia");
			}
			
			FloatControl gainControl = ((FloatControl) clips.getControl(FloatControl.Type.MASTER_GAIN));
			gainControl.setValue((gainControl.getMaximum() - gainControl.getMinimum() * volume) + gainControl.getMinimum());
			
			clips.loop(300);
		}
	}
	
	private static Clips load(String name, int count)
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream(name));
			
			byte[] buffer = new byte[1024];
			
			int read = 0;
			
			while((read = dis.read(buffer)) >= 0)
			{
				baos.write(buffer, 0, read);
			}
			
			dis.close();
			
			byte[] data = baos.toByteArray();
			
			try
			{
				return new Clips(data);
			} 
			catch (IllegalArgumentException e)
			{
				System.out.println("Argumentos inv�lidos: " + e.getMessage());
			} 
			catch (LineUnavailableException e)
			{
				System.out.println("Linha indispon�vel");
			} 
			catch (UnsupportedAudioFileException e)
			{
				System.out.println("Arquivo de audio n�o suportado");
			}
		}
		catch(NullPointerException | IOException e)
		{
			try
			{
				try
				{
					return new Clips(null);
				} 
				catch (IllegalArgumentException e1)
				{
					System.out.println("Argumentos inv�lidos: " + e.getMessage());
				} 
				catch (LineUnavailableException e1)
				{
					System.out.println("Linha indispon�vel");
				} 
				catch (UnsupportedAudioFileException e1)
				{
					System.out.println("Arquivo de audio n�o suportado");
				}
			} 
			catch (IOException e1)
			{
				return null;
			}
		}
		
		return null;
	}
}
