
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.lang.Thread;

public class Main extends Thread {

	public static Display canvas;
	public static CPU cpu;

	public static void main(String[] args) throws IOException {

		Path path = Paths.get("ROMS/spaceInvaders.ch8");
		byte[] array = Files.readAllBytes(path);
		canvas = new Display();
		cpu = new CPU(array, canvas);

		Main thread = new Main();
		thread.start();

	}

	public void run() {

		while (true) {

			for(int i = 0; i< 8; i++) {
				
				cpu.cycle();
				if(cpu.drawFlag == true) {
					
					
					canvas.panel.repaint();
				
				}
				
				
			}
			
			

			if (cpu.delayTimer > 0) {
				cpu.delayTimer--;
			}
			if (cpu.soundTimer > 0) {
				cpu.soundTimer--;
			}

			

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
