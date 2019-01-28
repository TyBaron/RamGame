package movableSprite.src.main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	public Sounds() {
	}
	public void playSounds() {
				
		try {
			Clip clip = AudioSystem.getClip();
			Clip t = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/Ram.wav")));
			t.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/imtrash.wav")));
			clip.start();
			t.start();
			
		}catch(Exception e) {
			System.out.println("messed");
		}
	}
	public void throwTrash() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/throw.wav")));
			clip.start();
			
		}catch(Exception e) {
			System.out.println("messed");
		}
	}
}
