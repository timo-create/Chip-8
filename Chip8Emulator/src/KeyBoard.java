import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyBoard implements KeyListener{
	
	public HashMap<Character,Boolean> keyMap = new HashMap<Character, Boolean>();
	
	public KeyBoard()  {
		
		
		keyMap.put('1', false);
		keyMap.put('2', false);
		keyMap.put('3', false);
		keyMap.put('4', false);
		keyMap.put('q', false);
		keyMap.put('w', false);
		keyMap.put('e', false);
		keyMap.put('r', false);
		keyMap.put('a', false);
		keyMap.put('s', false);
		keyMap.put('d', false);
		keyMap.put('f', false);
		keyMap.put('z', false);
		keyMap.put('x', false);
		keyMap.put('c', false);
		keyMap.put('v', false);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
		if(keyMap.get(e.getKeyChar())  != null) {keyMap.put(e.getKeyChar(), true); };
		
	
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(keyMap.get(e.getKeyChar()) != null) {keyMap.put(e.getKeyChar(), false); };
	
		
	}

}
