package SwingSystem;

import java.awt.BufferCapabilities;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;


public class JFrameWindow implements Window<BufferedImageWrapper,Graphics2DRenderer,KeyListenerEventHandler> {
	JFrame jFrame;
	JPanel innerPanel;
	Graphics2DRenderer renderer = new Graphics2DRenderer(this);
	KeyListenerEventHandler kbd = new KeyListenerEventHandler();
	
	JFrameWindow() {
		this.jFrame = new JFrame();
		// west virginia
		jFrame.addKeyListener(kbd);
	}
	
	@Override
	public void destroy() {
		jFrame.dispose();
	}

	@Override
	public Graphics2DRenderer getRenderer() {
		return renderer;
	}

	@Override
	public int getWidth() {
		return jFrame.getWidth();
	}

	@Override
	public int getHeight() {
		return jFrame.getHeight();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KeyListenerEventHandler getKeyboardEventHandler() {
		return kbd;
	}
	

	
	// optional
	@Override
	public void setSize(int x, int y) {
		jFrame.setSize(x, y);
	}
	@Override
	public void setIcon(BufferedImageWrapper icon) {
		jFrame.setIconImage(icon.image);
	}
	@Override
	public void setTitle(String title) {
		jFrame.setTitle(title);
	}
	@Override
	public void center() {
		jFrame.setLocationRelativeTo(null);
	}
	@Override
	public void setPositionOnScreen(int x, int y) {
		jFrame.setLocation(x, y);
	}
	@Override
	public void setResizable(boolean resizable) {
		jFrame.setResizable(resizable);
	}
	@Override
	public void setFPSCap(int fpsCap) {}
	@Override
	public void setVSync(boolean vSync) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferCapabilities bc = gc.getBufferCapabilities();
        // TODO use BufferStrategy before using this code, also finish writing it
        /*
        try {
        	// this code is ugly because i need to access it without importing it
        	
			Class<? extends BufferCapabilities> egc = (Class<? extends BufferCapabilities>)
					Class.forName("sun.java2d.pipe.hw.ExtendedBufferCapabilities");
			Class<?> vst = Class.forName("sun.java2d.pipe.hw.ExtendedBufferCapabilities$VSyncType");
			if (egc == null)
				return;
			if (egc.isInstance(gc)) {
				Method deriveMethod = egc.getMethod("derive", vst);
				deriveMethod.invoke(deriveMethod, null)
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			// does not support extended capabilities
			e.printStackTrace();
			return;
		}
		*/
	}
	@Override
	public void sync() {}
	@Override
	public void onWindowClose(Runnable action) {
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				action.run();
			}
		});
	}

}
