# Solution

The solution for imc assessment.
It consists of two unequal parts.
One is visitor and second one is Rock-Paper-Scissors application

## Visitor
Just basic visitor pattern with tests.

Class `Shape` defines interface for shapes and a visitor.
There are two simple implementations that demonstrate major strength of this pattern:
adding as many functionality as needed without modifying underlying classes.

## Rock Paper Scissors
### Build and run

```bash
mvn clean install # Build on any platform
bash run-lock.sh # for Linux or Mac
run-rock.bat # for Windows
```

### Structure
First, this solution is a total overkill for this problem.
Rock Paper Scissors is a simple game and requirements of clear, 
maintainable and extensible code can be done much simpler with less code.

I generalized the solution to an extreme and laid out a foundation
on which robust cloud platform for RPS-like games can be built.

#### General Idea
The solution is based on Robert C. Martin's idea of 
[clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) 
which implies strong emphasis on the separation of concerns and the dependency rule
(the core logic is in the center and all dependencies go inward).

I chose the [hexagonal architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) 
by Alistair Cockburn to achieve this because 
it is one of the simplest ones to understand it allows to achieve really loose 
coupling between components.

#### Implementation Details
The file structure is as follows:
```
rps/
   application/
   domain/game
       application/
       core/
           model/
           ports/
               incoming/
               outgoing/
       infrastructure/
   infrastructure/
```
The root folder contains three top-level folders, 
application domain and infrastructure.

`rps/domain` contains the most interesting logic.
There can be more than one domain, but in this case, there's only one called
`game`.

The domain has again three folders, `game/application`, `game/core` and 
`game/infrastructure`.

`game/core` contains the logic dedicated to the game itself (model) and also
defines interfaces on how this core will interact with outside world.
Ports are used for this task.
There are two kinds of ports, incoming and outgoing.
Incoming posts define how outside world interacts with the game, 
while outgoing ports define how core interacts with underlying systems.
`GameService` is a sort of orchestrator which defines what should be done.
Important note that `game/core` has no external dependencies and completely
framework-agnostic. And database-agnostic and protocol-agnostic and so on.

`game/application` is used as a gateway and a face turned to the outside world.
In this case, it's just plain old java api which is called from somewhere from the code.

`game/infrastructure` contains implementations of outgoing ports and where
connections to databases can live.
Right now here lies only in-memory 'database' which helps to store results of
the current game session and a dummy bot implementation.

`rps/application` basically contains logic for the console client of the game.

`rps/infrastructure` binds all necessary classes together.

#### Final notes
Developing such system was a great fun.
Though I'm stressing that biggest advantage of this system is extensibility,
I also tried to make this system as user-friendly as possible and put a lot of
effort in handling of user errors and tried to provide (hopefully) useful help.

There was quite little time for a system like this, so tradeoffs were necessary.

Unfortunately, I didn't have enough time to write good tests for all this code.
High hopes that this will be forgiven.
Looking from this point it seems that tests should not have fallen the victim of
such sacrifice and in the vast majority of cases it's better to have fewer but 
better tested features.

In a real world I'd advocate for TDD approach 
(you always have time to write tests if you write it first) and never ever
release code which is not thoroughly tested.
Though in some simple cases it might be an overkill as well.

Thank you for reading!
