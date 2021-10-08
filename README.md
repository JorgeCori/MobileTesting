# MobileTesting
Testing for mobile apps, both in iOS and Android, can be done through Appium. This is very similar to Selenium but there ARE a couple things here and there that make Appium much more harder to work with compairing it with Selenium. 
This repo will only cover the installation, main commands and utilities that can be used with Appium. Everything in this repo is done under a TestNG framework using Maven projects, so if you want to know a little bit more about that before going head first into Appium, checkout my other repo: https://github.com/JorgeCori/WebTesting
That one covers all theory used in TestNG and POM. 

## Installing Appium 
Okay, the problems start here: depending on your Java version, certain things are compatible and some others are not. 
For Java versions 15 (or 14, don't quite remember) or later, the tool you used to inspect the apps stopped working, so I'll try to cover the installation for both but be aware that it is quite voltaitle to work with Appium and this may change. 

### Installing for Java 15/14 or prior
1. Installing JDK: Head over to https://www.oracle.com/java/technologies/downloads/#jdk17-windows and download the JDK that corresponds to your OS and specifications. Follow the wizard normally to install Java.
2. Install AndroidStudio: Go to https://developer.android.com/studio?gclid=CjwKCAjw2P-KBhByEiwADBYWCr8NF9bmUkQqD0YnU1JqRlEiiJSvMMqI6zcXyOw7oio1oXuQ9xjyGhoCatMQAvD_BwE&gclsrc=aw.ds and download the latest version for AndroidStudio. Follow the wizard normally with the standard settings. 
3. Install Node: Go to https://nodejs.org/es/download/ and install Node using the standard configuration in the wizard
4. Adding the JAVA_HOME environment variable: Once Java is installed, head over to the location in which it's installed. Copy the path (it should look something like *C:/Program Files/Java/jdk-x.x.x*) and use the Windows search bar to look for *Environment variables*. In the new window, under *System variables*, click on *New..." and set the *Variable name* to **JAVA_HOME* and the *Variable value* to the full path you just copied. 
5. (OPTIONAL) Make the tools folder visible in the sdk path: Sometimes the installation of AndroidStudio doesn't make visible the tools folder in its installation folder, if that's your case, no problem. Open AndroidStudio and create a New Project (just to get to the landing page, it actually doesn't matter what you choose). Once everything is loaded, in the **Menu bar**, click on **Tools** and then, in the dropdown menu, click on **SDK Manager**. If this doesn't appear, then probably the build for the project hasn't finished. Give it some time and it will appear, then follow these steps: 
	1. Click on SDK Tools.
	2. Uncheck the Hide Obsolete Packages. (if it's unchecked by default it's alrgiht, go to next step)
	3. Check the Android SDK Tools (Obsolete). (if it's already checked, you're all set up)
	4. Click on Apply: It will take some time to download if you don't have it, but after that the tools folder should be visible
6. Add AndroidStudio Tools to Path: Go to your installation folder (Usually in *C:/Users/UserName/AppData/Local/Android/sdk*) and enter the **tools* folder and then the **bin** folder. Copy that entire path and go to your Environment Variables. Let's add a couple of them:
	* In your already existent environment variable of the name **Path** : Double click, click on **New...**, paste the path to the **bin folder**
	* Add another one to **Path**: C:/Users/UserName/AppData/Local/Android/sdk/tools 
7. Add NodeJS to Path: Copy the path to nodejs installation folder (Usually in *C:/Program Files/nodejs*) and then add a new System variable with *Variable Name* as **NODE_HOME** and *Variable Value* as the path for nodejs installation. 
8. Add npm to Path: Copy the path to npm (deeper in the nodejs folder, usually in *C:/Program Files/nodejs/node_modules/npm/bin*) and add it to the **Path** variable
9. Install Appium Server: Go to the Command Prompt and enter 
```
npm install -g appium
```

To start the appium server in the future just type *appium* on the command line. 

If you get errors and warnings when running this command, then you can do two things: 
1. Check that you've entered the System Variables correctly and ALL of them. 
2. Restart your computer. Sometimes the Path variables don't seem reflected until the system is rebooted. 

By this point you should have both Appium Server Installed and UIAutomatorView2 tool that allows to inspect apps. If your UIAUtomatorView2 doesn't work, then your Java version is probably not compatible.
Feel free to change your Java Version or use the installation steps for prior versions( I recommend the latter). 

### Installing for Java 15/14 and later
Well, I shouldn't say later. The method I'm about to show was successful for me in Java 16, however, I don't know if it will continue to be in later versions.
This method is way shorter and more effective, in my opinion.

1. Installing JDK: Head over to https://www.oracle.com/java/technologies/downloads/#jdk17-windows and download the JDK that corresponds to your OS and specifications. Follow the wizard normally to install Java.
2. Install AndroidStudio: Go to https://developer.android.com/studio?gclid=CjwKCAjw2P-KBhByEiwADBYWCr8NF9bmUkQqD0YnU1JqRlEiiJSvMMqI6zcXyOw7oio1oXuQ9xjyGhoCatMQAvD_BwE&gclsrc=aw.ds and download the latest version for AndroidStudio. Follow the wizard normally with the standard settings. 
3. Install Appium for desktop: Go to https://github.com/appium/appium-desktop/releases/tag/v1.21.0-1 and install Appium with the appropriate file. What's this? It's an all-in-one package: it starts the appium server and also serves as an inspector for the app. 
4. Adding the JAVA_HOME environment variable: Once Java is installed, head over to the location in which it's installed. Copy the path (it should look something like *C:/Program Files/Java/jdk-x.x.x*) and use the Windows search bar to look for *Environment variables*. In the new window, under *System variables*, click on *New..." and set the *Variable name* to **JAVA_HOME* and the *Variable value* to the full path you just copied. 
5. Add AndroidStudio Tools to Path:
	* Add another a new variable to **Path**: C:/Users/UserName/AppData/Local/Android/sdk/tools

**NOTE**: I know there's another version now, 1.22. However I have not tested this one and since this one separates the app inspector and the appium client, it could lead to more trouble. 
	

### Configuration of Android Virtual Device (AVD)
1. Open up AndroidStudio
2. Once it's fully loaded, got to **Tools** and then click **AVD**
3. Create virtual device: A few screen in which you select the model, Android version and specs will pop up. Choose accordingly to your needs. The **AVD Name** is important, youl'll need it in order to emulate in Java. 

**NOTE**: When selecting your Android version, be careful of which one you choose, since not all of them support Google Play Services. If you see the icon of the Play Store in a certain version, then you have the Google Play Services. If the app you're testing uses location or something like that, then you'll need this in order to successfully execute your tests.

4. Finish your set up and you should be able to run your virtual device. 

**NOTE**: If you chose a device with Google Play Services then you'll need to log into a Google Account when booting up the device for the first time. Also, it will probably need to update the services from within the Google Play Store. 

Once you have a device up and running, you don't need to have AndroidStudio open, so you can safely close it while you're simulating your device. 

### App inspecting 

You should now be ready to inspect apps that are currently running in your AVD. Let's check again how to do it depending on what you installed

#### With UIAutomator (Java prior to 14/15)
Once you have an app running in your avd, go to the Command Prompt and enter 
´´´
uiautomatorview2
´´´
And it should start the inspector that comes with AndroidStudio. If an error comes up, it probably is because it's not compatible with your Java Version. Downgrade or follow the steps for using the Appium Desktop Client. 

#### With Appium Desktop Client 
Setup your Appium Desktop Client and inspector:
	1. Open up Appium (it takes a while) 
	2. You should see a Host and Server inputs already filled. Just click on the blue button *Start Server v1.21.0*
	3. Now that you see a console-looking window, click on the magnifying glass icon to open the inspector. 
	4. In the new window you'll get to enter the Desired Capabilities. This should be filled using the information of your Android Virtual Device. The following are the capabilites that allowed to use the inspector
		* deviceName text NameOfYourAVDHere 
		* app filepath Full Path to the App
		* automationName text Appium
		* platformName text Android
		* platformVersion text VersionHere(11.0) 

If you can inspect your app with either one of the previous methods, you're good to go. 

### Java IDE, Maven Project and Dependencies
You can use your IDE of preference to work with Appium, as long as you're able to create Maven projects, you're golden. 
The dependencies you'll need to use are the following: 

´´´
<!-- All dependencies come from maven repositories-->
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>7.5.1</version>
</dependency>
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
</dependency>
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.4.0</version>
    <scope>test</scope> 
</dependency>
´´´

## Testing with Appium 
Appium is written on top of Selenium, so if you understand Selenium and TestNG you should get the hang of this pretty quickly. 
Check my other repo for full notes on TestNG. Before I start with actual class in a way that the objects created are actually null and, thus, they're not found.
I haven't found the solution to this, although it's probably by playing around with the versions of appium and selenium, but that's another story. 
Having said that, the examples you'll find in this repo will not implement POM because I just couldn't make it work. I hope you can, because, you know, it's the best way to write code for testing. 

### Your CapabilitesBuilder Class
Just like in Selenium, we have to create a driver with certain capabilites so that it's able to automate stuff. We'll create a parent class *CapabilitiesBuilder.java* that will be used for the upcoming tests. 

