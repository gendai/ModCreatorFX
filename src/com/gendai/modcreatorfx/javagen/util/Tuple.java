/*
 * ModCreatorFX, a mod generator with templates
 * Copyright (C) gendai <https://github.com/gendai/ModCreatorFX>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.gendai.modcreatorfx.javagen.util;

/**
 * Generic Tuple type.
 * @author gendai
 * @version 0.0.1
 * @param <X> the first tuple type.
 * @param <Y> the second tuple type.
 */
public class Tuple<X, Y> { 
	  public final X lines; 
	  public final Y serial; 
	  public Tuple(X lines, Y serial) { 
	    this.lines = lines; 
	    this.serial = serial; 
	  } 
} 
