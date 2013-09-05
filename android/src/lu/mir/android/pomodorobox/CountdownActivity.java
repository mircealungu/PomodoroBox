package lu.mir.android.pomodorobox;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings.Secure;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException.NotFound;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.dropbox.sync.android.DbxPath.InvalidPathException;

/**
 * The TextToSpeech code is from:
 * http://android-developers.blogspot.ch/2009/09/introduction
 * -to-text-to-speech-in.html
 * 
 * @author mircea
 * 
 */

public class CountdownActivity extends Activity implements OnInitListener {

	private TextToSpeech tts;
	private String message;
	private static long SECOND = 1000;
	private long COUNTDOWN_TIME;

	protected void updateTimer(long millisUntilFinished) {
		long minsToFinish = millisUntilFinished / 1000 / 60;
		long secs = millisUntilFinished / 1000 % 60;

		TextView counterView = (TextView) findViewById(R.id.counter);
		counterView.setText(minsToFinish + ":" + secs);
	}

	protected void speak(String text) {
		tts.setLanguage(Locale.US);
		tts.speak(text, TextToSpeech.QUEUE_ADD, null);
	}

	protected void logPomodoroToDropbox() throws InvalidPathException,
			IOException {
		String android_id = Secure.getString(getApplicationContext()
				.getContentResolver(), Secure.ANDROID_ID);
		DbxPath logFileName = new DbxPath("box.txt");

		DbxAccountManager mDbxAcctMgr = DbxAccountManager.getInstance(
				getApplicationContext(), MainActivity.DBX_APP_KEY, MainActivity.DBX_APP_SECRET);
		DbxFileSystem dbxFs;

		DbxFile logFile;
		dbxFs = DbxFileSystem.forAccount(mDbxAcctMgr.getLinkedAccount());

		try {
			logFile = dbxFs.open(logFileName);
		} catch (NotFound e) {
			logFile = dbxFs.create(logFileName);
		}

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		logFile.appendString(sdf.format(date) + ", " + message + "\n");
		logFile.close();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the text view as the activity layout
		setContentView(R.layout.activity_countdown);

		Intent intent = getIntent();
		message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		COUNTDOWN_TIME = SECOND * intent.getLongExtra(MainActivity.EXTRA_TIME_IN_SECONDS, 7);

		// Create the text view
		TextView activityView = (TextView) findViewById(R.id.activity);
		activityView.setText(message);

		tts = new TextToSpeech(this, this);

		new CountDownTimer(COUNTDOWN_TIME, SECOND) {
			public void onTick(long millisUntilFinished) {
				updateTimer(millisUntilFinished);
			}

			public void onFinish() {
				updateTimer(0);
				speak("Well done!");
				try {
					logPomodoroToDropbox();
				} catch (InvalidPathException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub

	}

}
