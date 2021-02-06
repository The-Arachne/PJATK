package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorBenc extends SimpleFileVisitor<Path>{

	FileChannel resutChannel;
	public FileVisitorBenc(String res_in_file) throws FileNotFoundException {
		File xd=new File(res_in_file);
		System.out.println(xd.getAbsolutePath());
		resutChannel=new FileOutputStream(xd).getChannel();
	}
	
	public void recode4UTF(FileChannel fileChannel, long l){
		ByteBuffer buff=ByteBuffer.allocate((int)l+1);
		Charset fromDecode  = Charset.forName("Cp1250");
		Charset	toDecode = Charset.forName("UTF-8");
		try {
			fileChannel.read(buff);
			buff.flip();
			CharBuffer decode=fromDecode.decode(buff);
			ByteBuffer encode=toDecode.encode(decode);
			
			while(encode.hasRemaining()){
				resutChannel.write(encode);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		recode4UTF(FileChannel.open(file), attrs.size());
		return FileVisitResult.CONTINUE;
	}
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.err.println("Nie udalo sie obejrzec pliku: " + exc);
		return FileVisitResult.CONTINUE;
	}
}
