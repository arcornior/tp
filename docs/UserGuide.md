---
layout: page
title: User Guide
---

Trackermon is a **desktop app** for **tracking and managing shows, optimized for use via a
Command Line Interface (CLI)** while still having the **benefits of a Graphical User Interface (GUI)**.
The app allows the user to track and remember what shows they have watched or currently watching.
They can easily look up the list of shows if they need to.

### Table of Contents
* [**Quick Start**](#quick-start)
* [**Features**](#features)
  * [Adding a show: `add`](#adding-a-show-add)
  * [Listing a show: `list`](#listing-all-shows-list)
  * [Requesting help URL: `help`](#requesting-help-url-help)
  * [Finding a show: `find`](#finding-a-show-find)
  * [Deleting a show: `delete`](#deleting-a-show-delete)
  * [Editing a show: `edit`](#editing-a-show-edit)
  * [Exiting the program: `exit`](#exiting-the-program-exit)
  * [Saving the data](#saving-the-data)
  * [Better Find [coming in v1.3]](#better-find-coming-in-v13)
* [**FAQ**](#faq)
* [**Command Summary**](#command-summary)
* [**Glossary**](#glossary)


---

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest version of **Trackermon** [here](https://github.com/AY2122S2-CS2103T-T09-3/tp/releases).

3. Move the file to the folder you want to use as the _home folder_ for **Trackermon**.

4. Double-click the file to start the app.

5. Start communicating with Trackermon using the command box.


Some example commands you can try:

* **`list`** : Lists all shows.

* **`add`** `n/ReZero s/watching t/Anime` : Adds a show **named** `ReZero` with the **tag** `Anime` to Trackermon as **watching**.

* **`delete`** `3` : Deletes the **3rd show** shown in the current list.

* **`exit`** : Exits the app.


[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `add n/<NAME>`, `<NAME>` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/<NAME> s/<STATUS> [t/<TAG>]` can be used as `n/ReZero s/completed t/Anime` or as `n/ReZero s/completed`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/<TAG>]…​` can be used as ` ` (i.e. 0 times), `t/Anime`, `t/Sitcom t/Kdrama` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/<NAME> [t/<TAG>]`, `[t/<TAG>] n/<NAME>` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/You n/Me`, only `n/Me` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list` ) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Adding a show: `add`

Adds a new show to Trackermon. Note that the name of the show can only contain alphanumeric characters.

Format: `add n/<NAME> s/<STATUS> [t/<TAG>]…​`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/All of us are dead s/completed`
* `add n/All of us are dead s/completed t/Kdrama`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Listing all shows: `list`

Shows a list of all shows in Trackermon.

Format: `list`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Requesting help URL: `help`

Shows a URL that redirects the user to Trackermon's user guide.

Format: `help`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Finding a show: `find`

**General Find**: Finds shows in Trackermon by matching the user's input across the name, status and tag parameters.

Format: `find <KEYWORD>`
* Find shows with the specified `<KEYWORD>`.
* The keyword refers to the input entered by the user after `find`.
* The keyword **can be a word or number** such as hero, S1,...
* There must be at **least one keyword** and it **must not be empty**.

Examples:
* `find shingeki` displays all the shows in the list that contain the keyword `shingeki` whether it is a name, status or tag.[put image here after ui update]
* * `find shingeki no kyojin` displays all the shows in the list that contain the keyword `shingeki` **or** `no` **or** `kyojin` whether it is a name, status or tag.[put image here after ui update]

**Precise Find** Finds shows in Trackermon by matching the user's input across name, status and tag parameters with prefixes.

Format: `find n/[NAME] s/[STATUS] t/[TAG]`
* **Within a single prefix**, the find command will execute an **AND** search across Trackermon's show list and return all shows that match all keywords that are input by the user in the specific prefix.
* **Across multiple prefixes**, the find command will execute an **AND** search across Trackermon's show list and return all shows that match all the keywords across all prefixes.
* There must be at **least one prefix field** and it **must not be empty**.

Examples:
* `find n/shingeki s/watching t/seinen` displays all the shows in the Trackermon's show list that contain **shingeki** in NAME, **watching** in STATUS, and **seinen** in TAG. [put image here after ui update]
* `find n/Shutter Island s/watching` displays all the shows in the Trackermon's show list that contains **Shutter Island** in NAME, and **watching** in STATUS. [put image here after ui update]

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Find is case-insensitive, and the order in which the keywords are entered is irrelevant. Partial words **will** be matched as well. e.g., `shing` will match `shingeki`.
</div>

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
find `find n/shingeki n/no n/2` does not mean `find n/shingeki no 2` The former will only find show names that match with **2**(as mentioned in [features](#features)) while the latter will find all show names that match **shingeki, no and 2**. This applies across the name and tag parameters.
</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Deleting a show: `delete`

Deletes the specified show from Trackermon.

Format: `delete <INDEX>`
* Deletes the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,..

Examples:
* `list` followed by `delete 2` removes 2nd show in Trackermon.
* `find ghibli` followed by `delete 1` removes 1st show in results of `find` command.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---
### Editing a show: `Edit`

Edit the specified show from Trackermon.

Format: `edit <INDEX> [n/<NAME>] [s/<STATUS>] [t/<TAG>]…​`
* Edit the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,..
* At least one field to edit must be provided
* Editing to an existing name is not allowed

Examples:
* `list` followed by `edit 2 n/Sailor Moo` edit 2nd show's name in Trackermon to Sailor Moo.
* `find ghibli` followed by `edit 1 n/Cowman s/completed t/awesome` edits 1st show in results of `find` command.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program. 

Format: `exit`

* Displays error message and exits the program after 3 seconds.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Saving the data 

Trackermon data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## FAQ

_Details coming soon ..._

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Command summary

| Action     | Format, Examples                                                                                                                                      |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/<NAME> s/<STATUS> [t/<TAG>]…​` <br> e.g., `n/ReZero s/watching t/Anime`                                                                        |
| **Delete** | `delete <INDEX>`<br> e.g., `delete 3`                                                                                                                 |
| **Edit**   | `edit <INDEX> [n/<NAME>] [s/<STATUS>] [t/<TAG>]…​` <br> e.g., `n/ReZero s/watching t/Anime`                                                           |
| **Exit**   | `exit`                                                                                                                                                |
| **Find**   | `find <KEYWORD>`<br> e.g., `find hero`<br><br>`find [n/NAME] [s/STATUS] [t/<TAG>]…​`<br>e.g., `find n/Shingeki no kyojin s/watching t/Anime t/Seinen` |
| **List**   | `list`                                                                                                                                                |


[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Glossary

| Term          | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| **Parameter** | Information passed in as part of a command with its type identified by a prefix (e.g. `NAME`)          |
| **Prefix**    | Characters used to identify the following parameter (e.g. `n/` is the prefix for the parameter `NAME`) |
| **JavaFX**    | JavaFX is a set of Java graphics libraries for creating Java GUI applications                          | 

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)
