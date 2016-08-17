# BonSketch-Computer Version

Copyright (c) | 2016 | Shuang Wu | University of Waterloo 

1.OS: Windows 7
2.JDK: JAVA SE 1.8
3.IDE: ECLIPSE MARS

call make to compile
call make clean to clean .class
call make run to run with default values
call java snake {fps} {speed} to run with customized values


1)Description:
Therefore sound effects!!! so Turn up your volume before playing!
It is a snake game which the user enters its own fps and snakespeed
1 is the slowest, 10 is the fastest.
Once the game begins, user uses arrow key to control the direction of the snake.
The snake needs to eat the fruit in order to grow longer
but it has to try its best avoid its own body or the four walls.
I provided the grid in order to help the user see when to turn easily.
The score increments by 1 every time the snake eats a fruit.
I set an array for the snakespeed,so the slowest snake can go (level 1)is 500 with timer
and the fastest (level 10) is 80 with the timer. The speed array is in snake
Sometimes there is a little margin on the right side and the bottom side of my window.
but on any other computers it all disappeared. So I really don't know why.


snake.java has the main class.
Splashscreen.java has everything about the splashscreen
SnakeBody.java has everything about the snake
Fruit.java has everyting about the fruit

	   
4)Sound and Picture Credit:
1. Grapefruit.png: https://cdn4.iconfinder.com/data/icons/Limon_iContainer/128/Grapefruit.png
2. watermelonz.png: https://images.encyclopediadramatica.se/d/d9/Watermelonz.png
3. watermelon.png: http://findicons.com/files/icons/1341/summertime_snacks/128/watermelon.png
4. kiwi.png: http://icons.iconseeker.com/png/fullsize/new-zealand-icons/kiwi-fruit.png
5. Pear.png: http://cdn.iconsmash.com/Content/icons/Paradise_Fruits_Icon_Set_by_artbees/iconpreviews/128/Pear.png
6. cherry2.png: http://www.veryicon.com/icon/png/System/World%20of%20Aqua%204/Cherry.png
7. apple.png: http://icons.iconarchive.com/icons/bingxueling/fruit-vegetables/128/apple-red-icon.png
8. chopping.jpg: https://s3-eu-west-1.amazonaws.com/content.cleanipedia.com/wp-content/uploads/2013/08/N19-2_CleanChoppingBoard-575x375.jpg
9. skin.png: http://www.officialpsds.com/images/thumbs/snake-skin-psd79929.png
10.snakeheadlogo: http://www.carlogos.org/uploads/allimg/130122/Dodge-srt-viper4.jpg
11."GAMEOVER"sound :http://www.freesound.org/people/Rocotilos/sounds/178876/
12."POWERUP" sound: http://www.freesound.org/people/GameAudio/sounds/220173/