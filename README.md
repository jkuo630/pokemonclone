# Jason's CPSC210 Project 

## 1. What will the application do?
This application, named *Jason mon* is my personal take on the
popular video game *Pokemon*. It will be an interactive game
that allows users to capture and collect creatures. Each pokemon has a set
of attack, and hit points. **The user is a trainer, and they will have a party of *6* pokemon
to use.** When first loaded in, the trainer will be prompted to either load an old user
or start a new one. If they have a previous trainer, their 
previous creatures will load in. If not, they will be
prompted to select their first creature, or pokemon, 
beginning their journey to become the very best. 


The application will have *6* main features:

- **1. Catch**

If the wild pokemon is caught, the pokemon is added to the trainer's collection Pokemon. 


- **2. View Party**

This option in the menu will list the user's *6* selected pokemon that
are their favourite.

- **3. View All Pokemon**

This option in the menu will list all the user's pokemon. 

- **4. Add to Party**

This option in the menu will allow the user to swap pokemon from their
collection, to their party, and vice versa. 

- **5. Save & Quit**

This option in the menu will allow the user to save their Pokemon
and their overall progress (pokemon level, hp, etc). 

## 2. Who will use it?
*Jason mon* will be used by gamers who are interested in pokemon or even 
anyone looking for a fun way to pass time. It is a primarily simple and 
straightforward game, so it will be suitable for all ages.

## 3. Why is this project of interest of you? 
This project is of interest to me because I played a lot of video games
growing up. In particular, Pokemon was something that stuck with me 
for a very long time. I've made some of my closest friends from playing 
video games and it ultimately allows people to bond in a fun way. In the future,
I want to explore game development and this project will be my first step into 
that field. I want to be able to learn how to design and create enjoyable games
so I can help other people bond and connect as well in the future, just like how I did. 

## User Stories
- As a user, I want to be able to obtain my first pokemon and add it to a list (collection) of pokemon.
- As a user, I want to be able to obtain more pokemon and continue adding it to my collection.
- As a user, I want to be able to select and view my list (party or collection) of Pokemon.
- As a user, I want to be able to move Pokémon from Collection to Party. 
- As a user, I want to be able to save my Pokémon and load them at will.

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by pressing the catch button on 
the menu panel. 
- You can generate the second required action related to adding Xs to a Y by clicking on view collection
and selecting the pokemon you want to see for their stats. You can also switch pokemons from your collection
to your party. 
- You can locate my visual component by clicking on the catch button in the menu panel.
- You can save the state of my application by pressing save and quit in the menu panel. 
- You can reload the state of my application by selecting load saved file at the first panel.

# Phase 4: Task 2
Tue Aug 08 14:29:59 PDT 2023
Bulbasaur has been added to the collection!
Tue Aug 08 14:30:00 PDT 2023
Pokemon collection has been viewed.
Tue Aug 08 14:30:00 PDT 2023
Pokemon collection has been viewed.
Tue Aug 08 14:30:04 PDT 2023
Bulbasaur has been viewed.
Tue Aug 08 14:30:12 PDT 2023
Dragonite has been added to the collection!
Tue Aug 08 14:30:14 PDT 2023
Pokemon party has been viewed.
Tue Aug 08 14:30:14 PDT 2023
Pokemon party has been viewed.
Tue Aug 08 14:30:16 PDT 2023
Pokemon collection has been viewed.
Tue Aug 08 14:30:16 PDT 2023
Pokemon collection has been viewed.
Tue Aug 08 14:30:22 PDT 2023
Pokemon collection has been viewed.
Tue Aug 08 14:30:22 PDT 2023
Pokemon collection has been viewed.
Tue Aug 08 14:30:26 PDT 2023
Bulbasaur has been viewed.
Tue Aug 08 14:30:26 PDT 2023
Bulbasaur has been removed from the collection.
Tue Aug 08 14:30:26 PDT 2023
Bulbasaur has been added to the party!
Tue Aug 08 14:30:28 PDT 2023
Pokemon party has been viewed.
Tue Aug 08 14:30:28 PDT 2023
Pokemon party has been viewed.

# Phase 4: Task 3
There are a couple things I would do to refactor my code. First, is to improve the readibility 
and clarity of the code. There are a couple of methods, such as the populatewildpokemon method 
that is redundant and does not look clean. I would implement a loop of some sort to add all the 
wild pokemon. There are also a couple methods that are very long, and something we can do is to
extract some methods that are reused to improve the visibility. An example is adding each visual 
component in every class panel, that can be extracted into a seperate method. 