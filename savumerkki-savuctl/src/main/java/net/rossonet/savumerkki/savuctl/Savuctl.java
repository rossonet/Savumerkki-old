/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    */
package net.rossonet.savumerkki.savuctl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Classe main per avvio
 *
 * @author Andrea Ambrosini
 */
public class Savuctl {

	public static final long WHILE_DELAY = 60 * 1000L;
	private static AppManager appManager = null;
	private static int identLevelStatusFile = 2;
	private static final Logger logger = Logger.getLogger(Savuctl.class.getName());

	private static boolean running = true;

	public static AppManager getAppManager() {
		return appManager;
	}

	public static int getIdentLevelStatusFile() {
		return identLevelStatusFile;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void main(final String[] args) {
		runApp();
	}

	public static void runApp() {
		appManager = new AppManagerImplementation();
		System.out.println("agent started");
		System.out.println(appManager.toString());
		System.out.println("started " + DaemonManager.getInstance().toString());
		DaemonManager.getInstance().reloadConfiguration();
		while (running) {
			try {
				writeStringToFile(appManager.getJsonStatus().toString(identLevelStatusFile),
						appManager.getStatusFilePath());
				Thread.sleep(WHILE_DELAY);
			} catch (final Exception e) {
				System.out.println("agent stopped");
				e.printStackTrace();
				System.exit(100);
			}
		}
		try {
			reset();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void setIdentLevelStatusFile(final int identLevelStatusFile) {
		Savuctl.identLevelStatusFile = identLevelStatusFile;
	}

	public static void stopSavuctl() {
		Savuctl.running = false;
	}

	public static final void writeStringToFile(final String text, final String fileName) throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(text);
		writer.close();
	}

	private static void reset() throws Exception {
		if (appManager != null) {
			appManager.close();
			appManager = null;
		}
	}
}
