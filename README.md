# Project 2 - *Twitter recreation*

**Twitter recreation** is an android app that allows a user to view their Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **15** hours spent in total (this is omiting the some progress that was not committed and was lost)

## User Stories

The following **required** functionality is completed:

* [Y] User can **sign in to Twitter** using OAuth login
* [Y] User can **view tweets from their home timeline**
  * [Y] User is displayed the username, name, and body for each tweet
  * [Y] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [Y] User can ***log out of the application** by tapping on a logout button
* [Y] User can **compose and post a new tweet**
  * [Y] User can click a “Compose” icon in the Action Bar on the top right
  * [Y] User can then enter a new tweet and post this to Twitter
  * [Y] User is taken back to home timeline with **new tweet visible** in timeline
  * [Y] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
* [Y] User can **see a counter that displays the total number of characters remaining for tweet** that also updates the count as the user types input on the Compose tweet page
* [Y] User can **pull down to refresh tweets timeline**
* [Y] User can **see embedded image media within a tweet** on list or detail view.

The following **optional** features are implemented:

* [Y] User is using **"Twitter branded" colors and styles**
* [ ] User sees an **indeterminate progress indicator** when any background or network task is happening
* [Y] User can **select "reply" from home timeline to respond to a tweet**
  * [Y] User that wrote the original tweet is **automatically "@" replied in compose**
* [ ] User can tap a tweet to **open a detailed tweet view**
  * [Y] User can **take favorite (and unfavorite) or retweet** actions on a tweet
* [ ] User can view more tweets as they scroll with infinite pagination
* [ ] Compose tweet functionality is built using modal overlay
* [ ] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [Y] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.org/android/Drawables#vector-drawables) where appropriate.
* [ ] User can view following / followers list through any profile they view.
* [ ] Use the View Binding library to reduce view boilerplate.
* [ ] On the Twitter timeline, apply scrolling effects such as [hiding/showing the toolbar](http://guides.codepath.org/android/Using-the-App-ToolBar#reacting-to-scroll) by implementing [CoordinatorLayout](http://guides.codepath.org/android/Handling-Scrolls-with-CoordinatorLayout#responding-to-scroll-events).
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in offline mode.

The following **additional** features are implemented:

* [Y] Navigation bar is in place!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Screen_Recording_2022-06-10_at_4_23_04_PM_copy_AdobeExpress-2](https://user-images.githubusercontent.com/96102973/173163222-09a12050-d469-4da5-bfba-cddeacf72765.gif)


## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

* [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
* [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
