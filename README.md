# Pre-work - **E-todo**

**E-todo** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Akash Sethiya**

Time spent: **1** week spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [X] Add support for completion due dates for todo items (and display within listview item)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [X] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [X] Search functionality of Todo items is implemented

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/2JNbb1m.gif' title='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** Working on development of Android app is always fun. The best thing about android app development is that you got to learn so much about effective user experience (UX) and interaction with mobile hardware with general programming. So, overall experience till now is good so far.

* Android layouts gives the platform to design app in a effective manner with built-in layout behaviors while in previous methods of user interface, developer have to design it from scratch.
* Layouts are easy to use and implement than traditional user interface.
* With layouts, developers can maintain consistency easily throughout the application while UI designs require extra efforts to maintain consistency..

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** `ArrayAdapter` is useful for providing and returning view for an AdapterView and for collections of data objects respectively.
In **E-todo**, I use ArrayAdapter to provide collection objects to ListView to populate elements on UI.

Adapter is like `boss` for android view elements. for example, if something is added or removed or update in data objects of views, they have to notify adapter. It also requires to display items on UI.

`convertView` is an parameter of getView() method, which is a View object. When Adapter populates the items in a list view, each element has been displayed as View object, so at anytime if developer have to use the old view of the item, they can recycle the old view by using `convertView`. It also  increases the performance of Adapter..

## Notes

* Face issues on designing the UI of the application.

## References
* https://developer.android.com/index.html

## License

    Copyright 2017 Akash Sethiya

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
