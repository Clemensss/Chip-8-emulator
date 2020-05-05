/*
    Class that holds methods for dealing with memory
 */
class Memory(private var rom: UByteArray)
{
    /*Initialize object and add rom do the main ram
     */
    private fun loadROM(ram: UByteArray, rom: UByteArray) :UByteArray
    {
        var tmp = ram

        if(ram.size < rom.size) Error("NOT THE SAME SIZE")
        for(i in rom.indices) tmp[i] = rom[i]

        return tmp
    }

    private var ram = UByteArray(size=0xFFFF)
    init {
        this.ram = loadROM(this.ram, this.rom)
    }
    /*handles memOut */
    fun memOut(addr: UShort): UByte
    {
        return this.ram[((addr - 0x200u).toInt())]
    }

    /*handles memIn*/
    fun memIn(addr: UShort, value: UByte)
    {
        this.ram[addr.toInt()] = value
    }
}


