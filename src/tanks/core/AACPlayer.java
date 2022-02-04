package tanks.core;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import net.sourceforge.jaad.aac.AACException;
import net.sourceforge.jaad.aac.Decoder;
import net.sourceforge.jaad.aac.SampleBuffer;
import net.sourceforge.jaad.mp4.MP4Container;
import net.sourceforge.jaad.mp4.api.AudioTrack;
import net.sourceforge.jaad.mp4.api.Frame;
import net.sourceforge.jaad.mp4.api.Movie;
import net.sourceforge.jaad.mp4.api.Track;

public class AACPlayer {

	private float volume;
	private String input;
	private boolean playing;
	
	public AACPlayer(String in) {
		this.input = in;
		this.volume = 50;
	}
	
	public AACPlayer(String in, float vol) {
		this.input = in;
		this.volume = vol;
	}
	

	public void play() throws Exception {
		if(!playing) {
			decodeMP4();
			playing = true;
		}
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	private void decodeMP4() throws Exception {
		SourceDataLine line = null;
		byte[] b;
		try {
			//create container
			final MP4Container cont = new MP4Container(new RandomAccessFile(input, "r"));
			final Movie movie = cont.getMovie();
			//find AAC track
			final List<Track> tracks = movie.getTracks(AudioTrack.AudioCodec.AAC);
			if(tracks.isEmpty()) throw new Exception("movie does not contain any AAC track");
			final AudioTrack track = (AudioTrack) tracks.get(0);

			//create audio format
			final AudioFormat aufmt = new AudioFormat(track.getSampleRate(), track.getSampleSize(), track.getChannelCount(), true, true);
			line = AudioSystem.getSourceDataLine(aufmt);
			line.open();
			line.start();

			final FloatControl volumeControl = (FloatControl) line.getControl( FloatControl.Type.MASTER_GAIN );
			volumeControl.setValue( 20.0f * (float) Math.log10( volume / 100.0 ) );
			
			//create AAC decoder
			final Decoder dec = new Decoder(track.getDecoderSpecificInfo());

			//decode
			Frame frame;
			final SampleBuffer buf = new SampleBuffer();
			while(track.hasMoreFrames()) {
				frame = track.readNextFrame();
				try {
					dec.decodeFrame(frame.getData(), buf);
					b = buf.getData();
					line.write(b, 0, b.length);
				}
				catch(AACException e) {
					e.printStackTrace();
					//since the frames are separate, decoding can continue if one fails
				}
			}
		}
		finally {
			if(line!=null) {
				line.stop();
				line.close();
			}
		}
	}
	
}
