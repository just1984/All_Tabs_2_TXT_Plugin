import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFile
import java.io.File
import java.io.PrintWriter

class TabToFileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val fileEditorManager = FileEditorManager.getInstance(project)

        val fileContents = StringBuilder()
        val openFiles = fileEditorManager.openFiles

        for (file: VirtualFile in openFiles) {
            val document = FileDocumentManager.getInstance().getDocument(file)
            val fileName = file.name
            val fileContent = document?.text

            fileContents.append("\n-----------------------------------------\n")
            fileContents.append("File Name: $fileName\n\n")
            fileContents.append(fileContent)
        }

        val tempFile = File.createTempFile("tabContent", ".txt")
        val writer = PrintWriter(tempFile, "UTF-8")
        println("Die Datei wurde hier erstellt: ${tempFile.absolutePath}")
        writer.println(fileContents.toString())
        writer.close()
    }
}