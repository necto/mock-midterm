package ch.epfl.sweng;

/**
 * Class implementing the behaviour of the bishop piece in chess.
 *
 * A `Superpiece` is a `Piece`, so it can merely `moveTo` a location if the player decides not to use its special two-moves ability.
 * On top of that, `Superpiece` also provides two new overloads of `moveTo`⏤ `moveTo(Position intermediateDestination, Position finalDestination)` and `moveTo(char intermediateColumn, int intermediateRow, char finalColumn, int finalRow)`⏤ that extend the respective semantics of `moveTo(Position destination)` and `moveTo(char column, int row)` to double moves.
 * Both double-move methods should throw an exception if either the intermediate or the final move is erroneous, and the position of the piece, along with any other properties of the object, should remain exactly as they were immediately prior to the invocation of the method. This property is called [strong exception guarantee](https://en.wikipedia.org/wiki/Exception_guarantees). It's OK for the second move to return the to its original position.
 */

public class Superpiece extends Piece {
    Piece inner = null;
    public Superpiece(PieceType pieceType, Position position, Color color) {
        super(position, color);
        switch(pieceType) {
        case ROOK:
            inner = new Rook(position, color);
            break;
        case QUEEN:
            inner = new Queen(position, color);
            break;
        case KNIGHT:
            inner = new Knight(position, color);
            break;
        case BISHOP:
            inner = new Bishop(position, color);
            break;
        };
    }

    private Superpiece(Superpiece b) {
        super(b.getPosition(), b.getColor());
        inner = b.inner;
    }

    @Override
    protected boolean isPieceMovementValid(Position.Offset offset) {
        inner.setPosition(getPosition());
        return inner.isPieceMovementValid(offset);
    }

    @Override
    public Superpiece copy() {
        return new Superpiece(this);
    }

    public void moveTo(Position intermediateDestination, Position finalDestination) throws InvalidPositionException, InvalidMoveException {
        if (intermediateDestination == null) throw new InvalidPositionException("null pointer as intermediate destination");
        if (finalDestination == null) throw new InvalidPositionException("null pointer as final destination");

        Position initialPosition = getPosition();

        Position.Offset offset1 = initialPosition.offsetTo(intermediateDestination);
        Position.Offset offset2 = intermediateDestination.offsetTo(finalDestination);

        if (offset1.isZero() && offset2.isZero())
            throw new InvalidMoveException("A no-op move issued");

        if (!isPieceMovementValid(offset1))
            throw new InvalidMoveException();

        setPosition(intermediateDestination);

        if (!isPieceMovementValid(offset2)) {
            setPosition(initialPosition);
            throw new InvalidMoveException();
        }

        setPosition(finalDestination);
    }

    public void moveTo(char intermediateColumn, int intermediateRow,
                       char finalColumn, int finalRow) throws InvalidPositionException,
                                                    InvalidMoveException
    {
        Position intermediateDestination = Position.positionIfLegal(intermediateColumn, intermediateRow);
        Position finalDestination = Position.positionIfLegal(finalColumn, finalRow);

        if (intermediateDestination != null && finalDestination != null)
            moveTo(intermediateDestination, finalDestination);
        else
            throw new InvalidPositionException();
    }
}
