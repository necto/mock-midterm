package ch.epfl.sweng;

import static ch.epfl.sweng.Color.WHITE;

/**
 * Class implementing the behaviour of the pawn piece in chess.
 *
 * The Pawn class should only implement standard movement, that is 1 step ahead vertically, for
 * white pawns, and 1 step back vertically, for black pawns. No first move double-jump, no diagonal
 * captures, no promotions, etc.
 */

public class Pawn extends Piece {
    public Pawn(Position position, Color color) {
        super(position, color);
    }

    public Pawn(Pawn p) { super(p.getPosition(), p.getColor()); }

    /* Accounting only for regular move, that is 1 up for white pawns and 1 down for black pawns */
    @Override
    protected boolean isPieceMovementValid(Position.Offset offset) {
        return offset.horizontal == 0 && (offset.vertical == (getColor() == WHITE ? 1 : -1));
    }

    @Override
    public Pawn copy() {
        return new Pawn(this);
    }

    public Superpiece promote(PieceType targetPiece) throws InvalidMoveException {
        if (targetPiece == PieceType.PAWN ||
            targetPiece == PieceType.KING) {
            throw new InvalidMoveException("You can not promote a pawn to this kind of superpiece");
        }
        return new Superpiece(targetPiece, getPosition(), getColor());
    }
}
