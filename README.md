PomodoroBox
===========

    Motto #1: "All your pomodoros are belong to box"
    Motto #2: "I know what I did last summer"


This repo contains a script that integrates with Pomodoro Desktop and logs all your pomodoros in Dropbox. 
It also contains an Android application that does the same thing, but that is still work in progress. 

# Installation for OS X

You have to follow these steps for each of your Macs on which you want to install Pomodoro Box.

1. Download and install [Pomodoro Desktop](http://mac.majorgeeks.com/files/details/pomodoro_desktop.html) by Ugo Landini

2. Open the Preferences of Pomodoro Desktop. Go to the Script tab and set the following script for the End event:

    ```
    do shell script "echo  `date '+%m/%d/%Y %H:%M'`, $pomodoroName >> ~/Dropbox/Apps/PomodoroBox/box.txt" 
    ```

3. Execute the following two commands one after the other in Terminal:

    ```
    wget -N -P ~/Dropbox/Apps/PomodoroBox/ https://raw.github.com/mircealungu/PomodoroBox/master/osx/box.sh
    ```
    
    ```
    chmod +x ~/Dropbox/Apps/PomodoroBox/box.sh
    ```

4. Create an alias to the script by adding the following line in your .profile. I like to call it `box` but you can call your command any way you like:

    ```
    alias box='~/Dropbox/Apps/PomodoroBox/box.sh'
    ```
    



# Testing your installation

Now you should be able to start using Pomodoro Desktop and every pomodoro that you finish will be logged into your Dropbox. 
Or if you already spent one full pomodoro during the instalation and you want to log it, you can do that with:

    box done "Installing PomodoroBox, self-improvement"

Once you've done a few you can see them by running:

    box list
    
For more commands, see the following section.


# Using Pomodoro Box

## Statistics
To see all the pomodoros you've ever done, use 
   
    ```box list```

To see how many pomodoros you've spent on a given project use
   
    ```box stats```
   
To see how many pomodoros and on which projects you've used today use
   
    ```box today```
    
## Logging
If you want to edit some of the pomodoros you've already logged, use
   
    ```box ed```
    
If you want to add a pomodoro that you just finished and you forgot to log with Pomodoro Desktop, use
    
    ```box done "name of task, project"```

    

