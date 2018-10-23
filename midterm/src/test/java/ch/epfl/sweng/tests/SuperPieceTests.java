package ch.epfl.sweng.tests;

import org.junit.Test;

import ch.epfl.sweng.Color;
import ch.epfl.sweng.InvalidMoveException;
import ch.epfl.sweng.InvalidPositionException;
import ch.epfl.sweng.Superpiece;
import ch.epfl.sweng.Position;
import ch.epfl.sweng.PieceType;

import static org.junit.Assert.assertEquals;

public class SuperPieceTests extends PieceTests<Superpiece> {
    @Override
    public void setUp() {
        piece = new Superpiece(PieceType.ROOK, position, Color.BLACK);
    }


    @Override
    public void testLegal() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('D', 3);
        assertEquals(piece.getPosition(), Position.positionIfLegal('D', 3));

        piece.moveTo('E', 3);
        assertEquals(piece.getPosition(), Position.positionIfLegal('E', 3));

        piece.moveTo('E', 7, 'A', 7);
        assertEquals(piece.getPosition(), Position.positionIfLegal('A', 7));
    }

    // testing the case of a black pawn moving upwards
    @Override
    public void testIllegal() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('E', 5);
    }

    @Test
    public void testMakeThemAll() {
        piece = new Superpiece(PieceType.QUEEN, position, Color.BLACK);
        piece = new Superpiece(PieceType.BISHOP, position, Color.BLACK);
        piece = new Superpiece(PieceType.KNIGHT, position, Color.BLACK);
    }

    @Test(expected = InvalidMoveException.class)
    public void invalidMove2Final() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('C', 4, 'E', 8);
    }

    @Test(expected = InvalidMoveException.class)
    public void invalidMove2Zero() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('D', 4, 'D', 4);
    }

    @Test(expected = InvalidMoveException.class)
    public void invalidMove2Intermediate() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('E', 5, 'E', 5);
    }

    @Test(expected = InvalidPositionException.class)
    public void invalidPosition() throws InvalidPositionException, InvalidMoveException {
        piece.moveTo('A', 15, 'E', 5);
    }
}
