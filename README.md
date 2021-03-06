PomodoroBox
===========

    Motto #1: "All your pomodoros are belong to PomodoroBox"
    Motto #2: "PomodoroBox knows what you did last summer"

The four greatest features of PomodoroBox are:
- Time and log pomodoros from all your *Mac*, and *Android* devices
- *Query and visualize* your past pomodoros
- Data is stored in a *plain text file in your Dropbox*. No proprietary format. All your pomodoros are belong to you.
- It integrates nicely with the excellent Pomodoro Desktop for Mac app by Ugo Landini

## Installation for OS X

You have to follow these steps for each of your Macs on which you want to install Pomodoro Box.

1. Download and install [Pomodoro Desktop](http://mac.softpedia.com/get/Business/Pomodoro-Desktop.shtml) by Ugo Landini

2. Open the Preferences of Pomodoro Desktop. Go to the Script tab and paste the following script for the End event (I used the mouse; the keyboard shortcut did not work):

    ```
    do shell script "echo  `date '+%Y/%m/%d %H:%M'`, $pomodoroName >> ~/Dropbox/Apps/PomodoroBox/box.txt" 
    ```

3. Execute the following command in the Terminal: 

    ```
    curl https://raw.github.com/mircealungu/Pomodoro-Box/master/get | bash
    ```
    
The commands download the script, make it executable, and add an alias named `pom` in the .profile script. Now you can use the `pom` command.

## Installation for Android
The app is still in the works, so it is not in the Android Market yet. You can clone this repo, install the Android SDK, and then deploy the app on your device.

## Testing your installation

Now you should be able to start using Pomodoro Desktop and every pomodoro that you finish will be logged into your Dropbox. 
Or if you already spent one full pomodoro during the instalation and you want to log it, you can do that with:

    pom done "Installing PomodoroBox, self-improvement"

Once you've done a few you can see them by running:

    pom list
    
For more commands, see the following section.


## Using Pomodoro Box

### Statistics
To see all the pomodoros you've ever done
   
    pom list

To see how did you spend your time on the different projects
   
    pom stats
   
To see how did you spend your time on the different projects today
   
    pom today
    
### Logging
If you want to edit some of the pomodoros you've already logged, use
   
    pom ed
    
If you want to add a pomodoro that you just finished and you forgot to log with Pomodoro Desktop, use
    
    pom done "Installed PomodoroBox, happiness"




    

