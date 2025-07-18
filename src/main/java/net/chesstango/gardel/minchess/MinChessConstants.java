package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
class MinChessConstants {

    static final long A1 = 0x01L;
    static final long C1 = 0x04L;
    static final long D1 = 0x08L;
    static final long E1 = 0x10L;
    static final long F1 = 0x20L;
    static final long G1 = 0x40L;
    static final long H1 = 0x80L;

    static final long A8 = 0x0100000000000000L;
    static final long C8 = 0x0400000000000000L;
    static final long D8 = 0x0800000000000000L;
    static final long E8 = 0x1000000000000000L;
    static final long F8 = 0x2000000000000000L;
    static final long G8 = 0x4000000000000000L;
    static final long H8 = 0x8000000000000000L;


    static final long LIMIT_NORTH = 0xFF00000000000000L;
    static final long LIMIT_SOUTH = 0x00000000000000FFL;
    static final long LIMIT_EAST = 0x8080808080808080L;
    static final long LIMIT_WEST = 0x101010101010101L;

    static final long LIMIT_NORTH_EAST = 0xFF00000000000000L | 0x8080808080808080L;
    static final long LIMIT_NORTH_WEST = 0xFF00000000000000L | 0x101010101010101L;

    static final long LIMIT_SOUTH_EAST = 0x00000000000000FFL | 0x8080808080808080L;
    static final long LIMIT_SOUTH_WEST = 0x00000000000000FFL | 0x101010101010101L;

    static final long[] KING_JUMPS = {
            0x0000000000000302L,
            0x0000000000000705L,
            0x0000000000000E0AL,
            0x0000000000001C14L,
            0x0000000000003828L,
            0x0000000000007050L,
            0x000000000000E0A0L,
            0x000000000000C040L,
            0x0000000000030203L,
            0x0000000000070507L,
            0x00000000000E0A0EL,
            0x00000000001C141CL,
            0x0000000000382838L,
            0x0000000000705070L,
            0x0000000000E0A0E0L,
            0x0000000000C040C0L,
            0x0000000003020300L,
            0x0000000007050700L,
            0x000000000E0A0E00L,
            0x000000001C141C00L,
            0x0000000038283800L,
            0x0000000070507000L,
            0x00000000E0A0E000L,
            0x00000000C040C000L,
            0x0000000302030000L,
            0x0000000705070000L,
            0x0000000E0A0E0000L,
            0x0000001C141C0000L,
            0x0000003828380000L,
            0x0000007050700000L,
            0x000000E0A0E00000L,
            0x000000C040C00000L,
            0x0000030203000000L,
            0x0000070507000000L,
            0x00000E0A0E000000L,
            0x00001C141C000000L,
            0x0000382838000000L,
            0x0000705070000000L,
            0x0000E0A0E0000000L,
            0x0000C040C0000000L,
            0x0003020300000000L,
            0x0007050700000000L,
            0x000E0A0E00000000L,
            0x001C141C00000000L,
            0x0038283800000000L,
            0x0070507000000000L,
            0x00E0A0E000000000L,
            0x00C040C000000000L,
            0x0302030000000000L,
            0x0705070000000000L,
            0x0E0A0E0000000000L,
            0x1C141C0000000000L,
            0x3828380000000000L,
            0x7050700000000000L,
            0xE0A0E00000000000L,
            0xC040C00000000000L,
            0x0203000000000000L,
            0x0507000000000000L,
            0x0A0E000000000000L,
            0x141C000000000000L,
            0x2838000000000000L,
            0x5070000000000000L,
            0xA0E0000000000000L,
            0x40C0000000000000L};

    static final long[] KNIGHT_JUMPS = {
            0x0000000000020400L,
            0x0000000000050800L,
            0x00000000000A1100L,
            0x0000000000142200L,
            0x0000000000284400L,
            0x0000000000508800L,
            0x0000000000A01000L,
            0x0000000000402000L,
            0x0000000002040004L,
            0x0000000005080008L,
            0x000000000A110011L,
            0x0000000014220022L,
            0x0000000028440044L,
            0x0000000050880088L,
            0x00000000A0100010L,
            0x0000000040200020L,
            0x0000000204000402L,
            0x0000000508000805L,
            0x0000000A1100110AL,
            0x0000001422002214L,
            0x0000002844004428L,
            0x0000005088008850L,
            0x000000A0100010A0L,
            0x0000004020002040L,
            0x0000020400040200L,
            0x0000050800080500L,
            0x00000A1100110A00L,
            0x0000142200221400L,
            0x0000284400442800L,
            0x0000508800885000L,
            0x0000A0100010A000L,
            0x0000402000204000L,
            0x0002040004020000L,
            0x0005080008050000L,
            0x000A1100110A0000L,
            0x0014220022140000L,
            0x0028440044280000L,
            0x0050880088500000L,
            0x00A0100010A00000L,
            0x0040200020400000L,
            0x0204000402000000L,
            0x0508000805000000L,
            0x0A1100110A000000L,
            0x1422002214000000L,
            0x2844004428000000L,
            0x5088008850000000L,
            0xA0100010A0000000L,
            0x4020002040000000L,
            0x0400040200000000L,
            0x0800080500000000L,
            0x1100110A00000000L,
            0x2200221400000000L,
            0x4400442800000000L,
            0x8800885000000000L,
            0x100010A000000000L,
            0x2000204000000000L,
            0x0004020000000000L,
            0x0008050000000000L,
            0x00110A0000000000L,
            0x0022140000000000L,
            0x0044280000000000L,
            0x0088500000000000L,
            0x0010A00000000000L,
            0x0020400000000000L};


    static short encodeMove(long fromPosition, long toPosition) {
        int fromIdx = Long.numberOfTrailingZeros(fromPosition);
        int fromFile = fromIdx % 8;
        int fromRank = fromIdx / 8;
        int binaryEncodedFrom = ((fromFile << 6) | (fromRank << 9));

        int toIdx = Long.numberOfTrailingZeros(toPosition);
        int toFile = toIdx % 8;
        int toRank = toIdx / 8;
        int binaryEncodedTo = (toFile | (toRank << 3));

        return (short) (binaryEncodedFrom | binaryEncodedTo);
    }

    static short encodeMove(long fromPosition, long toPosition, int promotionPiece) {
        short fromToEncoded = encodeMove(fromPosition, toPosition);
        return (short) (promotionPiece << 12 | fromToEncoded);
    }
}
