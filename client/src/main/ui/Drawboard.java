package ui;

import chess.*;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import static ui.EscapeSequences.*;
import static ui.EscapeSequences.SET_TEXT_COLOR_GREEN;

public class Drawboard {

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final int LINE_WIDTH_IN_CHARS = 1;
    private static final String EMPTY = "\u2003 ";
    private static boolean white = true;

        Drawboard(ChessBoard board) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        drawChessBoard(out, board);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    Drawboard(ChessBoard board, ChessGame.TeamColor color) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        drawChessBoard(out, color, board);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void drawChessBoard(PrintStream out, ChessGame.TeamColor color, ChessBoard board) {
            if(color == ChessGame.TeamColor.BLACK) {
                drawHeaders(out, "1");
                drawRowOfSquares(out, board);
                drawHeaders(out, "1");
            }else {

                System.out.println(" ");
                drawHeaders(out, "2");
                drawRowOfSquares2(out, board);
                drawHeaders(out, "2");

            }

    }
    private static void drawChessBoard(PrintStream out, ChessBoard board) {
            drawHeaders(out, "1");
            drawRowOfSquares(out, board);
            drawHeaders(out, "1");

            System.out.println(" ");
            drawHeaders(out, "2");
            drawRowOfSquares2(out, board);
        drawHeaders(out, "2");


    }
    Drawboard(ChessBoard board, Collection<ChessMove> moves, ChessGame.TeamColor color){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        drawChessBoard2(out, board, moves, color);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }
    private static void drawChessBoard2(PrintStream out, ChessBoard board, Collection<ChessMove> moves, ChessGame.TeamColor color) {
        if(color == ChessGame.TeamColor.WHITE) {
            drawHeaders(out, "2");
            highlightMovesWhite(out, board, moves);
            drawHeaders(out, "2");
        }else{
            drawHeaders(out, "1");
            highlightMovesBlack(out, board, moves);
            drawHeaders(out, "1");
        }
    }

