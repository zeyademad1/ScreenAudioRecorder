package com.zeyad.internalrecorder.Utils

object PCMConverter {
    fun createWavHeader(
        dataSize: Int,
        sampleRate: Int,
        numChannels: Int,
        bitDepth: Int
    ): ByteArray {
        val totalDataLen = dataSize + 36
        val byteRate = sampleRate * numChannels * bitDepth / 8
        val blockAlign = numChannels * bitDepth / 8

        return ByteArray(44).apply {
            // RIFF header
            this[0] = 'R'.code.toByte()
            this[1] = 'I'.code.toByte()
            this[2] = 'F'.code.toByte()
            this[3] = 'F'.code.toByte()

            // Overall file size
            this[4] = (totalDataLen and 0xff).toByte()
            this[5] = ((totalDataLen shr 8) and 0xff).toByte()
            this[6] = ((totalDataLen shr 16) and 0xff).toByte()
            this[7] = ((totalDataLen shr 24) and 0xff).toByte()

            // WAVE header
            this[8] = 'W'.code.toByte()
            this[9] = 'A'.code.toByte()
            this[10] = 'V'.code.toByte()
            this[11] = 'E'.code.toByte()

            // fmt subchunk
            this[12] = 'f'.code.toByte()
            this[13] = 'm'.code.toByte()
            this[14] = 't'.code.toByte()
            this[15] = ' '.code.toByte()

            // Subchunk1Size (16 for PCM)
            this[16] = 16
            this[17] = 0
            this[18] = 0
            this[19] = 0

            // Audio format (1 for PCM)
            this[20] = 1
            this[21] = 0

            // Number of channels
            this[22] = numChannels.toByte()
            this[23] = 0

            // Sample rate
            this[24] = (sampleRate and 0xff).toByte()
            this[25] = ((sampleRate shr 8) and 0xff).toByte()
            this[26] = ((sampleRate shr 16) and 0xff).toByte()
            this[27] = ((sampleRate shr 24) and 0xff).toByte()

            // Byte rate
            this[28] = (byteRate and 0xff).toByte()
            this[29] = ((byteRate shr 8) and 0xff).toByte()
            this[30] = ((byteRate shr 16) and 0xff).toByte()
            this[31] = ((byteRate shr 24) and 0xff).toByte()

            // Block align
            this[32] = blockAlign.toByte()
            this[33] = 0

            // Bits per sample
            this[34] = bitDepth.toByte()
            this[35] = 0

            // Data subchunk header
            this[36] = 'd'.code.toByte()
            this[37] = 'a'.code.toByte()
            this[38] = 't'.code.toByte()
            this[39] = 'a'.code.toByte()

            // Data chunk size
            this[40] = (dataSize and 0xff).toByte()
            this[41] = ((dataSize shr 8) and 0xff).toByte()
            this[42] = ((dataSize shr 16) and 0xff).toByte()
            this[43] = ((dataSize shr 24) and 0xff).toByte()
        }
    }

}