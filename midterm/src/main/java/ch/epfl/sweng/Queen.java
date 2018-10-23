package ch.epfl.sweng;

/**
 * Class implementing the behaviour of the queen piece in chess.
 *
 * Queen chess piece moves any number of steps, both forward and backward, along any of the four directions (vertical, horizontal, or one of the two diagonals).
 */

public class Queen extends Piece {
    public Queen(Position position, Color color) {
        super(position, color);
    }

    public Queen(Queen r) { super(r.getPosition(), r.getColor()); }

    @Override
    protected boolean isPieceMovementValid(Position.Offset offset) {
        return (Math.abs(offset.horizontal) + Math.abs(offset.vertical) != 0) &&
            (((offset.horizontal != 0) != (offset.vertical != 0)) ||
             (Math.abs(offset.vertical) == Math.abs(offset.horizontal)));
    }

    @Override
    public Queen copy() {
        return new Queen(this);
    }
}
