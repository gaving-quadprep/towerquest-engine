package SwingSystem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;

public class JFrameWindow implements Window<BufferedImageWrapper,Graphics2DRenderer,KeyListenerEventHandler> {
	JFrame jFrame;
	JPanel innerPanel;
	Graphics2DRenderer renderer = new Graphics2DRenderer();
	
	JFrameWindow() {
		this.jFrame = new JFrame();
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
		// TODO Auto-generated method stub
		return null;
	}
	

	
	// optional
	public void setIcon(BufferedImageWrapper icon) {
		jFrame.setIconImage(icon.image);
	}
	public void setTitle(String title) {
		jFrame.setTitle(title);
	}
	public void center() {
		jFrame.setLocationRelativeTo(null);
	}
	public void setPositionOnScreen(int x, int y) {
		jFrame.setLocation(x, y);
	}
	public void setResizable(boolean resizable) {
		jFrame.setResizable(resizable);
	}
	public void setFPSCap(int fpsCap) {}
	public void setVSync(boolean vSync) {
		// ExtendedBufferCapabilities.VSyncType.VSYNC_ON
	}
	public void sync() {}
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
