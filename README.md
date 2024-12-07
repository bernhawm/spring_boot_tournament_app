# spring_boot_tournament_app
 
Springboot Tournament App
Overview: While walking I thought about and wanted to try coming up with a tournament app, similar to those that are used in TCG and other various tournaments, the focus on tournaments for the app that I am building is primarily with the thought around how Magic: The Gathering is competitively played. 

The notes below are the initial ideas and thought process around creating such an app that I had while walking, I didn’t add or polish anything to keep a sense of seeing what I may have missed out on further on, and to see where I came from, and see what I choose to do differently and why later. That being said, I am going to polish it a tiny bit, cause walking and typing with a phone can lead to many issues and lack of idea around what is being said where. 


Goal: Create and manages tournament

Events/ Tournaments have the following 
matches rounds (int), player ids (varchar or int), standings (points total?, math can be done to get the players score how they see it )  , date (date time) , Format(varchar) , prizes (varchar) , ids int/varchar

Rounds have the following 
 matches (array of two players) and standings (Array of all players ordered by score), start time, end time , tournament  (owner I’d)

*Maybe these are players in scope of tourney 

Players have the following
 Player ID (ID) Decks (id), Records or points (int),  Tournament (id)
*I originally had Tournaments but that gets touched on later down where it may be better

Decks have the following
Deck ID (Id), Name (Varchar), Cards (an array or JSON list) , sideboards  ( an array or JSON list)
Tournament optional array or would be a list – this can depend on if I want to show the history of a deck being played but I don’t think so since I think the likelihood and usefulness of showing this history compared to history of events under player.


Matches have the following
Match ID (id), Outcomes/ Games (this is data that will be fed into rounds / tournament, game wins can be entered, winner is decided on who has 2/3 wins), Players (array of two players ), names ,  table number (int – location based on standings), 

Formats have the following
 Restrictions (an array or object of cards that can’t be in decks in certain formats) 
-	This may be something that I don’t really focus on since this is on the deckbuilding portion. 

Users have the following
User (ID), First Name (varchar), last name (varchar)  , email (varchar)  , password  (varchar), User Type or Status (Denotes purpose and what screens they will see, varchar)

-players may become users with user type denoting a store/ tournament account vs normal user who is only a player when in a tournament 

Extra functionality 
Change record 

Tournament logic 
Draws 
Points 
Players have infractions 
Optional plus 5 to round time 

Base functionality 
Create a tournament with the above and does below 
•	add players to tournament 
•	Drop a player from tournament 
•	start round - will need logic 
•	    Creates matches 
•	Allow players to submit their match or have Tournament owner do it
•	When all matches submitted end round and show standings
•	Start a new round on to action 
Create player 
Screens Draft

Login screen 
Player home screen 
•	Settings button/ screen
•	Decks button/ screen 
•	create deck button / pop up 
•	Tournaments deck / screen 
•	Join tournament button maybe screen 


Tournament org Home Screen
•	Create tournaments  button screen  - may under a central tournaments button 
•	tourney details above 
•	If tournament is active -active tournament button to bring them to controller
•	Add player (manual id enter, possible user lookup) 
•	Approve players who entered code 
•	Shows tourney code and players 
•	Next round and or start tourney button 
•	Outstanding matches? 

