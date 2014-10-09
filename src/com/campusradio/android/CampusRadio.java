package com.campusradio.android;

public class CampusRadio {

	public static final class keys {
		public static final String firstRun = "FIRST_RUN";
		public static final String uploadSongName = "upload_song_name";
		public static final String uploadArtistName = "upload_artist_name";
		public static final String uploadEmail = "upload_email";
		public static final String uploadUniversity = "upload_university";
		public static final String uploadMood = "upload_mood";
		public static final String uploadGenre = "upload_genre";
		public static final String uploadDescription = "upload_description";
		public static final String uploadUserName = "upload_username";
		public static final String uploadResponse = "upload_response";

	}

	public static class defaults{

		public static final String uploadSongName = "BAD_SONG_NAME";
		public static final String uploadArtistName = "BAD_ARTIST_NAME";
		public static final String uploadEmail = "BAD_EMAIL";
		public static final String uploadUniversity = "BAD_UNIVERSITY";
		public static final String uploadMood = "BAD_MOOD";
		public static final String uploadGenre = "BAD_GENRE";
		public static final String uploadDescription = "BAD_DESCRIPTION";
		public static final String uploadUserName = "BAD_USERNAME";
		public static final String uploadResponse = "BAD_RESPONSE";
		
	}
	
	public static class requestCodes{

		public static final int uploadSong = 1042;
		
	}
	
	public static class properties {
		public static final int splashDuration = 1000;
		public static final int uploadTimeOut = 60000*10;
	}
}