package me.camdenorrb.zambiegame;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		//final SwingGui gui = new SwingGui("Meow", new Dimension(1000, 1000));

		final ZambieGame game = new ZambieGame();
		game.start();

		//Thread.sleep(1000);

		/*final ProcGui gui = new ProcGui("Meow", new Dimension(1000, 500));
		gui.show();

		Thread.sleep(1000);
		gui.hide();

		Thread.sleep(1000);
		gui.show();*/

		//new SwingGui(new Dimension(1000, 1000)).show();
	}

}
