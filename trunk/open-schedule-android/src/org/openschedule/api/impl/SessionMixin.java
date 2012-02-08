/**
 *  This file is part of OpenSchedule for Android
 * 
 *  OpenSchedule for Android is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenSchedule for Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSchedule for Android.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Daniel Frey <dmfrey at gmail dot com>
 * 
 * This software can be found at <http://code.google.com/p/open-schedule-android/>
 *
 */
package org.openschedule.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Comment;
import org.openschedule.api.Speaker;

/**
 * Mixin class for adding Jackson annotations to Session.
 * 
 * @author Daniel Frey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class SessionMixin {

	@JsonCreator
	SessionMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "name" ) String name,
			@JsonProperty( "description" ) String description,
			@JsonProperty( "speakers" ) List<Speaker> speakers,
			@JsonProperty( "comments" ) List<Comment> comments
	) {}

}
