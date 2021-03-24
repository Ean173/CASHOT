# 3443-002-Team-Project
# Payhem by CASHOT

How to clone the GitHub repository on the UTSA VDI (and ensure everything is compatible):\
1: Log Into UTSA VDI\
2: Open the Eclipse folder and open "eclipse.exe"\
\* use default workspace\
3: Click on "Window" then click "Preferences"\
4: Expand "Java" and click on "Installed JREs"\
5: Click "Add" then select "Standard VM" then click "Next"\
6: Click "Directory" and navigate to "C:\Program Files\Java"\
7: Ensure there is "jdk1.8.0_XXX". If not, install a new JDK with this number.\
8: Open "jdk1.8.0_XXX" and single click "jre"\
9: Click "Select folder" and menu should auto-populate.\
10: Click "Finish" and select "C:Program Files\Java\jdk1.8.0_XXX\jre"\
11: click "Apply and Close"\
12: Now, go to "Help" and expand\
13: Open "Install New Software"\
14: Press "Add" then in "Name", type 'Mars' and in "Location" type 'http://download.eclipse.org/releases/mars' \
15: Press "Add". Make sure "Work with" is in Mars\
16: In "type filter text" type 'fx'\
17: Select "e(fx)clipse - IDE" and click "Next"\
18: Follow directions until Eclipse needs to restart. Restart.\
19: An error might come up, "You are not running your eclipse instance with Java8. The JavaFX tooling is disabled because of this." This is fine.\
20: Go to "File" then open "Import"\
21: Expand "Git" and select "Projects from Git"\
22: Select and open "Clone URI"\
23: Paste in 'https://github.com/Ean173/CASHOT.git' to URI\
\* this link also works for opening the GitHub website.\
24: Click "Next". Select the "____" branch. Click "Next" three times.\
25: Select "CASHOT" and then "Finish".\
26: Click on "Window" then " hover over "Perspective" then "Open Perspective". Click "Other"\
27: Click "Java" and then "Open"\
28: On very right-hand side, click "Restore"\
29: Click on "CASHOT" folder then click "Run". Project should run correctly.