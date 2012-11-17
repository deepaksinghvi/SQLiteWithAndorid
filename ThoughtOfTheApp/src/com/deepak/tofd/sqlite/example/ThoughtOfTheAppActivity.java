package com.deepak.tofd.sqlite.example;

import java.util.Random;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThoughtOfTheAppActivity extends Activity {

	private Button nextButton;
	private Button thanksButton;
	private SQLiteDatabase thoughtfulDB = null;
	private final String THOUGHTFUL_DB_NAME = "thoughtfulDB";
	private final String THOUGHTFUL_TABLE_NAME = "thoughtfultable";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final TextView textview = (TextView) findViewById(R.id.textview1);
		try {
			thoughtfulDB = this.openOrCreateDatabase(THOUGHTFUL_DB_NAME,
					MODE_PRIVATE, null);

			thoughtfulDB
					.execSQL("CREATE TABLE IF NOT EXISTS THOUGHTFUL_TABLE_NAME (thought VARCHAR, slno INT(3));");

			insertDataIntoThoughtFulDB();
			String randomThought = getRandomThoughtFromDatabase();
			textview.setText(randomThought);
		} catch (SQLiteException se) {
			Log.e(getClass().getSimpleName(),
					"Could not create or Open the database");
		}
		

		nextButton = (Button) findViewById(R.id.buttonNext);
		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String randomThought = getRandomThoughtFromDatabase();
				textview.setText(randomThought);
			}
		});

		thanksButton = (Button) findViewById(R.id.buttonThanks);
		thanksButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (thoughtfulDB != null){
					thoughtfulDB.execSQL("DELETE FROM " + THOUGHTFUL_TABLE_NAME);
					thoughtfulDB.close();
				}
				finish();
				System.exit(0);
			}
		});
	}

	private String getRandomThoughtFromDatabase() {
		Random random = new Random();
		int num = random.nextInt(20);
		if(num == 0){
			num = 1;
		}
		String tempResult = "";
		Cursor c = thoughtfulDB.rawQuery("SELECT thought, slno FROM "
				+ THOUGHTFUL_TABLE_NAME + " where slno =" + num, null);
		if (c != null) {
			if (c.moveToFirst()) {
				tempResult =  c.getString(c.getColumnIndex("thought"));
			}
		}
		return tempResult;
	}

	private void insertDataIntoThoughtFulDB() {
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('A life spent making mistakes is not only more honorable but more useful than a life spent doing nothing. - George Bernard Shaw',1);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Expectations are premeditated resentments. - M. Scott Peck',2);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Growing old is mandatory. Growing up is optional. - Chili Davis, California Angels ballplayer',3);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Whatever you do may seem insignificant, but it is most important that you do it. -  M. Gandhi',4);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('We cannot do great things in this life . . .We can only do small things with great love. - Mother Theresa',5);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Fanatacism consists in redoubling your efforts when you have forgotten your aim. - George Santayana',6);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('If we can think, than we can do that. Everything is possible. - Deepak Singhvi',7);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Three sentence for getting success : \n 1. Know more than others \n2. Work more than others \n3. Expect less than others. - W. Shakesphere',8);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('I am thankful to all those who said NO to me, Its Because of them I did it mysel. - Einstein',9);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('If friendship is your weakest point then you are the strongest person in the world. - A. Lincoln',10);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Opportunities are like sunrises, if you wait too long you miss them.  William Arthur',11);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('When you are in the light, everything follows you. But when you enter into the dark, even you own shadow doesn’t follow you. - Hitler',12);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('It is very easy to defeat someone, but it is very hard to win someone. - John Keats',13);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Learn from the mistakes of others... you cannot live long enough to make them all yourselves!! . - Chanakya',14);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Before you start some work, always ask yourself three questions - Why am I doing it, What the results might be and Will I be successful. Only when you think deeply and find satisfactory answers to these questions, go ahead.',15);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Once you start a working on something, do not be afraid of failure and do not abandon it. People who work sincerely are the happiest.',16);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Education is the Best Friend. An Educated Person is Respected Everywhere. Education beats the Beauty and the Youth.',17);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('A person should not be too honest. Straight trees are cut first and Honest people are screwed first.',18);");
		thoughtfulDB
				.execSQL("INSERT INTO "
						+ THOUGHTFUL_TABLE_NAME
						+ " Values ('Work like you do not need money, love like you have never been hurt, and dance like no ones watching.',19);");
		thoughtfulDB.execSQL("INSERT INTO " + THOUGHTFUL_TABLE_NAME
				+ " Values ('You can do anything, but not everything.',20);");
	}

}