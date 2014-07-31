/*
 * Copyright © 2014 TAD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package tad.g730.mmfi.utils;
/*used to check if already installed
 * */
import java.io.File;

public class check_state {
	public static boolean checkInstalled(){
		File fdexbak=new File("/system/framework/framework.odex.bak");
		File robotobak=new File("/system/fonts/Roboto-Regular.ttf.bak");
		if(fdexbak.exists() && robotobak.exists())
		return true;
		return false;
	}

}
