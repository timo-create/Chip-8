import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CPU {

	public int[] memory;
	public short[] stack;
	public byte[] V = new byte[16];

	public HashMap<Byte, Character> keyMapCPU = new HashMap<Byte, Character>();
	public boolean keys[] = new boolean[16];
	public Display canvas;
	public boolean drawFlag;

	public final short[] font = { 0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
			0x20, 0x60, 0x20, 0x20, 0x70, // 1
			0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
			0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
			0x90, 0x90, 0xF0, 0x10, 0x10, // 4
			0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
			0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
			0xF0, 0x10, 0x20, 0x40, 0x40, // 7
			0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
			0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
			0xF0, 0x90, 0xF0, 0x90, 0x90, // A
			0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
			0xF0, 0x80, 0x80, 0x80, 0xF0, // C
			0xE0, 0x90, 0x90, 0x90, 0xE0, // D
			0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
			0xF0, 0x80, 0xF0, 0x80, 0x80 // F

	};

	byte[] ch8;

	public byte sp;
	public short pc;
	public int opCode;
	public short index;

	public int delayTimer;
	public int soundTimer;

	public int[] video = new int[64 * 32];

	public CPU(byte[] ch8, Display canvas) {

		this.canvas = canvas;
		this.pc = 0x200;
		this.sp = 0;
		this.index = 0;
		opCode = 0;

		memory = new int[4069];
		stack = new short[16];
		this.ch8 = ch8;
		for (int i = 0; i < ch8.length; i++) {
			memory[i + 0x200] = ch8[i];

		}
		for (int i = 0; i < font.length; i++) {

			memory[i + 0x50] = (byte) font[i];

		}

		keyMapCPU.put((byte) 1, '1');
		keyMapCPU.put((byte) 2, '2');
		keyMapCPU.put((byte) 3, '3');
		keyMapCPU.put((byte) 0xC, '4');
		keyMapCPU.put((byte) 4, 'q');
		keyMapCPU.put((byte) 5, 'w');
		keyMapCPU.put((byte) 6, 'e');
		keyMapCPU.put((byte) 0xD, 'r');
		keyMapCPU.put((byte) 7, 'a');
		keyMapCPU.put((byte) 8, 's');
		keyMapCPU.put((byte) 9, 'd');
		keyMapCPU.put((byte) 0xE, 'f');
		keyMapCPU.put((byte) 0, 'z');
		keyMapCPU.put((byte) 0xA, 'x');
		keyMapCPU.put((byte) 0xB, 'c');
		keyMapCPU.put((byte) 0xF, 'v');

	}

	public void clearScreen(int[] video, int x) {

		for (int i = 0; i < video.length; i++) {

			video[i] = x;

		}
	}

	public void cycle() {

		int currentByte = (int) memory[pc] & 0xff;
		int nextByte = (int) memory[pc + 1] & 0xff;
		opCode = currentByte << 8 | nextByte;

		byte x = (byte) ((opCode & 0x0F00) >> 8);
		byte y = (byte) ((opCode & 0x00F0) >> 4);
		byte kk = (byte) ((opCode & 0x00FF));
		drawFlag = false;

		// System.out.println("currentByte: "+Integer.toHexString(currentByte)+ "
		// nextByte: "+ Integer.toHexString(nextByte)+ " pc: "+pc+ " opcode:
		// "+Integer.toHexString(opCode));

		switch (opCode & 0xF000) {

		case (0x0000): {

			switch (opCode & 0x00FF) {

			case (0xE0): {

				clearScreen(video, 0);
				canvas.panel.video = video;
				pc += 2;
				drawFlag = true;

				break;
			}
			case (0xEE): {

				sp--;
				pc = stack[sp];
				pc += 2;

				break;
			}

			}
			break;
		}

		case (0x1000): {

			pc = (short) (opCode & 0x0FFF);
			break;
		}
		case (0x2000): {

			stack[sp] = pc;
			sp++;
			pc = (short) (opCode & 0x0FFF);

			break;
		}
		case (0x3000): {

			if (V[x] == kk) {
				pc += 4;
			} else {
				pc += 2;
			}
			break;

		}

		case (0x4000): {

			if (V[x] != kk) {
				pc += 4;
			}

			else {
				pc += 2;
			}
			break;
		}
		case (0x5000): {

			if (V[x] == V[y]) {
				pc += 4;
			} else {
				pc += 2;
			}
			break;
		}
		case (0x6000): {

			V[x] = kk;
			pc += 2;

			break;
		}
		case (0x7000): {

			V[x] = (byte) (V[x] + kk);
			pc += 2;
			break;
		}
		case (int) (0x8000): {

			switch (opCode & 0xF) {

			case (0x0): {

				V[x] = V[y];

				pc += 2;
				break;
			}
			case (0x1): {

				V[x] = (byte) (V[x] | V[y]);
				pc += 2;
				break;
			}
			case (0x2): {

				V[x] &= V[y];
				pc += 2;
				break;
			}
			case (0x3): {

				V[x] ^= V[y];
				pc += 2;
				break;
			}
			case (0x4): {

				int sum = (V[x] + V[y]);

				V[0xF] = 0;
				if (sum > 0xFF) {

					V[0xF] = 1;
				}

				V[x] = (byte) (sum);
				pc += 2;
				break;
			}
			case (0x5): {

				int uX = V[x] & 0xFF;
				int uY = V[y] & 0xFF;

				V[0xF] = 0;
				if (uX > uY) {
					V[0xF] = 1;
				}
				V[x] = (byte) (V[x] - V[y]);
				pc += 2;
				break;
			}

			case (0x6): {

				V[0xF] = (byte) (V[x] & 0x1);
				V[x] >>= 1;
				pc += 2;
				break;
			}
			case (0x7): {

				V[0xF] = 0;
				if (V[y] > V[x]) {
					V[0xF] = 1;
				}
				V[x] = (byte) (V[y] - V[x]);
				pc += 2;
				break;
			}
			case (0xE): {

				V[0xF] = (byte) (V[x] & 0x80);
				V[x] <<= 1;
				pc += 2;
				break;

			}

			}
			break;

		}
		case (int) (0x9000): {

			if (V[x] != V[y]) {
				pc += 4;
			} else {
				pc += 2;
			}

			break;
		}
		case (int) (0xA000): {

			index = (short) (opCode & 0x0FFF);
			pc += 2;

			break;
		}
		case (int) (0xB000): {
			pc = (short) ((opCode & 0x0FFF) + V[0x0]);
			break;
		}
		case (int) (0xC000): {
			Random rand = new Random();
			int value = rand.nextInt(256);
			V[x] = (byte) (value & kk);
			pc += 2;
			break;
		}
		case (int) (0xD000): {

			byte height = (byte) (opCode & 0x000F);
			V[0xF] = 0;
			for (int i = 0; i < height; i++) {

				int sprite = memory[index + i];
				int yPos = ((V[y] + i) % 32);

				for (int j = 0; j < 8; j++) {

					int xPos = ((V[x] + j) % 64);
					int spritePixel = (sprite & 0x80) >> 7;
					int offset = (yPos * 64 + xPos);
					int newValue;

					if (spritePixel != 0 && video[offset] == 1) {

						
						V[0xF] = 1;

					} 
					newValue = video[offset] ^ spritePixel;

					video[offset ] = newValue;
					sprite <<= 1;
					canvas.panel.video = video;

				}
			}
			drawFlag = true;
			pc += 2;
			break;
		}

		case (int) (0xE000): {

			switch (opCode & 0x00F0) {

			case (int) (0x90): {

				if (canvas.kb.keyMap.get(keyMapCPU.get(V[x])) == true) {

					pc += 4;
				} else {
					pc += 2;
				}
				break;

			}
			case (int) (0xA0): {

				if (canvas.kb.keyMap.get(keyMapCPU.get(V[x])) == false) {

					pc += 4;

				} else {

					pc += 2;
				}
				break;
			}
			}
			break;
		}

		case (int) (0xF000): {

			switch (opCode & 0xFF) {

			case (0x07): {

				V[x] = (byte) delayTimer;
				pc += 2;
				break;
			}
			case (0x0A): {

				if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0))) {
					V[x] = 0;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 1))) {
					V[x] = 1;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 2))) {
					V[x] = 2;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 3))) {
					V[x] = 3;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 4))) {
					V[x] = 4;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 5))) {
					V[x] = 5;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 6))) {
					V[x] = 6;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 7))) {
					V[x] = 7;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 8))) {
					V[x] = 8;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 9))) {
					V[x] = 9;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0xA))) {
					V[x] = 0xA;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0xB))) {
					V[x] = 0xB;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0xC))) {
					V[x] = 0xC;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0xD))) {
					V[x] = 0xD;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0xE))) {
					V[x] = 0xE;
				} else if (canvas.kb.keyMap.get(keyMapCPU.get((byte) 0xF))) {
					V[x] = 0xF;
				} else {
					pc -= 2;
				}
				pc += 2;
				break;
			}
			case (0x15): {

				delayTimer = V[x];
				pc += 2;
				break;
			}
			case (0x18): {
				soundTimer = V[x];
				pc += 2;
				break;
			}
			case (0x1E): {
				index = (short) (index + V[x]);
				pc += 2;
				break;
			}
			case (0x29): {
				index = (short) (0x50 + (5 * V[x]));
				pc += 2;
				break;
			}
			case (0x33): {

				int value = V[x] & 0xFF;
				memory[index + 2] = (value % 10);
				value /= 10;
				memory[index + 1] = (value % 10);
				value /= 10;
				memory[index] = (value % 10);
				pc += 2;
				break;
			}
			case (0x55): {

				for (int i = 0; i <= x; i++) {

					memory[index + i] = V[i];
				}
				pc += 2;
				break;
			}
			case (0x65): {

				for (int i = 0; i <= x; i++) {

					V[i] = (byte) memory[index + i];
				}
				pc += 2;
				break;
			}

			}
			break;
		}

		}

	}

}
