package SwingSystem;

import javax.swing.JFrame;

import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;

public class JFrameWindow implements Window<BufferedImageWrapper,Graphics2DRenderer,KeyListenerEventHandler> {
	JFrame jFrame;
	
	@Override
	public void destroy() {
		jFrame.dispose();
	}

	@Override
	public Renderer getRenderer() {
		// get panel
		//return new Graphics2DRenderer()
		return null;
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
	public KeyboardEventHandler getKeyboardEventHandler() {
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
	public void center() {}
	public void setPositionOnScreen(int x, int y) {}
	public void setResizable(boolean resizable) {}
	public void setFPSCap(int fpsCap) {}
	public void setVSync(boolean vSync) {}
	public void sync() {}
	public void onWindowClose(Runnable action) {}

}
