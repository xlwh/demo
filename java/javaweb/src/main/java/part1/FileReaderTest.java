package part1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

	public static void main(String[] args) {
		StringBuffer strBuf = new StringBuffer();
		char buf[] = new char[1024];
		try {
			FileReader f = new FileReader("/etc/resolv.conf");
			try {
				if(f.read(buf) > 0 ){
					strBuf.append(buf);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(strBuf.toString());
	}

}
