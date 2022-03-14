---
layout: page
title: User Guide
---

Trackermon is a **desktop app** for **tracking and managing shows**, **optimized for use via a
Command Line Interface (CLI)** while still having the **benefits of a Graphical User Interface (GUI)**.
The app allows the user to track and remember what shows they have watched or currently watching.
They can easily look up the list of shows if they need to.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest version of **Trackermon** [here](https://github.com/AY2122S2-CS2103T-T09-3/tp/releases).

3. Move the file to the folder you want to use as the _home folder_ for **Trackermon**.

4. Double-click the file to start the app.

5. Start communicating with Trackermon using the command box.


Some example commands you can try:

* **`list`** : Lists all shows.

* **`add`**`n/ReZero s/watching t/Anime` : Adds a show named `ReZero` with the tag `Anime` to Trackermon as watching.

* **`delete`**`3` : Deletes the 3rd show shown in the current list.

* **`exit`** : Exits the app.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `add n/<NAME>`, `<NAME>` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/<NAME> s/<STATUS> [t/<TAG>]` can be used as `n/ReZero t/Anime` or as `n/ReZero`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/<TAG>]…​` can be used as ` ` (i.e. 0 times), `t/Anime`, `t/Sitcom t/Kdrama` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/<NAME> [t/<TAG>]`, `[t/<TAG>] n/<NAME>` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/You n/Me`, only `n/Me` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list` ) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>


### Adding a show: `add`

Adds a new show to Trackermon. Note that the name of the show can only contain alphanumeric characters.

Format: `add n/<NAME> [s/<STATUS>] [t/<TAG>]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/All of us are dead t/Kdrama`
* `add n/All of us are dead s/completed t/Kdrama`
* `add n/All of us are dead`

### Listing all shows : `list`

Shows a list of all shows in Trackermon.

Format: `list`

### Requesting help URL : `help`

Shows a URL that redirects the user to Trackermon's user guide.

Format: `help`

### Finding a show : `find`

Finds the specified show in Trackermon.

Format: `find <KEYWORD>`
* Finds the show with the specified `<KEYWORD>`.
* The keyword refers to the input entered by the user after `find`.
* The keyword **can be a word or number** such as hero, S1,..

Examples:
* `find` followed by `attack` displays all the shows in the list that contain the keyword `attack`.
* `find attack` followed by `delete 1` removes 1st show in results of `find` command.

### Deleting a show : `delete`

Deletes the specified show from Trackermon.

Format: `delete <INDEX>`
* Deletes the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,..

Examples:
* `list` followed by `delete 2` removes 2nd show in Trackermon.
* `find ghibli` followed by `delete 1` removes 1st show in results of `find` command.

### Exiting the program : `exit`

Exits the program. 

Format: `exit`

* Displays error message and exits the program after 3 seconds.

### Saving the data 

Trackermon data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file `[coming in v1.3]`

Trackermon data are saved as a JSON file `[JAR file location]/data/trackermon.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Trackermon will discard all data and start with an empty data file at the next run.
</div>

### Better Search `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/<NAME> [s/<STATUS>] [t/<TAG>]…​` <br> e.g., `n/ReZero s/watching t/Anime`
**Delete** | `delete <INDEX>`<br> e.g., `delete 3`
**List** | `list`
**Exit** | `exit`
