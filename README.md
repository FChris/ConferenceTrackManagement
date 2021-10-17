# Conference Track Management Tool

The Conference Track Management (CTM) tool organizes list of talks into a set of tracks.

## High-Level Overview

- A conference consists of multiple tracks
- Each track consists of a morning and an afternoon session
- Sessions contain a list of talks
- Afternoon sessions start at 1 pm and end with a networking event
- The networking event starts between 4:00 and 5:00

### Algorithm

The internal algorithm to assign talks to tracks loosely corresponds to the the First-fit-decreasing algorithm. 

The algorithm works as follows:

1. Sort the list of talks in descending order (longest first)
2. Create an empty track
3. Place first remaining item into the first track it fits
   1. If the talk does not fit into any track, create a new track and add the talk to it
   2. If a new track was created, add it to the list of tracks
4. Repeat Step 3 until every item is assigned
5. Return the list of tracks

### Input

An input for the tool may look like as follows:

```
Go for Java Developers 90min
Why you less brackets are great lightning
Concurrency in Go 90min
Best of funny JavaDoc comments lightning
How to find interesting GitHub content lightning
Mutex in Java, Python and Go. A comparison 45min
Go Channel internals 60min
A look into the JavaVM 60min
Compiling Python: Ten Reasons to do it 60min
```

### Output

The tool outputs a schedule which may look as follows:

```
Track 1:
09:00AM Go for Java Developers 90min
10:30AM Concurrency in Go 90min
12:00PM Lunch
01:00PM Go Channel internals 60min
02:00PM A look into the JavaVM 60min
03:00PM Compiling Python: Ten Reasons to do it 60min
04:00PM Mutex in Java, Python and Go. A comparison 45min
04:45PM Why you less brackets are great lightning
04:50PM Best of funny JavaDoc comments lightning
04:55PM How to find interesting GitHub content lightning
05:00PM Networking
```

## Assumptions

The tool makes to following assumptions about input and goal of the schedule generation.

- Talk lengths are given in minutes or as lightning (5 min)
- Talk lengths do not exceed 240min
- Talk titles do not contain the word lightning or numbers
- Sessions do not have to be full
- Lunch always starts at 12pm
- Networking starts at either 4pm or 5pm
  - if the last talk ends at 3:59pm or before networking event starts at 4pm
  - if the last talk ends at 4:01pm or later the networking event will always start at 5pm.
- The primary goal is to assign every talk to a track, not to have a fixed number of tracks that are completely filled
  - ideally all tracks can be filled
  - if tracks cannot be filled there may be a gap between the end of the last talk and the start of the session end event (Lunch/Networking)

# Building

##Windows:

```
.\gradlew.bat shadowJar
```

##Linux:

```
  ./gradlew shadowJar
```

Make sure `gradlew` is executable.
To make it executable run: `chmod a+x gradlew`

##Mac:

No build instructions yet, due to a lack of hardware to test. 
Patches with Build instructions are welcome.

# Usage

To run the Conference Track Management Tool use the following command with one of the possible commands

```
java -jar .\build\libs\ConferenceTrackManagement-1.0-all.jar
```

The Conference Track Management Tool allows three commands:

```
 -f,--file <arg>    File input with a list of talks
 -h,--help          Display the help message
 -i,--interactive   Interactive input of talks line by line

```

The `file` and `interactive` command may not be used at the same time. If both are chosen
only the `file` command will be run.

## Reading from a file

The CTM tool can read a list of talks from a file. The file may contain only one talk description 
per line. Empty lines are not allowed. 

To read from a file called `talks.txt` call the following command:

``java -jar .\build\libs\ConferenceTrackManagement-1.0-all.jar -f talks.txt``

The result will be printed to the console afterwards.

## Reading from the command line

The CTM tool allows the user to input talk descriptions on the console.
One talk description may be put in per line. 
To finish the input and have the results printed to the console an empty line has to be added.

To read from the console you may use the following command:

``java -jar .\build\libs\ConferenceTrackManagement-1.0-all.jar -i``

This will open the following prompt, after which you may put in talk descriptions.

```
> Please write one talk per line.
> Two finish the input enter an empty line.
> 
```
