import java.util.Stack

class Cpu(rom: UByteArray)
{
    /*flag for halting or interrupt handle*/

    var intr: Boolean = false
    var halt: Boolean = false

    /*registers represented as array of bytes*/
    var regs = UByteArray(size=16)

    /*register*/
    var i:  UShort = 0u
    var pc: UShort = 0u
    var sp: UShort = 0u

    var d:  UByte  = 0u
    var t:  UByte  = 0u

    /*memory*/
    val ram   = Memory(rom=rom)
    val stack = Stack<UShort>()

    /*returns x'th most significant bit */
    private fun getXBits(byte: UByte, x: Int): UByte
    {
        val real = (16 - x)
        if(real < 0) Error("Offset too big")

        return (byte.toInt() shr real).toUByte()
    }

    /*takes two bytes, joins them, and brushes off 4 most significant bits
     */
    private fun least12Bits(byte1: UByte, byte2: UByte): UShort
    {
        val leastSig4: UShort = (0b0000_1111u and byte1.toUInt()).toUShort()
        val joined: Int = (((leastSig4.toInt() shl 8)) or (byte2.toInt()))
        return joined.toUShort()
    }

    /*returns current instruction on PC*/
    private fun currentInst(): UByte
    {
        return this.ram.memOut(this.pc)
    }

    /*decodes most significant 4 bits */
    fun instDecoder(inst: UByte)
    {
        val nextByte = this.ram.memOut(this.pc.plus(2u).toUShort())

        when(getXBits(inst, x=4).toInt()) {
            0x0 -> handleInst0(nextByte)
            0x1 -> handleInst1(inst, nextByte)
            0x2 -> handleInst2(inst, nextByte)
            0x3 -> handleInst3(inst, nextByte)
            0x4 -> handleInst4(inst, nextByte)
            0x5 -> handleInst5(inst, nextByte)
            0x6 -> handleInst6(inst, nextByte)
            0x7 -> handleInst7(inst, nextByte)
            0x8 -> handleInst8(inst, nextByte)
            0x9 -> handleInst9(inst, nextByte)
            0xA -> handleInstA(inst, nextByte)
            0xB -> handleInstB(inst, nextByte)
            0xC -> handleInstC(inst, nextByte)
            0xD -> handleInstD(inst, nextByte)
            0xE -> handleInstE(inst, nextByte)
            0xF -> handleInstF(inst, nextByte)

            else -> {
                Error("Instruction not recognized")
                this.halt = true
            }

        }
        this.pc = this.pc.plus(2u).toUShort()
    }

    /*inst type 0xxx*/
    private fun handleInst0(nextByte: UByte)
    {
        when(nextByte.toInt()) {
            /*clear display */
            0xE0 -> clearDisplay()

            /*return instruction*/
            0xEE -> this.pc = this.stack.pop()
        }
    }

    /*inst type 1xxx*/
    private fun handleInst1(inst: UByte, nextByte: UByte)
    {
        /*jump instruction*/
        this.pc = least12Bits(inst, nextByte)
    }

    /*inst type 1xxx*/
    private fun handleInst2(inst: UByte, nextByte: UByte){}
    private fun handleInst3(inst: UByte, nextByte: UByte){}
    private fun handleInst4(inst: UByte, nextByte: UByte){}
    private fun handleInst5(inst: UByte, nextByte: UByte){}
    private fun handleInst6(inst: UByte, nextByte: UByte){}
    private fun handleInst7(inst: UByte, nextByte: UByte){}
    private fun handleInst8(inst: UByte, nextByte: UByte){}
    private fun handleInst9(inst: UByte, nextByte: UByte){}
    private fun handleInstA(inst: UByte, nextByte: UByte){}
    private fun handleInstB(inst: UByte, nextByte: UByte){}
    private fun handleInstC(inst: UByte, nextByte: UByte){}
    private fun handleInstD(inst: UByte, nextByte: UByte){}
    private fun handleInstE(inst: UByte, nextByte: UByte){}
    private fun handleInstF(inst: UByte, nextByte: UByte){}

    /*handles clearing the screen*/

    private fun clearDisplay(){}

}

fun main(args: Array<String>) {
}
