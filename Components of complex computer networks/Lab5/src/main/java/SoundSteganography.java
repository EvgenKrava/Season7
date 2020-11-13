import javax.sound.sampled.AudioInputStream;

public class SoundSteganography {

    AudioInputStream audioInputStream;

    public SoundSteganography(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    public AudioInputStream encode(AudioInputStream audioInputStream, String message) {
        return audioInputStream;
    }

    public String decode(AudioInputStream audioInputStream) {
        return "";
    }
}
