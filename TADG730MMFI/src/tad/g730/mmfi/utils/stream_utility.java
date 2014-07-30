package tad.g730.mmfi.utils;
/*codes in this class are referenced from Koush Superuser
 * It's needed by extract_helper class
 * */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class stream_utility {
	
	public static void copyStream(InputStream input, OutputStream output) throws IOException
    {
        final ReadableByteChannel inputChannel = Channels.newChannel(input);
        final WritableByteChannel outputChannel = Channels.newChannel(output);
        // copy the channels
        fastChannelCopy(inputChannel, outputChannel);
    }
	
    public static void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(1 << 17);
        while (src.read(buffer) != -1) {
            // prepare the buffer to be drained
            buffer.flip();
            // write to the channel, may block
            dest.write(buffer);
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear()
            buffer.compact();
        }
        // EOF will leave buffer in fill state
        buffer.flip();
        // make sure the buffer is fully drained.
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }
    
   
}

