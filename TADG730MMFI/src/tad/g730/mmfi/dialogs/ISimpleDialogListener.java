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

package tad.g730.mmfi.dialogs;

/**
 * Implement this interface in Activity or Fragment to react to positive and negative buttons.
 *
 * @author David Vávra (david@inmite.eu)
 */
public interface ISimpleDialogListener {
	public void onPositiveButtonClicked(int requestCode);
}
