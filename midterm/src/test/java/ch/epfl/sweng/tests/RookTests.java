package ch.epfl.sweng.tests;

import org.junit.Test;

import ch.epfl.sweng.Color;
import ch.epfl.sweng.InvalidMoveException;
import ch.epfl.sweng.InvalidPositionException;
import ch.epfl.sweng.Rook;
import ch.epfl.sweng.Position;

import static org.junit.Assert.assertEquals;

public class RookTests extends PieceTests<Rook> {
    @Override
    public void setUp() {
        piece = new Rook(position, Color.BLACK);
    }

    @Override
    public void testLegal() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('D', 3);
        assertEquals(piece.getPosition(), Position.positionIfLegal('D', 3));

        piece.moveTo('E', 3);
        assertEquals(piece.getPosition(), Position.positionIfLegal('E', 3));
    }

    // testing the case of a black pawn moving upwards
    @Override
    public void testIllegal() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('E', 5);
    }
}
