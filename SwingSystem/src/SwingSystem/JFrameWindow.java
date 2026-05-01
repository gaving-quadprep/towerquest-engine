package SwingSystem;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;


public class JFrameWindow implements Window<BufferedImageWrapper,Graphics2DRenderer,KeyListenerEventHandler> {
	JFrame jFrame;
	Canvas canvas;
	Graphics2DRenderer renderer = new Graphics2DRenderer(this);
	KeyListenerEventHandler kbd = new KeyListenerEventHandler();
	private int numBuffers = 2;
	
	JFrameWindow() {
		this.jFrame = new JFrame();
		canvas = new Canvas();
		canvas.addKeyListener(kbd);
		jFrame.add(canvas);
		jFrame.pack();
		jFrame.setVisible(true);
		canvas.createBufferStrategy(numBuffers);
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
        BufferStrategy bs = canvas.getBufferStrategy();
        BufferCapabilities bc = bs.getCapabilities();
        // TODO use BufferStrategy before using this code, also finish writing it
        
        try {
        	// this code is ugly because i need to access it without importing it
        	
			Class<? extends BufferCapabilities> egc = (Class<? extends BufferCapabilities>)
					Class.forName("sun.java2d.pipe.hw.ExtendedBufferCapabilities");
			Class<? extends Enum> vst = (Class<? extends Enum>) Class.forName("sun.java2d.pipe.hw.ExtendedBufferCapabilities$VSyncType");
			Object vsyncSetting = Enum.valueOf(vst, vSync ? "VSYNC_ON" : "VSYNC_OFF");
			if (egc == null)
				return;
			Constructor constructor = egc.getConstructor(new Class[] {BufferCapabilities.class, vst});
			BufferCapabilities newbc = (BufferCapabilities) constructor.newInstance(bc, vsyncSetting);
			canvas.createBufferStrategy(numBuffers, newbc); // Regenerates bufferStrategy
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
				| InvocationTargetException | IllegalAccessException | InstantiationException
				| IllegalArgumentException | AWTException e) {
			/* Don't you love Java exception handling?
			 * (in this case, system does not support extended capabilities)
			 */
			e.printStackTrace();
			return;
		}
		
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
