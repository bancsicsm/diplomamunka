package demo.app.paintball.util.services

import android.media.MediaRecorder
import android.os.ParcelFileDescriptor
import java.io.ByteArrayOutputStream

class RecordService {

    private val recorder: MediaRecorder
    private val inputStream: ParcelFileDescriptor.AutoCloseInputStream
    private lateinit var recordingOutputStream: ByteArrayOutputStream

    init {
        val descriptors = ParcelFileDescriptor.createPipe()
        val parcelRead = ParcelFileDescriptor(descriptors[0])
        val parcelWrite = ParcelFileDescriptor(descriptors[1])

        inputStream = ParcelFileDescriptor.AutoCloseInputStream(parcelRead)

        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        recorder.setOutputFile(parcelWrite.fileDescriptor)
    }

    private val recordingThread = Thread {
        recordingOutputStream = ByteArrayOutputStream()
        recorder.prepare()
        recorder.start()
        var read: Int
        val data = ByteArray(16384)
        while (inputStream.read(data, 0, data.size).also { read = it } != -1) {
            recordingOutputStream.write(data, 0, read)
        }
    }

    fun start() {
        recordingThread.start()
    }

    fun stop(): ByteArray {
        recorder.stop()
        recorder.reset()
        recorder.release()
        return recordingOutputStream.toByteArray()
    }
}