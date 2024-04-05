## SpaceCartography 🪐 ##

### __Short Summery about the theme:__

SpaceCartography is an object-oriented programming (OOP) project designed to simulate interstellar navigation and exploration.

<hr>

### __Classes:__

| CLASS  | DESCRIPTION | 
|------- |-------------|
| Coordinates | Class formed of three doubles representing the coordinate axies X, Y and Z used in most of the classes fro representing the position of objects.
| Planet | Class representing a rounded astronomical body. |
| Satellite | Class representing a rounded astronomical body that ortbits a planet. |
| Star | Class representing a lumious astronomical body.|
| PlanetHostingStar | Subclass of the class Star representing a bigger varaint of a normal star around which PlanetSystems can orbit. |
| DwarfStar | Subclass of the class Star representing a smaller variant of a normal star that can belong in a constellation.|
| PlanetSystem | Class representing the system formed by a central Planet object and the Satellite objects that orbit them.|
| StarSystem | Class representing the system formed by a central Star object and the PlanetSystem objects that orbit them.|
| SpaceShip | Class representing a moving spacial vehicle which direction, destination and speed can be altered by the user thorough functions.|
| ServiceClass | Class used to hold and access most functions as well as creating collections for objects.|
| MenuClass | Singleton Class used to create a terminal menu for minimal better experice. |
| Main | Class representing the native Java entry-point. |

<hr>

### __Main functionalities:__ 
The main functionalites of this project are around the SpaceShip objects and are as follows:
- Create a SpaceShip(or multiple).
- Set/Update its attributes.
- Set the direction of the spaceship.
- Set a specific destination for the spaceship.
- Aproximate the time it would take for a spaceship to arrive at a given location.

There are, as well, more general functionalities for most objects such as:
- Create and store any of the celestial objects.
- Set/Update attributes.
- Calculate the distance between any two objects.
- Polarize coordinates.
- Retrieve the planets that are life sustainable.
- Retrieve planets based on their proximity to specified coordinates.
- Retrieve and Show attributes of any type of objects.

<hr>

### ___Unfinished:__
- The computeDistance() function of the ServiceClass:
```java
public double computeDistance(Object obj1, Object obj2)
    {
        Coordinates coord1 = getCoordinates(obj1);
        Coordinates coord2 = getCoordinates(obj2);
        return coord1.distanceTo(coord2);
    }

    private Coordinates getCoordinates(Object obj) {
        if (obj instanceof Planet) {
            return ((Planet) obj).getCoordinates();
        } else if (obj instanceof Star) {
            return ((Star) obj).getCoordinates();
        } else if (obj instanceof Satellite) {
            return ((Satellite) obj).getCoordinates();
        } else if (obj instanceof StarSystem) {
            return ((StarSystem) obj).getCoordinates();
        }else if (obj instanceof PlanetSystem) {
            return ((PlanetSystem) obj).getCoordinates();
        }else if (obj instanceof SpaceShip) {
            return ((SpaceShip) obj).getCoordinates();
        }else {
            throw new IllegalArgumentException("ERROR::OBJECT_DOES_NOT_HAVE_COORDINATES");
        }
    }
```
As of rightnow the function getCoordinates throws and error but does not treat it.
- The MenuClass is incomplete. It offers access to most functionalities but not in a very clean way:
