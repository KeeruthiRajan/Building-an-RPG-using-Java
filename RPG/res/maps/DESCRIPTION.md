## How it Works
- Map is a **30 x 30** grid made of tiles from tile folder
- Each number in a text file marks the index of tile stored in tile array of TileManager
- **draw()** function uses these indices to figure out what tile to draw at the first space
- Modifiying a text file (replacing a number with 0 - 6), will be reflected in the game panel at the specific space.

## Dimensions of Tile
- Original size: **16 x 16**
- In game: Scaled thrice, ie., **48 x 48**

## Future Plans
- Increase grid size
- Add more tiles
- Connect multiple text maps somehow