    private static void drawRowOfSquares(PrintStream out, ChessBoard board) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
        for (int squareRow = 1; squareRow < 9; ++squareRow) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            for (int boardCol = 8; boardCol > 0; --boardCol) {

                if (white) {
                    setWhite(out);
                    white = false;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        out.print(SET_TEXT_COLOR_WHITE);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    } else{
                        out.print(SET_TEXT_COLOR_RED);
                    out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                } else {
                    setBlack(out);
                    white = true;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        out.print(SET_TEXT_COLOR_BLACK);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                }
            }
            white = !white;
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
        }
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
    }
    private static void drawRowOfSquares2(PrintStream out, ChessBoard board) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
        for (int squareRow = 8; squareRow > 0; --squareRow) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            for (int boardCol = 1; boardCol < 9; ++boardCol) {
                if (white) {
                    setWhite(out);
                    white = false;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        out.print(SET_TEXT_COLOR_WHITE);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                } else {
                    setBlack(out);
                    white = true;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        out.print(SET_TEXT_COLOR_BLACK);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                }
            }
            white = !white;
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
        }
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
    }
    private static void highlightMovesBlack(PrintStream out, ChessBoard board, Collection<ChessMove> moves) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
        boolean highlight = false;
        boolean yellow = false;
        for (int squareRow = 1; squareRow < 9; ++squareRow) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");

            for (int boardCol = 8; boardCol > 0; --boardCol) {
                for(ChessMove move : moves){
                    if(move.getEndPosition().getRow() == squareRow && move.getEndPosition().getColumn() == boardCol){
                        highlight = true;
                    }
                    if(move.getStartPosition().getRow() == squareRow && move.getStartPosition().getColumn() == boardCol){
                        yellow = true;
                    }
                }
                if (white) {
                    if(highlight){
                        setGreen(out);
                    } else if(yellow){
                        setYellow(out);
                    }else {
                        setWhite(out);
                    }
                    white = false;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        if(highlight) {
                            out.print(SET_TEXT_COLOR_GREEN);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        } else{
                            out.print(SET_TEXT_COLOR_WHITE);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        }
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                } else {
                    if(highlight){
                        setGreen(out);
                    } else if(yellow){
                        setYellow(out);
                    }else {
                        setBlack(out);
                    }
                    white = true;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        if(highlight) {
                            out.print(SET_TEXT_COLOR_GREEN);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        } else{
                            out.print(SET_TEXT_COLOR_BLACK);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        }
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                }
                highlight = false;
                yellow = false;
            }
            white = !white;
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
        }
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
    }
    private static void highlightMovesWhite(PrintStream out, ChessBoard board,  Collection<ChessMove> moves) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
        boolean highlight = false;
        boolean yellow = false;
        int row = 0;
        int col = 0;
        for (int squareRow = 8; squareRow > 0; --squareRow) {
            row++;
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            for (int boardCol = 1; boardCol < 9; ++boardCol) {
                col++;
                for (ChessMove move : moves) {
                    if (move.getEndPosition().getRow() == squareRow && move.getEndPosition().getColumn() == boardCol) {
                        highlight = true;
                    }
                    if(move.getStartPosition().getRow() == squareRow && move.getStartPosition().getColumn() == boardCol){
                        yellow = true;
                    }
                }
                if (white) {
                    if(highlight){
                        setGreen(out);
                    } else if(yellow){
                        setYellow(out);
                    }else {
                        setWhite(out);
                    }
                    white = false;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        if(highlight) {
                            out.print(SET_TEXT_COLOR_GREEN);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        } else{
                            out.print(SET_TEXT_COLOR_WHITE);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        }
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                } else {
                    if(highlight){
                        setGreen(out);
                    } else if(yellow){
                        setYellow(out);
                    }else {
                        setBlack(out);
                    }
                    white = true;
                    if (board.getPiece(new ChessPositions(squareRow, boardCol)) == null) {
                        if(highlight) {
                            out.print(SET_TEXT_COLOR_GREEN);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        } else{
                            out.print(SET_TEXT_COLOR_BLACK);
                            out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                        }
                    } else {
                        out.print(SET_TEXT_COLOR_RED);
                        out.print(printChessPiece(board.getPiece(new ChessPositions(squareRow, boardCol))));
                    }
                }
                highlight = false;
                yellow = false;
            }
            white = !white;
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(" " + squareRow + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();

        }
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_WHITE);
    }
    private static void setGreen(PrintStream out) {
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_WHITE);
    }
    private static void setYellow(PrintStream out) {
        out.print(SET_BG_COLOR_YELLOW);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_BLACK);
    }

    private static String printChessPiece(ChessPiece piece) {
        if(piece == null){
            return " ♕ ";
        }
        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            return switch (piece.getPieceType()) {
                case PAWN -> " ♙ ";
                case QUEEN -> " ♕ ";
                case KING -> " ♔ ";
                case ROOK -> " ♖ ";
                case KNIGHT -> " ♘ ";
                case BISHOP -> " ♗ ";

            };
        } else {
            return switch (piece.getPieceType()) {
                case PAWN -> " ♟ ";
                case QUEEN -> " ♛ ";
                case KING -> " ♚ ";
                case ROOK -> " ♜ ";
                case KNIGHT -> " ♞ ";
                case BISHOP -> " ♝ ";

            };
        }

    }
    private static void drawHeaders(PrintStream out, String num) {
        setBlack(out);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print("    ");
        if(num == "1") {
            String[] headers = {"h", "g", "f", "e", "d", "c", "b", "a"};
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                drawHeader(out, headers[boardCol]);
                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
                    out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
                }
            }

        }
        if(num == "2"){
            String[] headers = {"a", "b", "c", "d", "e", "f", "g", "h"};
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                drawHeader(out, headers[boardCol]);
                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
                    out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
                }
            }
        }
        out.print(" \u2003 ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
        out.println();
    }
    private static void drawHeader(PrintStream out, String headerText) {
        int prefixLength = 1/2;
        int suffixLength = 1/2;
        out.print(EMPTY.repeat(prefixLength));
        printHeaderText(out, headerText);
        out.print(EMPTY.repeat(suffixLength));
    }
    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_GREEN);
        out.print(player);
    }
}
