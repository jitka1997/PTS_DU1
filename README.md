### Initialization
- You can modify game constants in `Initial.java`
- You need to create SimpleDominionInterface implementation to play the game

### Gameplay and Endgame
- Player will automatically play all theirs treasure cards (only Copper in this implementation) at the beginning of each play phase (of course they will not cost them any Action)
- You can create endgame strategies parameterized using instances of `AtLeastNEmptyDecks.java` or completely custom endgame strategies by implementing the `EndGameStrategy.java` interface

### Testing
- Use `InitialForTest.java` for tests
- Or create new implementation for `InitialInterface.java` and make it so it suits your test