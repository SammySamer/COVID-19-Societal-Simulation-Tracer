# Covid-Tracker-UDP
 Covid Tracker extension from Java assignment 4 to include a UDP connection to produce a log of the nodes' movements.


# Covid-Tracker Assumptions

- "ID of User" means the ID of the thread. 
- "You need to capture the hostname of IP of the log machine" means the IP of the server, this is printed out at the beginning of "tracer.log" as part of the title.
- We included "No_COVID" that means not infected, "COVID" that means they already had COVID, and "True/Infected" that means they got the virus from someone and got infected. 


# Assignment Description

You are to augment the functionality of assignment 4 as follows: You need to create a log of the motion of people in the simulation. Every time a person moves to a new x and y coordinate, you need to log this motion in a file called “tracer.log”.The motion is as follows, with a flag at the end of each log line that is either true (flagged as infected), or empty.The log file must be on another machine on the network (could be your same machine for illustration purposes (localhost)). You need tocapture the hostname or IP of the log machine somewhere in your program.<ID of user, x, y, timestamp, true?>